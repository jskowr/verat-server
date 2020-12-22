package api.projekt.pl.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import api.projekt.pl.models.Board;
import api.projekt.pl.models.Milestone;
import api.projekt.pl.models.Task;

@Repository
public interface MilestoneRepository extends JpaRepository<Milestone, Integer> {
    public List<Board> findAllByOrderByIdDesc();
    @Query("Select e from Milestone e WHERE e.user_id = :user_id ORDER BY e.id DESC")
    public List<Milestone> findAllForUser(@Param("user_id") int user_id);
}
