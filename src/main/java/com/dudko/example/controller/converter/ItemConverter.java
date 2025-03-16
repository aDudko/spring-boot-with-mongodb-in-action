package com.dudko.example.controller.converter;

import com.dudko.example.controller.dto.ContentDto;
import com.dudko.example.controller.dto.ItemRequestDto;
import com.dudko.example.controller.dto.ItemResponseDto;
import com.dudko.example.model.Content;
import com.dudko.example.model.Item;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static java.util.Optional.ofNullable;

@Component
@RequiredArgsConstructor
public class ItemConverter {

    public Item convertToModel(@NonNull ItemRequestDto dto) {
        final ContentDto contentDto = dto.getContentDto();
        return Item.builder()
                .id(dto.getId() == null ? UUID.randomUUID() : UUID.fromString(dto.getId()))
                .content(Content.builder()
                        .content(ofNullable(contentDto).map(ContentDto::getContent).orElse(null))
                        .build())
                .build();
    }

    public ItemResponseDto convertToDto(@NonNull Item model) {
        final Content content = model.getContent();
        return ItemResponseDto.builder()
                .id(model.getId().toString())
                .contentDto(ContentDto.builder()
                        .content(ofNullable(content).map(Content::getContent).orElse(null))
                        .build())
                .build();
    }

}
