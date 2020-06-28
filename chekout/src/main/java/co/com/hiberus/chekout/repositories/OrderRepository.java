package co.com.hiberus.chekout.repositories;

import co.com.hiberus.chekout.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
