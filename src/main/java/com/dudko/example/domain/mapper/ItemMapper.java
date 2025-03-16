package com.dudko.example.domain.mapper;

import com.dudko.example.domain.entity.ItemEntity;
import com.dudko.example.model.Content;
import com.dudko.example.model.Item;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static java.util.Optional.ofNullable;

@Component
@RequiredArgsConstructor
public class ItemMapper {

    public ItemEntity mapToEntity(@NonNull Item model) {
        final Content content = model.getContent();
        return ItemEntity.builder()
                .id(model.getId())
                .content(ofNullable(content).map(Content::getContent).orElse(null))
                .build();
    }

    public Item mapToModel(@NonNull ItemEntity entity) {
        return Item.builder()
                .id(entity.getId())
                .content(Content.builder()
                        .content(entity.getContent())
                        .build())
                .build();
    }

}
