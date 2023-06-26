package com.wea4saken.rikmasters.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wea4saken.rikmasters.dto.DetailDto;
import com.wea4saken.rikmasters.model.Detail;
import com.wea4saken.rikmasters.repository.DetailRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class DetailControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private DetailRepository detailRepository;
    @Autowired
    private ObjectMapper objectMapper;

    private final Detail detail = new Detail();
    private final DetailDto detailDto = new DetailDto();

    @BeforeEach
    void setUp() {
        detail.setSerialNumber("1a2s3d4f");
        detail.setType("Test");
        detailRepository.save(detail);
    }

    @AfterEach
    void cleanUp() {
        detailRepository.delete(detail);
    }

    @Test
    public void testAddDetailReturnCorrectAddedDetailFromDatabase() throws Exception {
        detailDto.setType("Test 2");

        mockMvc.perform(post("/detail")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(detailDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.serialNumber").isString())
                .andExpect(jsonPath("$.type").value(detailDto.getType()));
    }

    @Test
    public void testUpdateDetailReturnsUpdatedDetail() throws Exception {
        detail.setType("Test 3");;
        detailRepository.save(detail);

        mockMvc.perform(patch("/detail/{serialNumber}", detail.getSerialNumber())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(detail)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.serialNumber").isString())
                .andExpect(jsonPath("$.type").value(detail.getType()));
    }

    @Test
    public void testGetAllDetailsReturnsCorrectListOfDetailsFromDatabase() throws Exception {
        mockMvc.perform(get("/detail")
                        .param("pageNumber", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void testGetAllDetailsReturnsCorrectListOfDetails() throws Exception {
        int pageNumber = 1;
        int pageSize = 1;

        mockMvc.perform(get("/detail")
                        .param("pageNumber", String.valueOf(pageNumber))
                        .param("pageSize", String.valueOf(pageSize)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(pageSize));
    }

    @Test
    public void testGetDetailReturnsCorrectDetailFromDatabase() throws Exception {
        mockMvc.perform(get("/detail/{serialNumber}", detail.getSerialNumber()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.serialNumber").isString())
                .andExpect(jsonPath("$.type").value(detail.getType()));
    }

    @Test
    public void testDeleteDetailReturnsOkWhenDetailDeleted() throws Exception {
        mockMvc.perform(delete("/detail/{serialNumber}", detail.getSerialNumber()))
                .andExpect(status().isOk());
    }

}