package api.projekt.pl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import api.projekt.pl.models.RoleCustom;
import api.projekt.pl.models.UserCustom;
import api.projekt.pl.services.SecurityServiceImpl;
import api.projekt.pl.services.UserServiceCustom;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SecurityServiceTest {

	@Autowired
	private TestEntityManager entityManager;
	@Autowired
	private SecurityServiceImpl securityService;
	private UserCustom user;
	private RoleCustom roleCustom;
	@Autowired
	private UserServiceCustom userService;
	private String userName;
	
	@Before
	public void setupSaveNewUser() {
		userName = "jan.kowalski@wp.pl";
		roleCustom = new RoleCustom();
		roleCustom.setName("ROLE_USER");
		List<RoleCustom> roles = new ArrayList<RoleCustom>();
		roles.add(roleCustom);
		
		user = new UserCustom();
		user.setEmail(userName);
		user.setPassword("test1234");
		user.setFirstName("Jan");
		user.setLastName("Kowalski");
		user.setRoles(roles);
		entityManager.persist(user);
		entityManager.flush();
	}
	
	@Test
	public void testIfAutoLoggedUserIsRetrievedBySecurityService() {
		securityService.authenticateUser(userName);
		Assert.assertEquals(userName, userService.getCurrentUserDetails().getEmail());
	}
}
