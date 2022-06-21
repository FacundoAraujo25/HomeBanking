package com.mindhub.HomeBanking;

import com.mindhub.HomeBanking.toolsBox.utils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.Matchers.*;

@SpringBootTest
public class UtilsTests {

    @Test

    public void cardNumberIsCreated(){

        String cardNumber = utils.getCardRandomNumber(1000000000000000L,9999999999999999L);

        assertThat(cardNumber,is(not(emptyOrNullString())));

    }

    @Test

    public void cardCvvIsCreated(){

        String cardNumber = utils.getCardRandomNumber(100,999);

        assertThat(cardNumber,is(not(emptyOrNullString())));

    }

}
