package api.projekt.pl.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import api.projekt.pl.models.Priority;

@Repository
public interface PriorityRepository extends JpaRepository<Priority, Integer> {
}
