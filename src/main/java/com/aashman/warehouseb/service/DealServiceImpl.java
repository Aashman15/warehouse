package com.aashman.warehouseb.service;

import com.aashman.warehouseb.model.AccumulativeDeal;
import com.aashman.warehouseb.model.CSVDeal;
import com.aashman.warehouseb.model.Deal;
import com.aashman.warehouseb.repository.AccumulativeDealRepository;
import com.aashman.warehouseb.repository.DealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class DealServiceImpl implements DealService {
    private final DealRepository dealRepository;

    private final AccumulativeDealRepository accumulativeDealRepository;

    @Override
    public List<Deal>  saveDeals(List<CSVDeal> csvDeals) throws Exception {

        if (csvDeals.isEmpty()){
            throw new Exception("Couldn't find any deals to import");
        }

        List<Deal> deals = new ArrayList<>();

        Map<String, Integer> accumulativeValues = new HashMap<>();

        csvDeals.forEach(csvDeal -> {
            Deal deal = new Deal(csvDeal);

            if (!deal.isInvalid()) {
                if (accumulativeValues.containsKey(deal.getFromCurrency())) {
                    int value = accumulativeValues.get(deal.getFromCurrency());
                    accumulativeValues.put(deal.getFromCurrency(), ++value);
                } else {
                    accumulativeValues.put(deal.getFromCurrency(), 1);
                }
            }

            deals.add(deal);
        });


        saveAccumulativeDeals(accumulativeValues);
       return dealRepository.saveAll(deals);
    }

    private void saveAccumulativeDeals(Map<String, Integer> accumulativeValues) {
        List<AccumulativeDeal> accumulativeDeals = new ArrayList<>();
        for (Object key : accumulativeValues.keySet()) {

            AccumulativeDeal accumulativeDeal;

            Optional<AccumulativeDeal> optional =  accumulativeDealRepository.
                    findByOrderingCurrency(key.toString());

            accumulativeDeal = optional.orElseGet(AccumulativeDeal::new);

            accumulativeDeal.setCount(accumulativeDeal.getCount() + accumulativeValues.get(key));
            accumulativeDeal.setOrderingCurrency(key.toString());
            accumulativeDeals.add(accumulativeDeal);
        }
        accumulativeDealRepository.saveAll(accumulativeDeals);
    }
}
