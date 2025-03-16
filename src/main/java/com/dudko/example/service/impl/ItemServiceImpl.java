package com.dudko.example.service.impl;

import com.dudko.example.domain.entity.ItemEntity;
import com.dudko.example.domain.mapper.ItemMapper;
import com.dudko.example.domain.repository.ItemRepository;
import com.dudko.example.model.Item;
import com.dudko.example.model.exception.ResourceNotFoundException;
import com.dudko.example.service.ItemService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository repository;
    private final ItemMapper mapper;


    @Override
    public Item save(@NonNull Item item) {
        if (repository.existsById(item.getId())) {
            log.info("Item with id: {} already exists", item.getId());
            return mapper.mapToModel(repository.findById(item.getId()).get());
        }
        ItemEntity savedEntity = repository.save(mapper.mapToEntity(item));
        log.info("Item was saved successfully with id: {}", savedEntity.getId());
        return mapper.mapToModel(savedEntity);
    }

    @Override
    public Item getById(@NonNull String id) {
        log.debug("Fetching Data from DB...");
        return repository.findById(UUID.fromString(id))
                .map(mapper::mapToModel)
                .orElseThrow(() -> {
                    log.warn("Item not found for id {}", id);
                    return new ResourceNotFoundException(String.format("Item not found for id: %s", id));
                });
    }

    @Override
    public List<Item> getAll() {
        log.debug("Fetching Data from DB...");
        return repository.findAll().stream()
                .map(mapper::mapToModel)
                .toList();
    }

    @Override
    public Item update(Item item) {
        Optional<ItemEntity> doc = repository.findById(item.getId());
        if (doc.isEmpty()) {
            log.info("Item with id: {} not found", item.getId());
            return mapper.mapToModel(repository.save(mapper.mapToEntity(item)));
        } else {
            Item updatedDoc = Item.builder()
                    .id(doc.get().getId())
                    .content(item.getContent())
                    .build();
            Item savedDoc = mapper.mapToModel(repository.save(mapper.mapToEntity(updatedDoc)));
            log.info("Item with id: {} saved successfully", savedDoc.getId());
            return savedDoc;
        }
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(UUID.fromString(id));
        log.info("Item with id: {} deleted successfully", id);
    }

}
