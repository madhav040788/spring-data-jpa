package com.madhavtech.repository;

import com.madhavtech.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface TaskRepository extends MongoRepository<Task,String> {
    List<Task> findByAssigneeAndPriority(String assignee, String priority);

    @Query(value = "{assignee: 0? ,priority: ?1}",fields = "{'description' :1, 'storyPoint' :2}")
    List<Task> findByAssigneeAndPriorityGiven(String assignee, String priority);
}
