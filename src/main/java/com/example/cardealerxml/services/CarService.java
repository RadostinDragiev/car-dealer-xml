package com.example.cardealerxml.services;

import com.example.cardealerxml.models.dtos.CarAddDto;
import com.example.cardealerxml.models.entities.Car;

public interface CarService {
    void addCard(CarAddDto carAddDto);

    Car getRandomCar();

}
