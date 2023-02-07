package com.example.cardealerxml.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarAddDto {
    @XmlElement(name = "make")
    private String make;

    @XmlElement(name = "model")
    private String model;

    @XmlElement(name = "travelled-distance")
    private long travelledDistance;
}
