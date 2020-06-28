package co.com.hiberus.bill.repositories;

import co.com.hiberus.bill.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    Optional<Order> findById(Integer id);
}
