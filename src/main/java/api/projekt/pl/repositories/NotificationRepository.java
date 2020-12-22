package api.projekt.pl.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import api.projekt.pl.models.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {
    @Query("Select e from Notification e WHERE e.user_id = :user_id ORDER BY e.id DESC")
    public List<Notification> findAllForUser(@Param("user_id") int user_id);
}
