package com.greatersum.rental;

class Movie {
    private final String title;
    private final String code;

    Movie(String title, String code) {
        this.title = title;
        this.code = code;
    }

    String getTitle() {
        return title;
    }

    String getCode() {
        return code;
    }
}
