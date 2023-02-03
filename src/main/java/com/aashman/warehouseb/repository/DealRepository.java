package com.aashman.warehouseb.repository;

import com.aashman.warehouseb.model.Deal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DealRepository extends JpaRepository<Deal, Long> {
    List<Deal> findAllByIsInvalid(boolean isInvalid);
}
