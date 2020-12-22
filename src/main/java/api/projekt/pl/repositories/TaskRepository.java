package api.projekt.pl.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import api.projekt.pl.models.Board;
import api.projekt.pl.models.ListOfTasks;
import api.projekt.pl.models.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
	@Query(value = "SELECT * FROM tasks WHERE list_of_tasks_id = :list_of_tasks_id", nativeQuery = true)
	public List<Task> findAllListTasks(@Param("list_of_tasks_id") int list_of_tasks_id);
	
	@Query(value = 
			"SELECT t.* FROM boards b"
			+ " LEFT JOIN lists_of_tasks l ON l.board_id = b.id"
			+ " LEFT JOIN tasks t ON t.list_of_tasks_id = l.id"
			+ " WHERE b.user_id = :user_id AND t.id IS NOT NULL"
			, nativeQuery = true)
	public List<Task> findAllUserTasks(@Param("user_id") int user_id);
	
	@Query(value = "Select t.* from tasks t LEFT JOIN milestones_tasks mt ON t.id=mt.tasks_id WHERE mt.milestone_id = :id ORDER BY mt.tasks_id DESC", nativeQuery = true)
    public List<Task> findAllMilestoneTasks(@Param("id") int id);
}