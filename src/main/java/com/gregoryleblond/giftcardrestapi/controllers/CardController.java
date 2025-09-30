package com.gregoryleblond.giftcardrestapi.controllers;

import com.gregoryleblond.giftcardrestapi.models.Card;
import com.gregoryleblond.giftcardrestapi.repositories.CardRepository;
import com.gregoryleblond.giftcardrestapi.services.CardService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import com.gregoryleblond.giftcardrestapi.dto.CardCreateRequest;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/card")
public class CardController {
    private final CardRepository repository;
    private final CardService service;

    public CardController(CardRepository repository, CardService service) {
        this.repository = repository;
        this.service = service;
    }

    @GetMapping("/all")
    public List<Card> getCards() {
        return repository.findAll();
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Card createCard(@RequestBody CardCreateRequest request) {
        try {
            return service.createCard(request);
        }
        catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCard(@PathVariable String id) {
        UUID cardId = UUID.fromString(id);
        service.deleteCard(cardId);
    }
}
