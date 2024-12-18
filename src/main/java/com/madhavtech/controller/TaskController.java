package com.madhavtech.controller;

import com.madhavtech.model.Task;
import com.madhavtech.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
     @GetMapping("/sp/{storyPoint}/priority/{priority}")
     public ResponseEntity<List<Task>> fetchTaskByTwoFeilds(@PathVariable int storyPoint, @PathVariable String priority){
        List<Task> responseBySpAndPrio = taskService.getTasksByStoreypointAndPriority(storyPoint, priority);
        return new ResponseEntity<>(responseBySpAndPrio,HttpStatus.OK);
     }

     @GetMapping("/in/{storyPoint}")
     public ResponseEntity<List<Task>>  fetchTaskByInStoryPoints(@PathVariable int storyPoint){
        List<Task> responseByInSp = taskService.getTaskByMultipleStoryPoints(storyPoint);
        return new ResponseEntity<>(responseByInSp,HttpStatus.OK);
     }
        // BETWEEN MIN&MAX
     @GetMapping("/between/{minPriority}/{maxPriority}")
    public ResponseEntity<List<Task>> fetchAllTasksBetweenPriority(@PathVariable String minPriority,@PathVariable String maxPriority){
        List<Task> resBetweenMinMax = taskService.getTaskBetweenPriority(minPriority, maxPriority);
        return new ResponseEntity<>(resBetweenMinMax,HttpStatus.OK);
     }
     @GetMapping("/less/{storyPoint}")
     public ResponseEntity<List<Task>> fetchAllTaskLessThanSp(@PathVariable int storyPoint){
        List<Task> resLessThan =taskService.getTasksLessThanStoryPoint(storyPoint);
        return new ResponseEntity<>(resLessThan,HttpStatus.OK);
     }

    @GetMapping("/greater/{storyPoint}")
    public ResponseEntity<List<Task>> fetchAllTaskGreaterThanSp(@PathVariable int storyPoint){
        List<Task> resGreaterThan =taskService.getTaskGraterThanStoryPoint(storyPoint);
        return new ResponseEntity<>(resGreaterThan,HttpStatus.OK);
    }

    @GetMapping("/like/{assignee}")
    public ResponseEntity<List<Task>> fetchAllTaskAssigneeNames(@PathVariable String assignee){
        List<Task> resAssignee =taskService.getTasksLikeAssigneeName(assignee);
        return new ResponseEntity<>(resAssignee,HttpStatus.OK);
    }

    @GetMapping("/ignoreCase/{assignee}")
    public ResponseEntity<List<Task>> fetchAllTaskIgnoreCaseAssigneeNames(@PathVariable String assignee){
        List<Task> resAssignee =taskService.getTasksIgnoreCaseAssignee(assignee);
        return new ResponseEntity<>(resAssignee,HttpStatus.OK);
    }

    //SORTING
    @GetMapping("/sort/{fieldName}")
    public ResponseEntity<List<Task>> fetchAllTaskSortingAssigneeNames(@PathVariable String fieldName){
        List<Task> resSorting =taskService.getTasksSortingOnName(fieldName);
        return new ResponseEntity<>(resSorting,HttpStatus.OK);
    }

    @GetMapping("/pagination/{offSet}/{limit}")
    public ResponseEntity<Page<Task>> getPaginationByRange(@PathVariable int offSet,@PathVariable int limit){
        Page<Task> responsePagination = taskService.getTaskPagingRange(offSet, limit);
        return  new ResponseEntity<>(responsePagination,HttpStatus.OK);
    }

    @GetMapping("/sortWithPage/{fieldName}/{offSet}/{limit}")
    public ResponseEntity<Page<Task>> getTaskByPaginationAndSorting(@PathVariable String fieldName,@PathVariable int offSet, @PathVariable int limit){
       logger.info("TaskController :: getTaskByPaginationAdnSorting() request to db {},{},{}",fieldName,offSet,limit);
        Page<Task> responsePgtnndSortng = taskService.getTaskPaginationAndSorting(fieldName, offSet, limit);
       logger.debug("TaskController :: getTaskByPaginationAdnSorting() Response from db {}",responsePgtnndSortng);
        return new ResponseEntity<>(responsePgtnndSortng,HttpStatus.OK);
    }

}
