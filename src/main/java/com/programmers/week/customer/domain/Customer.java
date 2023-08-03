package com.programmers.week.customer.domain;

import com.programmers.week.exception.Message;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 5, nullable = false)
    private String firstName;

    @Column(length = 2, nullable = false)
    private String lastName;

    public Customer(String firstName, String lastName) {
        validateFirstName(firstName);
        validateLastName(lastName);
        this.firstName = firstName;
        this.lastName = lastName;
    }

  private static void validateFirstName(String firstName) {
    if (Objects.isNull(firstName) || firstName.isBlank()) {
      throw new IllegalStateException(Message.FIRSTNAME_IS_NULL);
    }
  }

  public void changeLastName(String lastName) {
    validateLastName(lastName);
    this.lastName = lastName;
  }

  private static void validateLastName(String lastName) {
    if (Objects.isNull(lastName) || lastName.isBlank()) {
      throw new IllegalStateException(Message.LASTNAME_IS_NULL);
    }
  }

}
