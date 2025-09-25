package com.gregoryleblond.giftcardrestapi.services;

import com.gregoryleblond.giftcardrestapi.models.Company;
import com.gregoryleblond.giftcardrestapi.repositories.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CompanyService {
    private final CompanyRepository repository;
    public CompanyService(CompanyRepository repository) {
        this.repository = repository;
    }

    public Company createCompany(Company company) {
        return repository.save(company);
    }

    public void deleteCompanyById(String id) {
        UUID companyId = UUID.fromString(id);
        Company company = repository.findById(companyId);
        repository.deleteById(company.getId());
    }
}
