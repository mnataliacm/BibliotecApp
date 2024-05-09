package com.ncm.library.repository;

import java.util.Optional;

// import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ncm.library.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>
// public interface UserRepository extends JpaRepository<User, Integer> 
{

    Optional<User> findByName(String np);
    boolean existsByName(String np);

}