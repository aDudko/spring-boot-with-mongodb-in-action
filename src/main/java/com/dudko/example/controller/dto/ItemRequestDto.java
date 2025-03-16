package com.dudko.example.controller.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemRequestDto {

    private String id = null;

    @NotNull
    private ContentDto contentDto;

}
