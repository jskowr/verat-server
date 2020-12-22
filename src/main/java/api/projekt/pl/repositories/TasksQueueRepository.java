package api.projekt.pl.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import api.projekt.pl.models.Board;
import api.projekt.pl.models.TasksQueue;

@Repository
public interface TasksQueueRepository extends JpaRepository<TasksQueue, Integer> {
    @Query(value="SELECT * FROM tasks_queue q WHERE q.user_id = :user_id ORDER BY q.id ASC", nativeQuery=true)
    public List<TasksQueue> findForUser(@Param("user_id") int user_id);
    
    @Query(value="SELECT * FROM tasks_queue q WHERE q.task_id = :task_id ORDER BY q.id DESC LIMIT 1", nativeQuery=true)
    public TasksQueue findByTaskId(@Param("task_id") int task_id);
}
