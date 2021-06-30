package com.syaifulhaque.starter.project.repository;

import com.syaifulhaque.starter.project.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
}
