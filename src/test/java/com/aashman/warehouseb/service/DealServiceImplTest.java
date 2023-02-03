package com.aashman.warehouseb.service;

import com.aashman.warehouseb.model.CSVDeal;
import com.aashman.warehouseb.model.Deal;
import com.aashman.warehouseb.repository.AccumulativeDealRepository;
import com.aashman.warehouseb.repository.DealRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DealServiceImplTest extends Mockito {
    @Mock
    DealRepository dealRepository;

    @Mock
    AccumulativeDealRepository accumulativeDealRepository;

    @InjectMocks
    DealServiceImpl dealService;

    @BeforeEach
    public void setUp() {

        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void saveDeals_withEmptyCsvDeals_ExceptionShouldBeThrown() {
        assertThrows(Exception.class, () -> dealService.saveDeals(new ArrayList<>()));
    }

    @Test
    public void saveDeals_WithCsvDeals_SavedCsvDealsShouldBeReturned() throws Exception {
        when(dealRepository.saveAll(anyList())).thenReturn(getDeals());
        when(accumulativeDealRepository.findByOrderingCurrency(anyString())).thenReturn(Optional.empty());
        when(accumulativeDealRepository.saveAll(anyList())).thenReturn(new ArrayList<>());

        List<Deal> saveDeals = dealService.saveDeals(getCsvDeals());
        assertEquals(saveDeals.size(), getCsvDeals().size());
        assertEquals(saveDeals.get(1).getAmount(), getCsvDeals().get(1).getAmount());
    }

    private List<CSVDeal> getCsvDeals() {
        return Arrays.asList(new CSVDeal(1L, "USD", "ABC", new Date(), 100.00), new CSVDeal(2L, "CKS", "BDS", new Date(), 200.21));
    }

    private List<Deal> getDeals() {
        return Arrays.asList(new Deal(1L, "USD", "ABC", new Date(), 100.00, false), new Deal(2L, "CKS", "BDS", new Date(), 200.21, true));
    }

}