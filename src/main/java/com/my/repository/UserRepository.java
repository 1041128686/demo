package com.my.repository;

import com.my.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer > {

    User findUserByUsernameAndPassword(String username,String password);

    User findUserByUsername(String username);
}
