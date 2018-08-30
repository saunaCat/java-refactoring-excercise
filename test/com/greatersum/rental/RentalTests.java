package com.greatersum.rental;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class RentalTests {

  // Test cases for all categories and some corner cases would be further improvement.
  @Test
  public void MartinTest() {
    Customer customer = new Customer(
        "martin", Arrays.asList(
        new MovieRental("F001", 3),
        new MovieRental("F002", 1)
    ));
    RentalInfo info = new RentalInfo();
    String actualResult = info.statement(customer);

    /* Since it's short and string comparison is so exact, I put it here instead of parsing
    the file to make tabs and newlines visible. But it would be better to put the result into
    something more useful than a String and compare properties instead of Strings. */
    String expectedResult =
        "Rental Record for martin\n"
            + "\tRan\t3.5\n"
            + "\tTrois Couleurs: Bleu\t3\n"
            + "Amount owed is 6.5\n"
            + "You earned 2 frequent renter points\n";

    Assert.assertEquals(expectedResult, actualResult);
  }
}
