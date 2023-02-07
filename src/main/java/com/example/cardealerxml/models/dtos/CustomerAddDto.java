package com.example.cardealerxml.models.dtos;

import com.example.cardealerxml.utils.LocalDateTimeAdapter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "customer")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerAddDto {
    @XmlAttribute(name = "name")
    private String name;

    @XmlElement(name = "birth-date")
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    private LocalDateTime birthDate;

    @XmlElement(name = "is-young-driver")
    private boolean isYoungDriver;
}
