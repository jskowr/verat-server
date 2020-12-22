package api.projekt.pl.controllers;

import java.util.List;

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
import api.projekt.pl.models.TasksQueue;
import api.projekt.pl.models.UserCustom;
import api.projekt.pl.services.ListOfTasksService;
import api.projekt.pl.services.TaskService;
import api.projekt.pl.services.TasksQueueService;
import api.projekt.pl.services.UserServiceCustom;

@RestController
@RequestMapping("/tasks_queue")
public class TasksQueueController {
	@Autowired
	TasksQueueService tasksQueueService;
	
	@Autowired
	TaskService taskService;
	
	@Autowired
	UserServiceCustom userService;
	
	@GetMapping("/{id}")
	public TasksQueue listOfTasks(@PathVariable int id) {
		return tasksQueueService.findOne(id);
	}
	
	@GetMapping("/user")
	public List<TasksQueue> userQueue() {
		UserCustom user = userService.getCurrentUserDetails();
		return tasksQueueService.findForUser(user.getId());
	}
	
	@DeleteMapping("/{id}")
	public void deleteList(@PathVariable int id) {
		tasksQueueService.deleteById(id);
	}
	
	@PostMapping("/add")
	public void addList(@RequestBody TasksQueue tasksQueue){
		int id = tasksQueue.getTask().getId();
		if(tasksQueueService.findByTaskId(id) != null) {
			return;
		}
		UserCustom user = userService.getCurrentUserDetails();
		tasksQueue.setUser(user);
		tasksQueueService.saveAndFlush(tasksQueue);
	}
	
	@PutMapping("/update")
	public void updateList(@RequestBody TasksQueue tasksQueue){
		UserCustom user = userService.getCurrentUserDetails();
		tasksQueue.setUser(user);
		tasksQueueService.saveAndFlush(tasksQueue);
	}
}
