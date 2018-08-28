package com.greatersum.rental;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Objects;

class RentalInfo {

    String statement(Customer customer) {
        HashMap<String, Movie> movies = new HashMap<>();
        movies.put("F001", new Movie("Ran", "regular"));
        movies.put("F002", new Movie("Trois Couleurs: Bleu", "regular"));
        movies.put("F003", new Movie("Cars 2", "childrens"));
        movies.put("F004", new Movie("Latest Hit Release", "new"));

        BigDecimal totalAmount = BigDecimal.valueOf(0);
        int frequentRenterPoints = 0;
        StringBuilder resultBuilder = new StringBuilder("Rental Record for " + customer.getName() + "\n");
        for (MovieRental r : customer.getRentals()) {
            Movie movie = movies.get(r.getMovieId());
            BigDecimal thisAmount = BigDecimal.valueOf(0);

            // determine amount for each movie
            switch (movie.getCode()) {
                case "regular":
                    thisAmount = BigDecimal.valueOf(2);
                    if (r.getDays() > 2) {
                        thisAmount = BigDecimal.valueOf((r.getDays() - 2) * 1.5).add(thisAmount);
                    }
                    break;
                case "new":
                    thisAmount = BigDecimal.valueOf(r.getDays() * 3);
                    break;
                case "childrens":
                    thisAmount = BigDecimal.valueOf(1.5);
                    if (r.getDays() > 3) {
                        thisAmount = BigDecimal.valueOf((r.getDays() - 3) * 1.5).add(thisAmount);
                    }
                    break;
            }

            //add frequent renter points
            frequentRenterPoints++;
            // add bonus for a two day new release rental
            if (Objects.equals(movie.getCode(), "new") && r.getDays() > 2) frequentRenterPoints++;

            //print figures for this rental
            resultBuilder
                    .append("\t")
                    .append(movie.getTitle())
                    .append("\t")
                    .append(thisAmount)
                    .append("\n");

            totalAmount = totalAmount.add(thisAmount);
        }
        String result = resultBuilder.toString();
        // add footer lines
        result += "Amount owed is " + totalAmount + "\n";
        result += "You earned " + frequentRenterPoints + " frequent renter points\n";

        return result;
    }
}
