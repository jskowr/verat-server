package api.projekt.pl.controllers;

import java.util.List;

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
import api.projekt.pl.models.Priority;
import api.projekt.pl.models.UserCustom;
import api.projekt.pl.services.BoardService;
import api.projekt.pl.services.ListOfTasksService;
import api.projekt.pl.services.PriorityService;
import api.projekt.pl.services.UserServiceCustom;

@RestController
@RequestMapping("/priorities")
public class PriorityController {
	@Autowired
	PriorityService priorityService;
	
	@GetMapping("/all")
	public ResponseEntity<List<Priority>> getAllPriorities() {
		return new ResponseEntity<List<Priority>>(priorityService.findAll(), HttpStatus.OK);
	}
}
