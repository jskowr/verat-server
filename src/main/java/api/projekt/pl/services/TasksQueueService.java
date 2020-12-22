package api.projekt.pl.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api.projekt.pl.dao.ListOfTasksDAO;
import api.projekt.pl.models.ListOfTasks;
import api.projekt.pl.models.TasksQueue;
import api.projekt.pl.repositories.ListOfTasksRepository;
import api.projekt.pl.repositories.TasksQueueRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TasksQueueService {

	@Autowired
	private TasksQueueRepository tasksQueueRepository;
	
	public List<TasksQueue> findAll() {
        return tasksQueueRepository.findAll();
    }
	
	public TasksQueue findOne(int id) {
        return tasksQueueRepository.findById(id).orElse(null);
    }
	
	public TasksQueue findByTaskId(int id) {
        return tasksQueueRepository.findByTaskId(id);
    }
	
	public void deleteById(int id) {
		tasksQueueRepository.deleteById(id);
	}
	
	public void saveAndFlush(TasksQueue tasksQueue) {
		tasksQueueRepository.save(tasksQueue);
	}
	
	public List<TasksQueue> findForUser(int user_id){
		return tasksQueueRepository.findForUser(user_id);
	}
	
}
