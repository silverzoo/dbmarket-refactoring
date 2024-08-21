package com.example.team1.Prometheus.service;

import com.example.team1.Prometheus.entity.ItemDetail;
import com.example.team1.Prometheus.entity.ItemListViewResponse;
import com.example.team1.Prometheus.repository.ItemDetailRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemListService {
    private final ItemDetailRepository itemDetailRepository;

    public ItemListService(ItemDetailRepository itemDetailRepository){
        this.itemDetailRepository = itemDetailRepository;
    }

    //모든 아이템 정보를 DTO로 변환 후 리스트로 리턴
    public List<ItemListViewResponse> getAllItems(){
        List<ItemDetail> itemDetails = itemDetailRepository.findAll();
        return itemDetails.stream()
                .map(ItemListViewResponse::new)
                .collect(Collectors.toList());
    }

}
