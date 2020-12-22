package api.projekt.pl;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import api.projekt.pl.models.Board;
import api.projekt.pl.models.UserCustom;
import api.projekt.pl.repositories.BoardRepository;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BoardRepositoryTest {
	  @Autowired
	  private TestEntityManager entityManager;

	  @Autowired
	  BoardRepository boardRepository;
	  
	  @Test
	  public void createNewBoardTest() {
	      Board newBoard = new Board();
	      newBoard.setName("nowa tablica");
	      newBoard.setDescription("testowanie dodawania tablicy");
	      
	      entityManager.persist(newBoard);
	      entityManager.flush();
	   
	      Board board = boardRepository.findByName("nowa tablica");
	   
	      assertThat(board.getName())
	        .isEqualTo("nowa tablica");
	  }
}
