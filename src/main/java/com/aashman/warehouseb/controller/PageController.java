package com.aashman.warehouseb.controller;

import com.aashman.warehouseb.model.AccumulativeDeal;
import com.aashman.warehouseb.model.Deal;
import com.aashman.warehouseb.repository.AccumulativeDealRepository;
import com.aashman.warehouseb.repository.DealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PageController {

    private final DealRepository dealRepository;
    private final AccumulativeDealRepository accumulativeDealRepository;

    @GetMapping("/valid-deals")
    public String getValidDealsPage(Model model) {
       List<Deal> deals = dealRepository.findAllByIsInvalid(false);
       model.addAttribute("deals", deals);
        return "deals";
    }

    @GetMapping("/invalid-deals")
    public String getInValidDealsPage(Model model) {
        List<Deal> deals = dealRepository.findAllByIsInvalid(true);
        model.addAttribute("deals", deals);
        return "deals";
    }

    @GetMapping("/accumulative-deals")
    public String getAccumulativeDealsPage(Model model) {
        List<AccumulativeDeal> deals = accumulativeDealRepository.findAll();
        model.addAttribute("deals", deals);
        return "accumulative-deals";
    }

    @GetMapping("/")
    public String getUploadFilePage() {
        return "upload";
    }

}
