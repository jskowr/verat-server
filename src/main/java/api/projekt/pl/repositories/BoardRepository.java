package api.projekt.pl.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import api.projekt.pl.models.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {
    public List<Board> findAllByOrderByIdDesc();
    @Query("Select e from Board e WHERE e.user_id = :user_id ORDER BY e.id DESC")
    public List<Board> findAllForUser(@Param("user_id") int user_id);
    
    Board findByName(String name);
}
