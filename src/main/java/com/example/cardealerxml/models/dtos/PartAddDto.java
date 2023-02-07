package com.example.cardealerxml.models.dtos;

import com.example.cardealerxml.models.entities.Supplier;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PartAddDto {
    private String name;

    private BigDecimal price;

    private int quantity;

    private Supplier supplier;
}
