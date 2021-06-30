package com.syaifulhaque.starter.project.controller;

import com.syaifulhaque.starter.project.common.Constants;
import com.syaifulhaque.starter.project.dto.CarDTO;
import com.syaifulhaque.starter.project.dto.ResponseDto;
import com.syaifulhaque.starter.project.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "/car")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping()
    public ResponseEntity getCar() {
        return ResponseEntity.ok(ResponseDto.builder()
                .timestamp(LocalDateTime.now())
                .data(carService.getCars())
                .message(Constants.SUCCESS_MESSAGE)
                .build());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity getOneCar(@PathVariable("id") Long id) {
        return ResponseEntity.ok(ResponseDto.builder()
                .timestamp(LocalDateTime.now())
                .data(carService.findOneById(id))
                .message(Constants.SUCCESS_MESSAGE)
                .build());
    }

    @PostMapping()
    public ResponseEntity saveCar(@RequestBody CarDTO carDTO) {
        return ResponseEntity.ok(ResponseDto.builder()
                .timestamp(LocalDateTime.now())
                .data(carService.save(carDTO))
                .message(Constants.SUCCESS_MESSAGE)
                .build());
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity updateCar(@PathVariable("id") Long id, @RequestBody @Validated CarDTO car) {
        return ResponseEntity.ok(ResponseDto.builder()
                .timestamp(LocalDateTime.now())
                .data(carService.update(id, car))
                .message(Constants.SUCCESS_MESSAGE)
                .build());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteCar(@PathVariable("id") Long id) {
        return ResponseEntity.ok(ResponseDto.builder()
                .timestamp(LocalDateTime.now())
                .data(carService.delete(id))
                .message(Constants.SUCCESS_MESSAGE)
                .build());
    }
}
