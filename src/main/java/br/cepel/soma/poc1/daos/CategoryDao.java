package br.cepel.soma.poc1.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.cepel.soma.poc1.models.Category;
import br.cepel.soma.poc1.models.PaginatedList;

@Repository
public class CategoryDao {

	@PersistenceContext
	private EntityManager manager;

	public List<Category> all() {
		return manager.createQuery("select c from Category c", Category.class).getResultList();
	}

	public void save(Category category) {
		manager.persist(category);
	}

	public Category findById(Integer id) {
		return manager.find(Category.class, id);
	}

	public void remove(Category category) {
		manager.remove(category);
	}

	public void update(Category category) {
		manager.merge(category);
	}

	public PaginatedList paginated(int page, int max) {
		return new PaginatorQueryHelper().list(manager, Category.class, page, max);
	}

}
