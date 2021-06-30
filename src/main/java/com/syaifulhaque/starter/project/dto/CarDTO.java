package com.syaifulhaque.starter.project.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarDTO {
    private String name;
    private String policeNumber;
    private String color;
}
