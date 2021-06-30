package com.syaifulhaque.starter.project.controller;

import com.syaifulhaque.starter.project.dto.CarDTO;
import com.syaifulhaque.starter.project.entity.Car;
import com.syaifulhaque.starter.project.service.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;

@WebMvcTest(CarController.class)
class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarService carService;

    private Car car;
    private CarDTO carDTO;

    @BeforeEach
    void setup() {
        car = Car.builder()
                .id(1L)
                .color("Red")
                .createdDate(LocalDateTime.now())
                .policeNumber("E61M")
                .name("Ayla")
                .build();
        carDTO = CarDTO.builder()
                .color("Red")
                .policeNumber("E61M")
                .name("Ayla")
                .build();
    }

    @Test
    void saveCar() throws Exception {
        Mockito.when(carService.save(Mockito.any(CarDTO.class)))
                .thenReturn(car);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/car")
                .contextPath("/api")
                .servletPath("/car")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Buggati\",\"policeNumber\":\"E 61 MS\",\"color\":\"Black\"}");

        mockMvc.perform(requestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void updateCar() throws Exception {
        Mockito.when(carService.update(Mockito.anyLong(), Mockito.any(CarDTO.class)))
                .thenReturn(car);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .patch("/api/car/1")
                .contextPath("/api")
                .servletPath("/car/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Buggati\",\"policeNumber\":\"E 61 MS\",\"color\":\"Black\"}");

        mockMvc.perform(requestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void deleteCar() throws Exception {
        Mockito.when(carService.delete(Mockito.anyLong()))
                .thenReturn(car);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/api/car/1")
                .contextPath("/api")
                .servletPath("/car/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Buggati\",\"policeNumber\":\"E 61 MS\",\"color\":\"Black\"}");

        mockMvc.perform(requestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void findOneCar() throws Exception {
        Mockito.when(carService.findOneById(Mockito.anyLong()))
                .thenReturn(car);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/car/1")
                .contextPath("/api")
                .servletPath("/car/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("");

        mockMvc.perform(requestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}