package com.greatersum.rental;

import java.math.BigDecimal;

enum PriceCode {
  REGULAR {
    @Override
    BigDecimal getPriceForRental(MovieRental rental) {
      BigDecimal rentalPriceForCategory = BigDecimal.valueOf(2);
      if (rental.getDays() > 2) {
        return BigDecimal.valueOf((rental.getDays() - 2) * 1.5)
            .add(rentalPriceForCategory);
      } else return BigDecimal.valueOf(rental.getDays() * 3);
    }
  },
  NEW {
    @Override
    public BigDecimal getPriceForRental(MovieRental rental) {
      return BigDecimal.valueOf(rental.getDays() * 3);
    }
  },
  CHILDRENS {
    @Override
    public BigDecimal getPriceForRental(MovieRental rental) {
      BigDecimal rentalPriceForCategory = BigDecimal.valueOf(1.5);
      if (rental.getDays() > 3) {
        return BigDecimal.valueOf((rental.getDays() - 3) * 1.5)
            .add(rentalPriceForCategory);
      } else return rentalPriceForCategory;
    }
  };


  /* Could do something with Optional instead of returning an
  arbitrary BigDecimal here. */
  BigDecimal getPriceForRental(MovieRental rental) {
    return BigDecimal.ONE;
  }

}
