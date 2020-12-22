package api.projekt.pl;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import api.projekt.pl.models.Milestone;
import api.projekt.pl.models.RoleCustom;
import api.projekt.pl.models.UserCustom;
import api.projekt.pl.repositories.MilestoneRepository;
import api.projekt.pl.repositories.UserCustomRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MilestoneRepositoryTest {
	@Autowired
	private TestEntityManager entityManager;
	@Autowired
	private MilestoneRepository milestoneRepository;
	@Autowired
	private UserCustomRepository userCustomRepository;
	private UserCustom user;

	@Before
	public void setupUserWithMilestones() {
		user = new UserCustom();
		user.setEmail("jan.kowalski@wp.pl");
		user.setPassword("test1234");
		user.setFirstName("Jan");
		user.setLastName("Kowalski");
		entityManager.persist(user);
		entityManager.flush();

		Milestone tempMilestone = new Milestone();
		tempMilestone.setName("przykładowy kamień milowy");
		tempMilestone.setDescription("testowanie dodawania kamieni milowych");
		tempMilestone.setUser(user);
		entityManager.persist(tempMilestone);
		entityManager.flush();

		Milestone tempMilestone2 = new Milestone();
		tempMilestone2.setName("inny przykładowy kamień milowy");
		tempMilestone2.setDescription("opis");
		tempMilestone2.setUser(user);
		entityManager.persist(tempMilestone2);
		entityManager.flush();
	}

	@Test
	public void testIfCreatedUserHasTwoMilestones() {
		List<Milestone> milestones = milestoneRepository.findAllForUser(user.getId());
		Assert.assertEquals(2, milestones.size());
	}
}
