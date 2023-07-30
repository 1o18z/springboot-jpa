package com.programmers.week.orderItem.domain;

import com.programmers.week.base.BaseEntity;
import com.programmers.week.item.domain.Item;
import com.programmers.week.order.domain.Order;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem extends BaseEntity {

  @Id
  @GeneratedValue
  private Long id;

  @ManyToOne
  @JoinColumn(name = "order_id")
  private Order order;

  @ManyToOne
  @JoinColumn(name = "item_id")
  private Item item;

  public OrderItem(Order order, Item item) {
    this.order = order;
    this.item = item;
  }

}