package com.aashman.warehouseb.service;

import com.aashman.warehouseb.model.CSVDeal;
import com.aashman.warehouseb.model.Deal;

import java.util.List;

public interface DealService {
    List<Deal> saveDeals(List<CSVDeal> deals) throws Exception;
}
