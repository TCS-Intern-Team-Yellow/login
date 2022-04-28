package com.yellow.login;

import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
//The two parameters are <Object Data Type, Primary Key Data Type>
public interface UserRepo extends CrudRepository<User,String> {
	User findByEmailId(String emailId);
}
