package com.example.cardealerxml.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerAddDto {
    private String name;

    private LocalDateTime birthDate;

    private boolean isYoungDriver;
}
