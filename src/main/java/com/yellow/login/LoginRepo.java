package com.yellow.login;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
//The two parameters are <Object Data Type, Primary Key Data Type>
public interface LoginRepo extends CrudRepository<User,String> {
	
//	@Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM User p WHERE p.email_id = :email and p.password = :password")
//    boolean authentication(String username,String password);
	User findByEmailId(String emailId);
}
