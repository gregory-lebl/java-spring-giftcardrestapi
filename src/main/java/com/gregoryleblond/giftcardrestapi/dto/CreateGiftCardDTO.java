package com.gregoryleblond.giftcardrestapi.dto;

import java.util.Date;
import java.util.UUID;

public class CreateGiftCardDTO {
    private UUID user;
    private UUID company;
    private Integer amount;
    private Date expireAt;

}
