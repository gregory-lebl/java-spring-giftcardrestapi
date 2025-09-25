package com.gregoryleblond.giftcardrestapi.repositories;

import com.gregoryleblond.giftcardrestapi.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    Company findById(UUID id);
    Company findBySiren(String siren);
    @Transactional
    void deleteById(UUID id);
}
