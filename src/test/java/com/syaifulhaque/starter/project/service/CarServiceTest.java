package com.syaifulhaque.starter.project.service;

import com.syaifulhaque.starter.project.dto.CarDTO;
import com.syaifulhaque.starter.project.entity.Car;
import com.syaifulhaque.starter.project.repository.CarRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CarServiceTest {

    @Autowired
    private CarService carService;

    @MockBean
    private CarRepository carRepository;

    private Car car;

    private CarDTO carDTO;

    @BeforeEach
    void setup(){

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
    void getCars() {
        Mockito.when(carRepository.findAll()).thenReturn(List.of(car));
        Assertions.assertNotNull(carService.getCars());
    }

    @Test
    void save() {
        Mockito.when(carRepository.save(Mockito.any(Car.class))).thenReturn(car);
        Car actual = carService.save(carDTO);

        Assertions.assertNotNull(actual);
    }

    @Test
    public void findOneById() {
        Optional<Car> carOptional = Optional.of(car);
        Mockito.when(carRepository.findById(Mockito.any(Long.class))).thenReturn(carOptional);

        Car actual = carService.findOneById(1L);

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(carOptional.get().getId(), actual.getId());
    }

    @Test
    public void findOneByIdNotFound() {
        Mockito.when(carRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.empty());
        Assertions.assertThrows(NoSuchElementException.class, ()  -> carService.findOneById(2L));
    }

    @Test
    public void update() {

        Optional<Car> carOptional = Optional.of(car);
        Mockito.when(carRepository.findById(Mockito.any(Long.class))).thenReturn(carOptional);
        Mockito.when(carRepository.save(Mockito.any(Car.class))).thenReturn(car);
        Car actual = carService.update(1L, carDTO);

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(car, actual);
    }

    @Test
    public void deleteTest() {
        Optional<Car> carOptional = Optional.of(car);
        Mockito.when(carRepository.findById(Mockito.any(Long.class))).thenReturn(carOptional);
        Car actual = carService.delete(1L);
        Assertions.assertNotNull(actual);
    }
}