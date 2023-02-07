package com.example.cardealerxml.repositories;

import com.example.cardealerxml.models.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    @Query("SELECT s FROM Supplier AS s WHERE s.name = :name AND s.isImporter = :isImporter")
    Supplier findSupplierByNameAndImporterEquals(@Param(value = "name") String name, @Param(value = "isImporter") boolean isImporter);

}
