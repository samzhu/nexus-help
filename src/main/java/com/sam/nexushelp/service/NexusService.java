package com.sam.nexushelp.service;

import com.sam.nexushelp.config.NexusConfiguration;
import com.sam.nexushelp.dto.search.ComponentItem;
import com.sam.nexushelp.dto.search.SearchResponse;
import com.sam.nexushelp.dto.task.ListTasksResponse;
import com.sam.nexushelp.dto.task.TaskItem;
import com.sam.nexushelp.feign.NexusClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NexusService {

    @Autowired
    private NexusConfiguration nexusConfiguration;

    @Autowired
    private NexusClient nexusClient;

    public List<ComponentItem> findAllComponents() {
        String continuationToken = null;
        List<ComponentItem> componentItems = new ArrayList<>();
        do {
            SearchResponse searchResponse = nexusClient.searchComponents(
                    this.genAuthorization(), nexusConfiguration.getRepository(), nexusConfiguration.getFormat(), null, continuationToken);
            componentItems.addAll(searchResponse.getComponentItems());
            continuationToken = searchResponse.getContinuationToken();
        } while (StringUtils.hasText(continuationToken));
        return componentItems;
    }

    public void deleteComponent(String id) {
        nexusClient.deleteComponent(this.genAuthorization(), id);
    }

    public List<TaskItem> listAllTasks() {
        String continuationToken = null;
        List<TaskItem> taskItems = new ArrayList<>();
        do {
            ListTasksResponse listTasksResponse = nexusClient.listTasks(this.genAuthorization());
            taskItems.addAll(listTasksResponse.getTaskItems());
            continuationToken = listTasksResponse.getContinuationToken();
        } while (StringUtils.hasText(continuationToken));
        return taskItems;
    }

    public List<TaskItem> filterDeleteUnusedManifestsAndImages(List<TaskItem> taskItems) {
        return taskItems.stream().filter(task -> "repository.docker.gc" .equals(task.getType())).collect(Collectors.toList());
    }

    public List<TaskItem> filterCompactingBlobStore(List<TaskItem> taskItems) {
        return taskItems.stream().filter(task -> "blobstore.compact" .equals(task.getType())).collect(Collectors.toList());
    }

    public void taskRun(String id) {
        nexusClient.runTask(this.genAuthorization(), id);
    }

    private String genAuthorization() {
        String token = Base64Utils.encodeToString(
                (nexusConfiguration.getUsername() + ":" + nexusConfiguration.getPassword())
                        .getBytes(StandardCharsets.UTF_8));
        return "Basic " + token;
    }
}
