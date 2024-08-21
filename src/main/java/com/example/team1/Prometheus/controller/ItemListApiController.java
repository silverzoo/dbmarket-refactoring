package com.example.team1.Prometheus.controller;

import com.example.team1.Prometheus.entity.ItemListViewResponse;
import com.example.team1.Prometheus.service.ItemListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//아이템 목록 조회
@RestController
@RequiredArgsConstructor
public class ItemListApiController {

    private final ItemListService itemListService;
    @GetMapping("/api/itmes")
    public ResponseEntity<List<ItemListViewResponse>> getAllItems(){
        List<ItemListViewResponse> items = itemListService.getAllItems();
        return ResponseEntity.ok(items);
    }
}
