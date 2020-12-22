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
import api.projekt.pl.models.UserCustom;
import api.projekt.pl.services.BoardService;
import api.projekt.pl.services.ListOfTasksService;
import api.projekt.pl.services.UserServiceCustom;

@RestController
@RequestMapping("/boards")
public class BoardController {
	@Autowired
	BoardService boardService;
	
	@Autowired
	ListOfTasksService listOfTasksService;
	
	@Autowired
	UserServiceCustom userService;
	
	@GetMapping("/all")
	public ResponseEntity<List<Board>> getAllUserBoards() {
		UserCustom user = userService.getCurrentUserDetails();
		return new ResponseEntity<List<Board>>(boardService.findAllForUser(user.getId()), HttpStatus.OK);
	}
	
	@GetMapping("/{id}/lists")
	public ResponseEntity<List<ListOfTasks>> getAllBoardLists(@PathVariable int id) {
		return new ResponseEntity<List<ListOfTasks>>(listOfTasksService.findAllBoardLists(id), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public Board getBoard(@PathVariable int id) {
		return boardService.findOne(id);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteBoard(@PathVariable int id) {
		boardService.deleteById(id);
		return new ResponseEntity<String>("Tablica z id="+id+" została usunięta", HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public void addBoard(@RequestBody Board board){
		UserCustom user = userService.getCurrentUserDetails();
		board.setUser(user);
		boardService.saveAndFlush(board);
	}
	
	@PutMapping("/update")
	public void updateBoard(@RequestBody Board board){
		boardService.saveAndFlush(board);
	}
}
