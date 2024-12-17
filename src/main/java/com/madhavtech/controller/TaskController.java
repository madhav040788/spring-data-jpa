package com.madhavtech.controller;

import com.madhavtech.model.Task;
import com.madhavtech.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);

    @PostMapping
     public ResponseEntity<Task> addTask(@RequestBody Task task){
         Task responseTask = taskService.saveTask(task);
         return new ResponseEntity<>(responseTask, HttpStatus.CREATED);
     }

     @GetMapping
     public ResponseEntity<List<Task>> findAllTask(){
         List<Task> allTaskResponse = taskService.getAllTask();
         return new ResponseEntity<>(allTaskResponse,HttpStatus.OK);
     }

     @GetMapping("/{taskId}")
     public ResponseEntity<Task> findSingleTask( @PathVariable String taskId){
         Task signleResponse = taskService.singleTask(taskId);
         return new ResponseEntity<Task>(signleResponse,HttpStatus.OK);
     }

     @PutMapping
     public ResponseEntity<Task> uptoDateTask(@RequestBody Task task){
         Task updateResponse = taskService.updateTask(task);
         return new ResponseEntity<>(updateResponse,HttpStatus.OK);
     }

     @DeleteMapping("/{taskId}")
     public String deleteTask(@PathVariable  String taskId){
         return taskService.deleteTask(taskId);
     }

     @GetMapping("/assignee/{assignee}/priority/{priority}")
     public List<Task> getTaskByAssigneeAndPriority(@PathVariable String assignee,@PathVariable String priority){
        return taskService.getTaskByAssigneeAndPriorityTwo(assignee,priority);
     }
}
