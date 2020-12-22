package api.projekt.pl.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api.projekt.pl.models.Board;
import api.projekt.pl.repositories.BoardRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
	@Autowired
	private BoardRepository boardRespository;
	
	public List<Board> findAll() {
        return boardRespository.findAllByOrderByIdDesc();
    }
	
	public List<Board> findAllForUser(int user_id) {
        return boardRespository.findAllForUser(user_id);
    }
	
	public Board findOne(int id) {
        return boardRespository.findById(id).orElse(null);
    }
	
	public void deleteById(int id) {
		boardRespository.deleteById(id);
	}
	
	public void saveAndFlush(Board board) {
		boardRespository.save(board);
	}
}
