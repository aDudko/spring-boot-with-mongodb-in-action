package com.dudko.example.service;

import com.dudko.example.model.Item;

import java.util.List;


public interface ItemService {

    Item save(Item item);

    Item findById(String id);

    List<Item> findAll();

    Item update(Item item);

    void deleteById(String id);

}
