package com.gregoryleblond.giftcardrestapi.repositories;

import com.gregoryleblond.giftcardrestapi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByLastName(String lastName);

    User findById(UUID id);
}
