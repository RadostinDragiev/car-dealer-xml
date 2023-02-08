package com.example.cardealerxml.repositories;

import com.example.cardealerxml.models.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
    @Query(value = "SELECT c.make, c.model, c.travelled_distance, ANY_VALUE(cs.name) AS 'customer', ANY_VALUE(s.discount) AS 'discount', SUM(p.price) AS 'price' FROM sales AS s JOIN customers cs on cs.id = s.customer_id JOIN cars c on c.id = s.car_id\n JOIN cars_parts cp on c.id = cp.cars_id JOIN parts p on p.id = cp.parts_id GROUP BY c.id;", nativeQuery = true)
    List<String> getSalesWithAppliedDiscount();
}
