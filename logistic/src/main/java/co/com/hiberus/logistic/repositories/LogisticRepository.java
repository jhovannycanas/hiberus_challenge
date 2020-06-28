package co.com.hiberus.logistic.repositories;

import co.com.hiberus.logistic.model.Logistic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogisticRepository extends JpaRepository<Logistic, Integer> {
}
