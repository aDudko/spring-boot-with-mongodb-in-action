package com.dudko.example.controller;

import com.dudko.example.model.Item;
import com.dudko.example.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/items", produces = "application/vnd.api.v1+json")
public class ItemController {

    private final ItemService itemService;


    @PostMapping
    public Item addDoc(@RequestBody Item item) {
        return itemService.save(item);
    }

    @GetMapping("/{id}")
    public Item findById(@PathVariable String id) {
        return itemService.findById(id);
    }

    @GetMapping
    public List<Item> findAll() {
        return itemService.findAll();
    }

    @PostMapping("/update")
    public Item update(@RequestBody Item item) {
        return itemService.update(item);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
        itemService.deleteById(id);
    }

}
