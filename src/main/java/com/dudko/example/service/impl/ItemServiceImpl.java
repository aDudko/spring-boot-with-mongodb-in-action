package com.dudko.example.service.impl;

import com.dudko.example.model.Item;
import com.dudko.example.repository.ItemRepository;
import com.dudko.example.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository repository;


    @Override
    public Item save(Item item) {
        return repository.save(new Item(UUID.randomUUID(), item.getContent()));
    }

    @Override
    public Item findById(String id) {
        return repository.findById(UUID.fromString(id)).orElseThrow();
    }

    @Override
    public List<Item> findAll() {
        return repository.findAll();
    }

    @Override
    public Item update(Item item) {
        Optional<Item> doc = repository.findById(item.getId());
        if (doc.isPresent()) {
            Item temp = doc.get();
            temp.setContent(item.getContent());
            return repository.save(temp);
        }
        return repository.save(item);
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(UUID.fromString(id));
    }

}
