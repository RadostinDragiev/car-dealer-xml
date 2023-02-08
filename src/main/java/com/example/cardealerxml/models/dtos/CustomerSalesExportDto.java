package com.example.cardealerxml.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "customer")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerSalesExportDto {
    @XmlAttribute(name = "full-name")
    private String name;

    @XmlAttribute(name = " bought-car")
    private int boughtCars;

    @XmlAttribute(name = "spent-money")
    private double spentMoney;
}
