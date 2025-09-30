package com.gregoryleblond.giftcardrestapi.controllers;

import com.gregoryleblond.giftcardrestapi.models.Company;
import com.gregoryleblond.giftcardrestapi.repositories.CompanyRepository;
import com.gregoryleblond.giftcardrestapi.services.CompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/company")
public class CompanyController {
    private final CompanyService service;
    private final CompanyRepository repository;
    Logger logger = LoggerFactory.getLogger(CompanyController.class);

    public CompanyController(CompanyService service,  CompanyRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @GetMapping("/all")
    public List<Company> getAllCompanies() {
        return repository.findAll();
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Company createCompany(@RequestBody Company company) {
        return service.createCompany(company);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompany(@PathVariable String id) {
        try {
            service.deleteCompanyById(id);
        } catch (Exception e) {
            logger.error("Exception while deleting company with id {}", id, e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
