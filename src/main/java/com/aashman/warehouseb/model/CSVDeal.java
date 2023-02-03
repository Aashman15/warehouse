package com.aashman.warehouseb.model;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import lombok.*;

import java.util.Date;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CSVDeal {
    private Long Id;
    @CsvBindByName
    private String fromCurrency;
    @CsvBindByName
    private String toCurrency;
    @CsvBindByName
    @CsvDate(value = "dd/MM/yyyy")
    private Date dealDate;
    @CsvBindByName
    private Double amount;
}
