package com.example.cardealerxml.services.impl;

import com.example.cardealerxml.models.dtos.*;
import com.example.cardealerxml.models.entities.Car;
import com.example.cardealerxml.models.entities.Part;
import com.example.cardealerxml.repositories.CarRepository;
import com.example.cardealerxml.services.CarService;
import com.example.cardealerxml.services.PartService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final ModelMapper modelMapper;
    private final PartService partService;

    public CarServiceImpl(CarRepository carRepository, ModelMapper modelMapper, PartService partService) {
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
        this.partService = partService;
    }

    @Override
    @Transactional
    public void addCard(CarAddDto carAddDto) {
        Car car = this.modelMapper.map(carAddDto, Car.class);
        Set<Part> parts = new HashSet<>();
        int random = new Random().nextInt(10) + 20;
        for (int i = 10; i < random; i++) {
            Part randomParts = this.partService.getRandomParts();
            parts.add(randomParts);
        }
        car.setParts(parts);
        this.carRepository.saveAndFlush(car);
    }

    @Override
    public Car getRandomCar() {
        long randomId = new Random().nextInt((int) this.carRepository.count()) + 1;
        return this.carRepository.findById(randomId).get();
    }

    @Override
    public CarRootExportDto getCarsByMake(String make) {
        CarExportDto[] carExportDto = this.modelMapper.map(this.carRepository.findAllByMakeOrderByModelAscTravelledDistanceDesc(make), CarExportDto[].class);
        return new CarRootExportDto(Arrays.stream(carExportDto).collect(Collectors.toList()));
    }

    @Override
    public CarsWithPartsExportDto getAllCars() {
        List<Car> cars = this.carRepository.findAll();
        List<CarWithPartsExportDto> carWithPartsExportDtos = new ArrayList<>();
        cars.forEach(car -> {
            CarWithPartsExportDto carWithPartsExportDto = this.modelMapper.map(car, CarWithPartsExportDto.class);
            PartExportDto[] partExportDto = this.modelMapper.map(car.getParts(), PartExportDto[].class);
            carWithPartsExportDto.setPartsExportDto(new PartsExportDto(Arrays.stream(partExportDto).collect(Collectors.toList())));
            carWithPartsExportDtos.add(carWithPartsExportDto);
        });

        return new CarsWithPartsExportDto(carWithPartsExportDtos);
    }

}
