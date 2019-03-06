package com.sam.nexushelp.schedule;

import com.sam.nexushelp.config.CleanupPolicy;
import com.sam.nexushelp.config.Mail;
import com.sam.nexushelp.config.NexusConfiguration;
import com.sam.nexushelp.config.NotifyConfiguration;
import com.sam.nexushelp.dto.search.ComponentItem;
import com.sam.nexushelp.dto.task.TaskItem;
import com.sam.nexushelp.policies.CleanupPoliciesInterface;
import com.sam.nexushelp.service.NexusService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Component
public class JobCleanNexus {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private NexusConfiguration nexusConfiguration;
    @Autowired
    private NotifyConfiguration notifyConfiguration;
    @Autowired
    private NexusService nexusService;
    @Autowired
    private ApplicationContext applicationContext;

    @Scheduled(cron = "${job-clean-nexus.cron}")
    public void clean() {
        log.debug(LocalDateTime.now() + " 開始執行任務");
        StringBuilder sbMailMsg = new StringBuilder();
        sbMailMsg.append(LocalDateTime.now() + " 開始執行任務\r\n");
        sbMailMsg.append("\r\n");
        try {
            // 查詢取出所有組件
            List<ComponentItem> componentItems = nexusService.findAllComponents();
            // 清除條件列表
            List<CleanupPolicy> cleanupPolicies = nexusConfiguration.getCleanupPolicies();
            for (CleanupPolicy cleanupPolicy : cleanupPolicies) {
                CleanupPoliciesInterface cleanupPoliciesInterface = (CleanupPoliciesInterface) applicationContext.getBean(cleanupPolicy.getPoliciesName());
                componentItems = cleanupPoliciesInterface.filterComponentItem(cleanupPolicy, componentItems);
            }
            sbMailMsg.append(LocalDateTime.now() + " 清除以下組件\r\n");
            // 執行清除
            componentItems.forEach(componentItem -> {
                sbMailMsg.append(componentItem.getName() + ", " + componentItem.getVersion() + "\r\n");
//                nexusService.deleteComponent(componentItem.getId());
            });
            sbMailMsg.append("\r\n");
            // 取出所有任務
            List<TaskItem> taskItems = nexusService.listAllTasks();
            sbMailMsg.append(LocalDateTime.now() + " 執行以下任務\r\n");
            // 清除沒有用的
            List<TaskItem> deleteUnuseds = nexusService.filterDeleteUnusedManifestsAndImages(taskItems);
            deleteUnuseds.forEach(taskItem -> {
                sbMailMsg.append(taskItem.getId() + ", " + taskItem.getName() + ", " + taskItem.getType() + "\r\n");
                nexusService.taskRun(taskItem.getId());
            });
            sbMailMsg.append("\r\n");
            // 進行磁碟整理
            List<TaskItem> compactings = nexusService.filterCompactingBlobStore(taskItems);
            compactings.forEach(taskItem -> {
                sbMailMsg.append(taskItem.getId() + ", " + taskItem.getName() + ", " + taskItem.getType() + "\r\n");
                nexusService.taskRun(taskItem.getId());
            });
            sbMailMsg.append("\r\n");
            sbMailMsg.append(LocalDateTime.now() + " 任務完成\r\n");
        } catch (Exception e) {
            log.error("", e);
            sbMailMsg.append(LocalDateTime.now() + " 執行任務發生錯誤\r\n");
            sbMailMsg.append(e.getMessage());
        } finally {
            Mail mailConfig = notifyConfiguration.getMail();
            MimeMessage mimeMessage = mailSender.createMimeMessage();
//            SimpleMailMessage mimeMessage = new SimpleMailMessage();
            try {
                MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
                mimeMessageHelper.setFrom(mailConfig.getFrom());
                mimeMessageHelper.setTo(mailConfig.getTo().split(","));
                mimeMessageHelper.setSubject(mailConfig.getSubject());
                mimeMessageHelper.setText(sbMailMsg.toString());
                mailSender.send(mimeMessage);
            } catch (MessagingException e) {
                log.error("", e);
            }
        }
    }
}