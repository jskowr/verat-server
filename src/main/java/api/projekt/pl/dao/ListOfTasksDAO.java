package api.projekt.pl.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import api.projekt.pl.models.ListOfTasks;

@Repository
public class ListOfTasksDAO {
	@PersistenceUnit
	private EntityManagerFactory emf;
	
	 public List<ListOfTasks> findAllBoardLists(int board_id) {
	      EntityManager em = emf.createEntityManager();
	      TypedQuery<ListOfTasks> query = em.createQuery("SELECT x FROM ListOfTasks x WHERE x.board_id = :board_id", ListOfTasks.class);
	      query.setParameter("board_id", board_id);
	      List<ListOfTasks> lists = query.getResultList();
	      return lists;
	 }
}
