package com.dudko.example.controller;

import com.dudko.example.controller.converter.ItemConverter;
import com.dudko.example.controller.dto.ItemRequestDto;
import com.dudko.example.controller.dto.ItemResponseDto;
import com.dudko.example.model.Item;
import com.dudko.example.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "User REST API")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/items", produces = "application/vnd.api.v1+json")
public class ItemController {

    private final ItemService itemService;
    private final ItemConverter converter;


    @Operation(summary = "Registration of a new document to be saved in DB")
    @PostMapping
    public ResponseEntity<ItemResponseDto> addDoc(@RequestBody ItemRequestDto itemRequest) {
        Item persisted = itemService.save(converter.convertToModel(itemRequest));
        ItemResponseDto converted = converter.convertToDto(persisted);
        return new ResponseEntity<>(converted, HttpStatus.CREATED);
    }

    @Operation(summary = "Find a document by ID")
    @GetMapping("/{id}")
    public ResponseEntity<ItemResponseDto> getById(@PathVariable("id") String id) {
        return ResponseEntity.ok(converter.convertToDto(itemService.getById(id)));
    }

    @Operation(summary = "Getting all documents")
    @GetMapping
    public ResponseEntity<List<ItemResponseDto>> getAll() {
        List<ItemResponseDto> responseDtos = itemService.getAll().stream()
                .map(converter::convertToDto)
                .toList();
        return ResponseEntity.ok(responseDtos);
    }

    @Operation(summary = "Update document. If document with ID not exist, it will be saved in DB")
    @PostMapping("/update")
    public ResponseEntity<ItemResponseDto> update(@RequestBody ItemRequestDto itemRequest) {
        Item updated = itemService.update(converter.convertToModel(itemRequest));
        ItemResponseDto converted = converter.convertToDto(updated);
        return ResponseEntity.ok(converted);
    }

    @Operation(summary = "Delete the document by ID")
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
        itemService.deleteById(id);
    }

}
