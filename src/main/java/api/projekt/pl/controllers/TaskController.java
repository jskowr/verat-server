package api.projekt.pl.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.projekt.pl.models.Board;
import api.projekt.pl.models.ListOfTasks;
import api.projekt.pl.models.Task;
import api.projekt.pl.models.UserCustom;
import api.projekt.pl.services.BoardService;
import api.projekt.pl.services.ListOfTasksService;
import api.projekt.pl.services.TaskService;
import api.projekt.pl.services.UserServiceCustom;

@RestController
@RequestMapping("/tasks")
public class TaskController {
	@Autowired
	TaskService taskService;
	
	@Autowired
	UserServiceCustom userService;
	
	@GetMapping("/all")
	public List<Task> getAllTasks(){
		UserCustom user = userService.getCurrentUserDetails();
		return taskService.findAllUserTasks(user.getId());
	}
	
	@GetMapping("/{id}")
	public Task task(@PathVariable int id) {
		return taskService.findOne(id);
	}
	
	@DeleteMapping("/{id}")
	public void deleteTask(@PathVariable int id) {
		taskService.deleteById(id);
	}
	
	@PostMapping("/add")
	public void addTask(@RequestBody Task task){
		taskService.saveAndFlush(task);
	}
	
	 @PutMapping("/move")
	 public void moveTask(@RequestBody Task task) {
		 Task tempTask = taskService.findOne(task.getId());
	     tempTask.setListOfTasks(task.getListOfTasks());
	     taskService.saveAndFlush(tempTask);
	  }
	
	@PutMapping("/update")
	public void updateTask(@RequestBody Task task) {
		Task tempTask = taskService.findOne(task.getId());
		tempTask.setPriority(task.getPriority());
		taskService.saveAndFlush(tempTask);
	}
}
