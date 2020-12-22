package api.projekt.pl.services;
import org.springframework.security.core.userdetails.UserDetailsService;

import api.projekt.pl.dto.UserCustomRegistrationDto;
import api.projekt.pl.models.UserCustom;

public interface UserServiceCustom extends UserDetailsService {
	 UserCustom findByEmail(String email);
	 UserCustom save(UserCustomRegistrationDto registration);
	 UserCustom getCurrentUserDetails();
}
