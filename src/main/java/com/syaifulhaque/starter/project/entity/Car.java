package com.syaifulhaque.starter.project.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "car")
@Builder
public class Car {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "police_number")
    private String policeNumber;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "color")
    private String color;

    @PrePersist
    public void prePersist() {
        createdDate = LocalDateTime.now();
    }


}
