package com.gregoryleblond.giftcardrestapi.services;

import com.gregoryleblond.giftcardrestapi.dto.CardCreateRequest;
import com.gregoryleblond.giftcardrestapi.models.Card;
import com.gregoryleblond.giftcardrestapi.models.Company;
import com.gregoryleblond.giftcardrestapi.models.User;
import com.gregoryleblond.giftcardrestapi.repositories.CardRepository;
import com.gregoryleblond.giftcardrestapi.repositories.CompanyRepository;
import com.gregoryleblond.giftcardrestapi.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CardService {
    Logger logger = LoggerFactory.getLogger(CardService.class);
    private final CardRepository repository;
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;

    public CardService(CardRepository repository, UserRepository userRepository, CompanyRepository companyRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
    }

    public Card createCard(CardCreateRequest request) {
        try {
            User user = userRepository.findById(request.userId());
            Company company = companyRepository.findById(request.companyId());

            Card card = new Card();
            card.setUser(user);
            card.setCompany(company);
            card.setAmount(request.amount());
            card.setExpireAt(request.expireAt());

            return repository.save(card);
        }
        catch(Exception e){
            logger.error("Error while saving card");
            logger.error(e.getMessage());
            return null;
        }
    }

    public void deleteCard(UUID id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            logger.error("Could not delete card with id {}", id);
            logger.error(e.getMessage());
        }
    }
}
