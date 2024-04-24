package com.jtspringproject.JtSpringProject.dao;

import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jtspringproject.JtSpringProject.models.Category;

@Repository
public class categoryDao{
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Transactional
	public Category addCategory(String name) {
		Category category = new Category();
		category.setName(name);
		this.sessionFactory.getCurrentSession().save(category);
		return category;
	}

	@Transactional
	public List<Category> getCategories() {
		//        return this.sessionFactory.getCurrentSession().createQuery("from Category", Category.class).list();
		return this.sessionFactory.getCurrentSession().createQuery("from CATEGORY").list();
	}

	@Transactional
	public Boolean deleteCategory(int id) {

		Session session = this.sessionFactory.getCurrentSession();
		Category category = session.get(Category.class, id); // Попытка загрузки категории по идентификатору

		if (category != null) { // Если категория существует, то производим удаление
			session.delete(category);
			return true;
		} else {
			return false; // Если категория не существует, возвращаем false
		}
	}

	@Transactional
	public Category updateCategory(int id, String name) {
		Category category = this.sessionFactory.getCurrentSession().get(Category.class, id);
		//         if (category != null) {
		category.setName(name);

		this.sessionFactory.getCurrentSession().update(category);
		return category;
	}

	@Transactional
	public Category getCategory(int id) {
		return this.sessionFactory.getCurrentSession().get(Category.class,id);
	}
}
