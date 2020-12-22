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
import api.projekt.pl.models.UserCustom;
import api.projekt.pl.services.ListOfTasksService;
import api.projekt.pl.services.TaskService;

@RestController
@RequestMapping("/lists")
public class ListOfTasksController {
	@Autowired
	ListOfTasksService listOfTasksService;
	
	@Autowired
	TaskService taskService;
	
	@GetMapping("/all/board/{id}")
	public List<ListOfTasks> getAllListsForBoard(@PathVariable int id){
		return listOfTasksService.findAllBoardLists(id);
	}
	
	@GetMapping("/{id}")
	public ListOfTasks listOfTasks(@PathVariable int id) {
		return listOfTasksService.findOne(id);
	}
	
	@DeleteMapping("/{id}")
	public void deleteList(@PathVariable int id) {
		listOfTasksService.deleteById(id);
	}
	
	@PostMapping("/add")
	public void addList(@RequestBody ListOfTasks listOfTasks){
		listOfTasksService.saveAndFlush(listOfTasks);
	}
	
	@GetMapping("/{id}/tasks")
	public ResponseEntity<List<Task>> getAllBoardLists(@PathVariable int id) {
		return new ResponseEntity<List<Task>>(taskService.findAllListTasks(id), HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public void updateList(@RequestBody ListOfTasks listOfTasks){
		listOfTasksService.saveAndFlush(listOfTasks);
	}
}
