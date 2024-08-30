package com.example.team1.Prometheus.service;

import com.example.team1.Prometheus.entity.Item;
import com.example.team1.Prometheus.entity.ItemListViewResponse;
import com.example.team1.Prometheus.entity.ItemResponse;
import com.example.team1.Prometheus.mapper.ItemMapper;
import com.example.team1.Prometheus.repository.ItemDetailRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
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

    public List<ItemResponse> getItemsByCategory(Long categoryId) {
        List<Item> items = itemDetailRepository.findByCategoryId(categoryId);
        return items.stream()
                .filter(item -> item.getCategoryId().equals(categoryId))
                .map(itemMapper::toItemResponse)
                .collect(Collectors.toList());
    }

    public List<ItemResponse> getOrderByDateDesc(Long categoryId){
        List<Item> items = itemDetailRepository.findByCategoryId(categoryId);
        return items.stream()
                .filter(item -> item.getCategoryId().equals(categoryId))
                .sorted(Comparator.comparing(Item::getCreatedAt))
                .map(itemMapper::toItemResponse)
                .collect(Collectors.toList());
    }

    public List<ItemResponse> getOrderByDateAsc(Long categoryId) {
        List<Item> items = itemDetailRepository.findByCategoryId(categoryId);
        return items.stream()
                .filter(item -> item.getCategoryId().equals(categoryId))
                .sorted(Comparator.comparing(Item::getCreatedAt).reversed())
                .map(itemMapper::toItemResponse)
                .collect(Collectors.toList());
    }
}
