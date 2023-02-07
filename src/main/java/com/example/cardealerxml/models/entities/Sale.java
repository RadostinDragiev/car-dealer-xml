package com.example.cardealerxml.models.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "sales")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Sale extends BaseEntity {
    private int discount;

    @OneToOne
    private Car car;

    @ManyToOne(targetEntity = Customer.class, cascade = CascadeType.ALL)
    private Customer customer;
}
