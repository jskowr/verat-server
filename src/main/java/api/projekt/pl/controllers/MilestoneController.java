package api.projekt.pl.controllers;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
import api.projekt.pl.models.Milestone;
import api.projekt.pl.models.Task;
import api.projekt.pl.models.UserCustom;
import api.projekt.pl.services.BoardService;
import api.projekt.pl.services.ListOfTasksService;
import api.projekt.pl.services.MilestoneService;
import api.projekt.pl.services.TaskService;
import api.projekt.pl.services.UserServiceCustom;

@RestController
@RequestMapping("/milestones")
public class MilestoneController {
	@Autowired
	UserServiceCustom userService;
	
	@Autowired
	MilestoneService milestoneService;
	
	@Autowired
	TaskService taskService;
	
	@GetMapping("/all")
	public ResponseEntity<List<Milestone>> getAllUserMilestones() {
		UserCustom user = userService.getCurrentUserDetails();
		return new ResponseEntity<List<Milestone>>(milestoneService.findAllForUser(user.getId()), HttpStatus.OK);
	}
	
	
	@GetMapping("/{id}/tasks")
	public ResponseEntity<List<Task>> getAllMilestoneTasks(@PathVariable int id) {
		return new ResponseEntity<List<Task>>(milestoneService.findAllMilestoneTasks(id), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public Milestone getBoard(@PathVariable int id) {
		return milestoneService.findOne(id);
	}
	
	@DeleteMapping("/{id}")
	public void deleteBoard(@PathVariable int id) {
		milestoneService.deleteById(id);
	}
	
	@PostMapping("/add")
	public void addBoard(@RequestBody Milestone milestone){
		UserCustom user = userService.getCurrentUserDetails();
		milestone.setUser(user);
		milestoneService.saveAndFlush(milestone);
	}
	
	@PostMapping("/add/{id}/task/{taskid}")
	public void addTaskToMilestone(@PathVariable int id, @PathVariable int taskid){
		UserCustom user = userService.getCurrentUserDetails();
		 Milestone milestone = milestoneService.findOne(id);
		 Set<Task> tasks = milestone.getTasks();
		 Task tempTask = taskService.findOne(taskid);
		 tasks.add(tempTask);
		 milestone.setTasks(tasks);
		milestoneService.saveAndFlush(milestone);
	}
	
	@PutMapping("/update")
	public void updateBoard(@RequestBody Milestone milestone){
		UserCustom user = userService.getCurrentUserDetails();
		milestone.setUser(user);
		milestoneService.saveAndFlush(milestone);
	}
}
