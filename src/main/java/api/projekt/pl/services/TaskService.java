package api.projekt.pl.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import api.projekt.pl.dao.ListOfTasksDAO;
import api.projekt.pl.models.ListOfTasks;
import api.projekt.pl.models.Task;
import api.projekt.pl.repositories.ListOfTasksRepository;
import api.projekt.pl.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskService {
	@Autowired
	private TaskRepository taskRepository;
	
	public List<Task> findAll() {
        return taskRepository.findAll();
    }
	
	public Task findOne(int id) {
        return taskRepository.findById(id).orElse(null);
    }
	
	public void deleteById(int id) {
		taskRepository.deleteById(id);
	}
	
	public void saveAndFlush(Task task) {
		taskRepository.save(task);
	}
	
	public List<Task> findAllListTasks(int list_id){
		return taskRepository.findAllListTasks(list_id);
	}
	
	public List<Task> findAllUserTasks(int user_id){
		return taskRepository.findAllUserTasks(user_id);
	}
}
