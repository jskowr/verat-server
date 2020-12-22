package api.projekt.pl.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api.projekt.pl.models.Board;
import api.projekt.pl.models.Milestone;
import api.projekt.pl.models.Task;
import api.projekt.pl.repositories.BoardRepository;
import api.projekt.pl.repositories.MilestoneRepository;
import api.projekt.pl.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MilestoneService {
	@Autowired
	private MilestoneRepository milestoneRepository;
	
	@Autowired
	private TaskRepository taskRepository;
	
	public List<Milestone> findAllForUser(int user_id) {
        return milestoneRepository.findAllForUser(user_id);
    }
	
	public Milestone findOne(int id) {
        return milestoneRepository.findById(id).orElse(null);
    }
	
	public void deleteById(int id) {
		milestoneRepository.deleteById(id);
	}
	
	public void saveAndFlush(Milestone milestone) {
		milestoneRepository.save(milestone);
	}
	
	public List<Task> findAllMilestoneTasks(int id){
		return taskRepository.findAllMilestoneTasks(id);
	}
}
