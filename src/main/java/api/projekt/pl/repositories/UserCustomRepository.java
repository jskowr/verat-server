package api.projekt.pl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import api.projekt.pl.models.UserCustom;

public interface UserCustomRepository  extends JpaRepository<UserCustom, Long>{
	UserCustom findByEmail(String email);
}
