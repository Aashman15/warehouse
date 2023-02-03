package com.aashman.warehouseb.controller;

import com.aashman.warehouseb.model.CSVDeal;
import com.aashman.warehouseb.service.DealService;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class UploadFileController {

    private final DealService dealService;

    @PostMapping(value = "/upload-file")
    public String uploadFile(@RequestParam("file") MultipartFile file, Model model) throws Exception {
        if (file.isEmpty()) {
            throw new Exception("Please select a CSV file to upload.");
        } else {
            try {
                Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
                CsvToBean<CSVDeal> csvToBean = new CsvToBeanBuilder(reader).withType(CSVDeal.class).withIgnoreLeadingWhiteSpace(true).build();
                List<CSVDeal> deals = csvToBean.parse();

                dealService.saveDeals(deals);

                model.addAttribute("message", "Successfully imported");
                model.addAttribute("status", true);

            } catch (Exception ex) {
                throw new Exception("An error occurred while processing the CSV file.");
            }

        }
        return "status";
    }


}



