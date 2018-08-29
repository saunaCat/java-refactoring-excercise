package com.greatersum.rental;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Objects;

public class RentalInfo {
  private HashMap<String, Movie> movies = getMovies();

  public String statement(Customer customer) {

    BigDecimal totalAmount = BigDecimal.valueOf(0);
    int frequentRenterPoints = 0;
    StringBuilder resultBuilder = new StringBuilder("Rental Record for " + customer.getName() + "\n");

    for (MovieRental rental : customer.getRentals()) {
      Movie movie = movies.get(rental.getMovieId());

      // determine amount for each movie
      BigDecimal thisAmount = determineRentalPriceForCategory(movie.getCode(), rental);

      //add frequent renter points
      frequentRenterPoints = getFrequentRenterPointsForRental(movie.getCode(), rental, frequentRenterPoints);

      //print figures for this rental
      resultBuilder
          .append("\t")
          .append(movie.getTitle())
          .append("\t")
          .append(thisAmount)
          .append("\n");

      totalAmount = totalAmount.add(thisAmount);
    }

    return resultBuilder
        .append("Amount owed is ")
        .append(totalAmount)
        .append("\n")
        .append("You earned ")
        .append(frequentRenterPoints)
        .append(" frequent renter points\n")
        .toString();
  }

  private int getFrequentRenterPointsForRental(String movieCode, MovieRental rental, int currentRenterPoints) {
    if (Objects.equals(movieCode, "new") && rental.getDays() > 2) {
      return currentRenterPoints + 2; // add bonus for a two day new release rental
    } else {
      return currentRenterPoints + 1;
    }
  }

  private HashMap<String, Movie> getMovies() {
    HashMap<String, Movie> movies = new HashMap<>();
    movies.put("F001", new Movie("Ran", "regular"));
    movies.put("F002", new Movie("Trois Couleurs: Bleu", "regular"));
    movies.put("F003", new Movie("Cars 2", "childrens"));
    movies.put("F004", new Movie("Latest Hit Release", "new"));

    return movies;
  }

  private BigDecimal determineRentalPriceForCategory(String movieCode, MovieRental rental) {
    BigDecimal rentalPriceForCategory = BigDecimal.valueOf(0);
    switch (movieCode) {
      case "regular":
        rentalPriceForCategory = BigDecimal.valueOf(2);
        if (rental.getDays() > 2) {
          return BigDecimal.valueOf((rental.getDays() - 2) * 1.5)
              .add(rentalPriceForCategory);
        }
      case "new":
        return BigDecimal.valueOf(rental.getDays() * 3);
      case "childrens":
        rentalPriceForCategory = BigDecimal.valueOf(1.5);
        if (rental.getDays() > 3) {
          return BigDecimal.valueOf((rental.getDays() - 3) * 1.5)
              .add(rentalPriceForCategory);
        }
        break;
    }
    return rentalPriceForCategory;
  }

}
