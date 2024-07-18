package com.icwd.serviceregistry.scm20.Repository;

import com.icwd.serviceregistry.scm20.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,String>
{
   Optional<User> findByEmail(String email);
   Optional<User> findByEmailAndPassword(String email,String password);

   Optional<User> findByEmailToken(String id);
}
