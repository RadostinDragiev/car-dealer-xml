package com.example.cardealerxml.models.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "suppliers")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Supplier extends BaseEntity {
    private String name;

    @Column(name = "is_importer")
    private boolean isImporter;

    @OneToMany(mappedBy = "supplier", fetch = FetchType.EAGER)
    private Set<Part> parts;
}
