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

import api.projekt.pl.models.Notification;
import api.projekt.pl.models.UserCustom;
import api.projekt.pl.services.NotificationService;
import api.projekt.pl.services.UserServiceCustom;

@RestController
@RequestMapping("/notifications")
public class NotificationController {
	@Autowired
	UserServiceCustom userService;
	
	@Autowired
	NotificationService notificationService;
	
	@GetMapping("/all")
	public ResponseEntity<List<Notification>> getAllUserNotifications() {
		UserCustom user = userService.getCurrentUserDetails();
		return new ResponseEntity<List<Notification>>(notificationService.findAllForUser(user.getId()), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public Notification getNotification(@PathVariable int id) {
		return notificationService.findOne(id);
	}
	
	@DeleteMapping("/{id}")
	public void deleteNotification(@PathVariable int id) {
		notificationService.deleteById(id);
	}
	
	@PostMapping("/add")
	public void addNotification(@RequestBody Notification notification){
		UserCustom user = userService.getCurrentUserDetails();
		notification.setUser(user);
		notificationService.saveAndFlush(notification);
	}
	
	@PutMapping("/update")
	public void updateNotification(@RequestBody Notification notification){
		notificationService.saveAndFlush(notification);
	}
}
