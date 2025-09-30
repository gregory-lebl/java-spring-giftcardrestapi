package com.gregoryleblond.giftcardrestapi.dto;


import java.util.Date;
import java.util.UUID;

public record CardCreateRequest(
        UUID userId,
        UUID companyId,
        int amount,
        Date expireAt
) {}

