package com.mindhub.HomeBanking.toolsBox;

public class utils {

    public static int getRandomAccountNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static String getCardRandomNumber (long min, long max) {


       long cardNumber = (long) ((Math.random() * (max - min)) + min);

       String NumberOfCard = Long.toString(cardNumber);

       return NumberOfCard;

    }
}
