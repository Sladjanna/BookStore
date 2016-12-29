package app.service;

import org.springframework.data.domain.Page;

import app.model.User;

public interface UserService {
	User findOne(Long id);
	Page<User> findAll(int page);
	User save(User user);
	void delete(Long id); 
}
