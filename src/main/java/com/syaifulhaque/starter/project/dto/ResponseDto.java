package com.syaifulhaque.starter.project.dto;


import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto {

    private Integer status;
    private LocalDateTime timestamp;
    private String message;
    private Object data;
    private String[] errors;
}
