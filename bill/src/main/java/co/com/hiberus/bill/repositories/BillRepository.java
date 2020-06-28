package co.com.hiberus.bill.repositories;

import co.com.hiberus.bill.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill, Integer> {
}
