package api.projekt.pl.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api.projekt.pl.models.Board;
import api.projekt.pl.models.Priority;
import api.projekt.pl.repositories.BoardRepository;
import api.projekt.pl.repositories.PriorityRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PriorityService {
	@Autowired
	private PriorityRepository priorityRepository;
	
	public List<Priority> findAll() {
        return priorityRepository.findAll();
    }
}
