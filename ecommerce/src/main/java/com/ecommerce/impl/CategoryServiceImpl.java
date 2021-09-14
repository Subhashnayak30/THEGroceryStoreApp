package com.ecommerce.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.dao.CategoryDao;
import com.ecommerce.dao.UserDao;
import com.ecommerce.modal.Category;
import com.ecommerce.modal.User;
import com.ecommerce.service.CategoryService;
@Service
@Transactional
@Component

public class CategoryServiceImpl implements CategoryService {
	
	private static Logger log = Logger.getLogger(CategoryServiceImpl.class);

	@Autowired
	private CategoryDao categoryDao;

	@Autowired
	private UserDao userDao;
	
	@Override
	public Category addCategoryToUser(Category category, long idUser) {
		User user = userDao.findById(idUser).orElse(null);
		user.addCategoryToUser(category);
		log.info("Adding the category to user");
		return categoryDao.save(category);
	}

	@Override
	public Category editCategory(Category category, long id) {
		Category existsCategory = categoryDao.findById(id).orElse(null);
		existsCategory.setName(category.getName());
		log.info("Editing the category");
		return categoryDao.save(existsCategory);
	}

	@Override
	public Category findCategoryById(long id) {
		return categoryDao.findById(id).orElse(null);
	}

	@Override
	public void deleteCategory(long id) {
		categoryDao.deleteById(id);
	}

	@Override
	public List<Category> findAllCategories() {
		return categoryDao.findAll();
	}

	@Override
	public List<Category> findCategoriesForUser(long id) {
		User user = userDao.findById(id).orElse(null);
		return user.getCategories();
	}

}
