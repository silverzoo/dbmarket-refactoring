package com.example.team1.Prometheus.service;

import com.example.team1.Prometheus.entity.ItemDetail;
import com.example.team1.Prometheus.repository.ItemDetailRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemDetailService {
    private final ItemDetailRepository itemDetailRepository;

    public ItemDetail findById(long id) {
        return itemDetailRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));
    }
}
