package com.aashman.warehouseb.repository;

import com.aashman.warehouseb.model.AccumulativeDeal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccumulativeDealRepository extends JpaRepository<AccumulativeDeal, Integer> {
    Optional<AccumulativeDeal> findByOrderingCurrency(String orderingCurrency);
}
