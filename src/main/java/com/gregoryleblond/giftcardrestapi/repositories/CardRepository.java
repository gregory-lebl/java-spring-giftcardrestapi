package com.gregoryleblond.giftcardrestapi.repositories;

import com.gregoryleblond.giftcardrestapi.models.Card;
import com.gregoryleblond.giftcardrestapi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CardRepository extends JpaRepository<Card, UUID> {
    Card findCardById(UUID id);
    List<Card> findByUserId(User userId);
}
