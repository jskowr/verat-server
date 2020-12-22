package api.projekt.pl.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api.projekt.pl.dao.ListOfTasksDAO;
import api.projekt.pl.models.Board;
import api.projekt.pl.models.ListOfTasks;
import api.projekt.pl.repositories.ListOfTasksRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ListOfTasksService {
	@Autowired
	private ListOfTasksRepository listOfTasksRepository;
	
	@Autowired
	private ListOfTasksDAO listOfTasksDAO;
	
	public List<ListOfTasks> findAll() {
        return listOfTasksRepository.findAll();
    }
	
	public ListOfTasks findOne(int id) {
        return listOfTasksRepository.findById(id).orElse(null);
    }
	
	public void deleteById(int id) {
		listOfTasksRepository.deleteById(id);
	}
	
	public void saveAndFlush(ListOfTasks listOfTasks) {
		listOfTasksRepository.save(listOfTasks);
	}
	
	public List<ListOfTasks> findAllBoardLists(int board_id){
		return listOfTasksRepository.findAllBoardLists(board_id);
	}

}
