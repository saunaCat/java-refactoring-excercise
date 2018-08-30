package com.greatersum.rental;

import java.math.BigDecimal;
import java.util.HashMap;

public class RentalInfo {
  private HashMap<String, Movie> movies = getMovies();

  //Proper logging would be good to add if there was time.
  public String statement(Customer customer) {

    BigDecimal totalAmount = BigDecimal.valueOf(0);
    int frequentRenterPoints = 0;
    StringBuilder resultBuilder = new StringBuilder("Rental Record for " + customer.getName() + "\n");

    for (MovieRental rental : customer.getRentals()) {
      Movie movie = movies.get(rental.getMovieId());

      // determine amount for each movie
      BigDecimal thisAmount = movie.getCode().getPriceForRental(rental);

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

  private int getFrequentRenterPointsForRental(PriceCode movieCode, MovieRental rental, int currentRenterPoints) {
    if (movieCode.equals(PriceCode.NEW) && rental.getDays() > 2) {
      return currentRenterPoints + 2; // add bonus for a two day new release rental
    } else {
      return currentRenterPoints + 1;
    }
  }

  /*Further improvement would be to implement this HashMap as
  another "Movies" object and store them there. */
  private HashMap<String, Movie> getMovies() {
    HashMap<String, Movie> movies = new HashMap<>();
    movies.put("F001", new Movie("Ran", PriceCode.REGULAR));
    movies.put("F002", new Movie("Trois Couleurs: Bleu", PriceCode.REGULAR));
    movies.put("F003", new Movie("Cars 2", PriceCode.CHILDRENS));
    movies.put("F004", new Movie("Latest Hit Release", PriceCode.NEW));

    return movies;
  }

}
