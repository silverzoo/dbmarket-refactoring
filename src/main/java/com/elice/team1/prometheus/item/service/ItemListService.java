package com.elice.team1.prometheus.item.service;

import com.elice.team1.prometheus.category.entity.Category;
import com.elice.team1.prometheus.item.entity.Item;
import com.elice.team1.prometheus.item.dto.ItemResponse;
import com.elice.team1.prometheus.item.mapper.ItemMapper;
import com.elice.team1.prometheus.item.repository.ItemDetailRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemListService {
    private final ItemDetailRepository itemDetailRepository;
    private final ItemMapper itemMapper;

    public ItemListService(ItemDetailRepository itemDetailRepository, ItemMapper itemMapper){
        this.itemDetailRepository = itemDetailRepository;
        this.itemMapper = itemMapper;
    }

    public List<ItemResponse> getItemsByCategory(Category category) {
        List<Item> items = itemDetailRepository.findByCategory(category);
        return items.stream()
                .filter(item -> item.getCategory().equals(category))
                .map(itemMapper::toItemResponse)
                .collect(Collectors.toList());
    }

    public List<ItemResponse> getOrderByDateDesc(Category category){
        List<Item> items = itemDetailRepository.findByCategory(category);
        return items.stream()
                .filter(item -> item.getCategory().equals(category))
                .sorted(Comparator.comparing(Item::getCreatedAt))
                .map(itemMapper::toItemResponse)
                .collect(Collectors.toList());
    }

    public List<ItemResponse> getOrderByDateAsc(Category category) {
        List<Item> items = itemDetailRepository.findByCategory(category);
        return items.stream()
                .filter(item -> item.getCategory().equals(category))
                .sorted(Comparator.comparing(Item::getCreatedAt).reversed())
                .map(itemMapper::toItemResponse)
                .collect(Collectors.toList());
    }
}
