package com.aashman.warehouseb.controller;

import com.aashman.warehouseb.service.DealService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class UploadFileControllerTest extends Mockito {

    @Mock
    DealService dealService;

    @Mock
    Model model;

    @Mock
    MultipartFile multipartFile;

    @InjectMocks
    UploadFileController uploadFileController;

    @BeforeEach
    public void setUp() {

        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void uploadFile_withValidFile_StatusPageWithSuccessMessageShouldBeReturned() throws Exception {
        MultipartFile file = new MockMultipartFile("sample", "sample_data.csv", "text/plain", Files.readAllBytes(Path.of("./src/main/resources/sample/sample_data.csv")));

        when(dealService.saveDeals(anyList())).thenReturn(new ArrayList<>());

        String response = uploadFileController.uploadFile(file, model);
        assertEquals(response, "status");
    }


    @Test
    public void uploadFile_withEmptyFile_ExceptionShouldBeThrown() {
        assertThrows(Exception.class, () -> uploadFileController.uploadFile(multipartFile, model));
    }

}