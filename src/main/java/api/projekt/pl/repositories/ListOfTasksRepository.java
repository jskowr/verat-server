package api.projekt.pl.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import api.projekt.pl.models.Board;
import api.projekt.pl.models.ListOfTasks;;

@Repository
public interface ListOfTasksRepository extends JpaRepository<ListOfTasks, Integer> {
	@Query(value = "SELECT * FROM lists_of_tasks WHERE board_id = :board_id", nativeQuery = true)
	public List<ListOfTasks> findAllBoardLists(@Param("board_id") int board_id);
}