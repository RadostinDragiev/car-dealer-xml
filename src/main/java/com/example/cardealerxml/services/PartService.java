package com.example.cardealerxml.services;

import com.example.cardealerxml.models.dtos.PartAddDto;
import com.example.cardealerxml.models.entities.Part;

public interface PartService {
    void addPart(PartAddDto partAddDto);

    Part getRandomParts();
}
