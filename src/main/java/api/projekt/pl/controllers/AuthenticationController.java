package api.projekt.pl.controllers;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import api.projekt.pl.dto.UserCustomRegistrationDto;
import api.projekt.pl.models.UserCustom;
import api.projekt.pl.services.UserServiceCustom;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Controller
@RequestMapping("/auth")
public class AuthenticationController {

	@Autowired
    private UserServiceCustom userService;
	
	@Autowired
    private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody UserCustom user){
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
		Authentication authentication = this.authenticationManager.authenticate(authToken);
		if(authentication == null) {
			return new ResponseEntity<String>("Nieprawidłowy email lub hasło.", HttpStatus.BAD_REQUEST);
		}
		
		String token = getJWTToken(user.getEmail());
		return new ResponseEntity<String>(token, HttpStatus.OK);
	}
	
	private String getJWTToken(String username) {
		String secretKey = "mySecretKey";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList("ROLE_USER");
		
		String token = Jwts
				.builder()
				.setId("softtekJWT")
				.setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 3600 * 24 * 7))
				.signWith(SignatureAlgorithm.HS512,
						secretKey.getBytes()).compact();

		return token;
	}
	
	@GetMapping("/refreshtoken")
	public  ResponseEntity<String> refreshToken() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = "";
		if (principal instanceof UserDetails) {
		username = ((UserDetails)principal).getUsername();
		} else {
		username = principal.toString();
		}
		
		String token = this.getJWTToken(username);
		
		return new ResponseEntity<String>(token, HttpStatus.OK);
	}
	
	
}
