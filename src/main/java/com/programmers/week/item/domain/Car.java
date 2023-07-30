package com.programmers.week.item.domain;

import com.programmers.week.Message;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@DiscriminatorValue("CAR")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Car extends Item {

  private long power;

  private Car(int price, int stockQuantity, long power) {
    super(price, stockQuantity);
    this.power = power;
  }

  public static Item of(int price, int stockQuantity, long power) {
    validatePower(power);
    return new Car(price, stockQuantity, power);
  }

  private static void validatePower(long power) {
    if (power < 0) {
      throw new IllegalStateException(String.format(Message.POWER_PRICE_IS_MINUS + "%s", power));
    }
  }

}