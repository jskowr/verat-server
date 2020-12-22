package api.projekt.pl.repositories;

import api.projekt.pl.models.Board;
import api.projekt.pl.models.RoleCustom;
import api.projekt.pl.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleCustom, Long> {
}