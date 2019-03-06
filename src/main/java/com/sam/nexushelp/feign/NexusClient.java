package com.sam.nexushelp.feign;

import com.sam.nexushelp.dto.search.SearchResponse;
import com.sam.nexushelp.dto.task.ListTasksResponse;
import com.sam.nexushelp.dto.task.TaskItem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "nexus", url = "${nexus.url}")
public interface NexusClient {

    @GetMapping(path = "/service/rest/v1/search")
    SearchResponse searchComponents(
            @RequestHeader("Authorization") String authorization,
            @RequestParam("repository") String repository,
            @RequestParam("format") String format,
            @RequestParam("q") String q,
            @RequestParam("continuationToken") String continuationToken);

    @DeleteMapping(path = "/service/rest/v1/components/{id}")
    void deleteComponent(@RequestHeader("Authorization") String authorization, @PathVariable("id") String id);

    @GetMapping(path = "/service/rest/v1/tasks")
    ListTasksResponse listTasks(@RequestHeader("Authorization") String authorization);
    // type
    // repository.cleanup
    // repository.docker.gc
    // blobstore.compact

    @GetMapping(path = "/service/rest/v1/tasks/{id}")
    TaskItem getTask(@RequestHeader("Authorization") String authorization, @PathVariable("id") String id);

    @PostMapping(path = "/service/rest/v1/tasks/{id}/run")
    void runTask(@RequestHeader("Authorization") String authorization, @PathVariable("id") String id);
}
