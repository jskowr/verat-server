package api.projekt.pl.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api.projekt.pl.models.Milestone;
import api.projekt.pl.models.Notification;
import api.projekt.pl.repositories.NotificationRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationService {
	@Autowired
	private NotificationRepository notificationRepository;
	
	public List<Notification> findAllForUser(int user_id) {
        return notificationRepository.findAllForUser(user_id);
    }
	
	public void deleteById(int id) {
		notificationRepository.deleteById(id);
	}
	
	public void saveAndFlush(Notification board) {
		notificationRepository.save(board);
	}
	
	public Notification findOne(int id) {
        return notificationRepository.findById(id).orElse(null);
    }
}
