package controller;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.MoviesList;

public class MoviesListHelper {

	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("MovieList");

	public void insertMovie(MoviesList li) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(li);
		em.getTransaction().commit();
		em.close();
//
	}
	
	public void deleteMovie(MoviesList toRemove) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<MoviesList> typedQuery = em.createQuery("", MoviesList.class);
		typedQuery.setParameter("selectedTitle", toRemove.getTitle());
		typedQuery.setParameter("selectedRunTime", toRemove.getRunTime());
		typedQuery.setMaxResults(1);
		MoviesList result = typedQuery.getSingleResult();
		em.remove(result);
		em.remove(toRemove);
		em.getTransaction().commit();
		em.close();
	}
}

