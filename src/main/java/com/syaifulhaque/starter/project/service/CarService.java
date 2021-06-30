package com.syaifulhaque.starter.project.service;

import com.syaifulhaque.starter.project.annotation.LogExecutionTime;
import com.syaifulhaque.starter.project.common.CopyUtil;
import com.syaifulhaque.starter.project.dto.CarDTO;
import com.syaifulhaque.starter.project.entity.Car;
import com.syaifulhaque.starter.project.repository.CarRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
public class CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    ModelMapper modelMapper;

    public List<Car> getCars() {
        return carRepository.findAll();
    }

    @LogExecutionTime
    public Car save(CarDTO car) {
        return carRepository.save(modelMapper.map(car, Car.class));
    }

    public Car findOneById(long id) {
        return carRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public Car update(long id, CarDTO carDTO) {
        Car car = carRepository.findById(id).orElseThrow(NoSuchElementException::new);
        CopyUtil.copyNonNullProperties(car, carDTO);
        return carRepository.save(car);
    }

    public Car delete(Long id) {
        Car car = carRepository.findById(id).orElseThrow(NoSuchElementException::new);
        carRepository.delete(car);
        return car;
    }
}
