package com.aashman.warehouseb.controller;

import com.aashman.warehouseb.repository.AccumulativeDealRepository;
import com.aashman.warehouseb.repository.DealRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PageControllerTest extends Mockito {
    @Mock
    DealRepository dealRepository;

    @Mock
    AccumulativeDealRepository accumulativeDealRepository;

    @Mock
    Model model;

    @InjectMocks
    PageController pageController;

    @BeforeEach
    public void setUp() {

        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void getValidDealsPage_ShouldReturn_DealsPage() {
        when(dealRepository.findAllByIsInvalid(anyBoolean())).thenReturn(new ArrayList<>());
        String response = pageController.getValidDealsPage(model);
        assertEquals(response, "deals");
    }

    @Test
    public void getAccumulativeDealsPage_ShouldReturn_AccumulativeDealsPage() {
        when(accumulativeDealRepository.findAll()).thenReturn(new ArrayList<>());
        String response = pageController.getAccumulativeDealsPage(model);
        assertEquals(response, "accumulative-deals");
    }


}