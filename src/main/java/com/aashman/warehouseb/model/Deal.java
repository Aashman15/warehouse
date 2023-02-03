package com.aashman.warehouseb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;

import java.util.Date;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Deal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fromCurrency;
    private String toCurrency;
    private Date dealDate;
    private Double amount;
    private boolean isInvalid;

    public Deal(CSVDeal csvDeal) {
        this.fromCurrency = csvDeal.getFromCurrency();
        this.toCurrency = csvDeal.getToCurrency();
        this.dealDate = csvDeal.getDealDate();
        this.amount = csvDeal.getAmount();
        this.isInvalid = ObjectUtils.isEmpty(csvDeal.getDealDate()) || ObjectUtils.isEmpty(csvDeal.getFromCurrency()) || ObjectUtils.isEmpty(csvDeal.getToCurrency()) || ObjectUtils.isEmpty(csvDeal.getAmount());
    }


}
