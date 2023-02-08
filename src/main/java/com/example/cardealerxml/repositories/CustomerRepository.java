package com.example.cardealerxml.repositories;

import com.example.cardealerxml.models.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("SELECT c FROM Customer AS c ORDER BY c.birthDate, c.isYoungDriver")
    Set<Customer> getAllOrderedByBirthDate();

    @Query(value = "SELECT cs.name, COUNT(DISTINCT (s.car_id)) AS 'bought_cars', SUM(p.price) AS 'spent_money' FROM customers AS cs JOIN sales s on cs.id = s.customer_id JOIN cars c on c.id = s.car_id JOIN cars_parts cp on c.id = cp.cars_id JOIN parts p on p.id = cp.parts_id GROUP BY cs.id ORDER BY spent_money DESC, bought_cars DESC", nativeQuery = true)
    List<String> getCustomersWIthTotalSales();
}
