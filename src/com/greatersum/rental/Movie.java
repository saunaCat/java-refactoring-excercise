package com.greatersum.rental;

class Movie {
    private final String title;
    private final PriceCode code;

    Movie(String title, PriceCode code) {
        this.title = title;
        this.code = code;
    }

    String getTitle() {
        return title;
    }

    PriceCode getCode() {
        return code;
    }
}
