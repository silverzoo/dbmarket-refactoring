package com.elice.team1.dbmarket.item.service;

import com.elice.team1.dbmarket.category.entity.Category;
import com.elice.team1.dbmarket.item.entity.Item;
import com.elice.team1.dbmarket.item.dto.ItemResponse;
import com.elice.team1.dbmarket.item.mapper.ItemMapper;
import com.elice.team1.dbmarket.item.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemListService {
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    public ItemListService(ItemRepository itemRepository, ItemMapper itemMapper){
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
    }

    public List<ItemResponse> getItemsByCategory(Category category) {
        List<Item> items = itemRepository.findByCategory(category);
        return items.stream()
                .filter(item -> item.getCategory().equals(category))
                .map(itemMapper::toItemResponse)
                .collect(Collectors.toList());
    }

    public List<ItemResponse> getOrderByDateDesc(Category category){
        List<Item> items = itemRepository.findByCategory(category);
        return items.stream()
                .filter(item -> item.getCategory().equals(category))
                .sorted(Comparator.comparing(Item::getCreatedAt))
                .map(itemMapper::toItemResponse)
                .collect(Collectors.toList());
    }

    public List<ItemResponse> getOrderByDateAsc(Category category) {
        List<Item> items = itemRepository.findByCategory(category);
        return items.stream()
                .filter(item -> item.getCategory().equals(category))
                .sorted(Comparator.comparing(Item::getCreatedAt).reversed())
                .map(itemMapper::toItemResponse)
                .collect(Collectors.toList());
    }
}
