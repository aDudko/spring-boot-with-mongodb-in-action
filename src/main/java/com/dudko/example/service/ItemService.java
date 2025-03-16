package com.dudko.example.service;

import com.dudko.example.model.Item;

import java.util.List;

public interface ItemService {

    Item save(Item item);

    Item getById(String id);

    List<Item> getAll();

    Item update(Item item);

    void deleteById(String id);

}
