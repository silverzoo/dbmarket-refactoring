package com.example.team1.Prometheus.controller;

import com.example.team1.Prometheus.entity.ItemDetail;
import com.example.team1.Prometheus.entity.ItemDetailRequest;
import com.example.team1.Prometheus.entity.ItemDetailResponse;
import com.example.team1.Prometheus.service.ItemDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/items")
public class ItemDetailApiController {
    private final ItemDetailService itemDetailService;

    // NOTE : 상세 페이지의 데이터 조회
    @GetMapping("/{id}")
    public ResponseEntity<ItemDetailResponse> findItem(@PathVariable("id") long id) {
        ItemDetailResponse itemDetailResponse = itemDetailService.findById(id);

        return ResponseEntity.ok().body(itemDetailResponse);
    }

    // NOTE : 상세페이지의 데이터 수정
    @PutMapping("/{id}")
    public ResponseEntity<ItemDetailResponse> updateItem(@PathVariable("id") long id, @RequestBody ItemDetailRequest request) {
        ItemDetailResponse response = itemDetailService.updateItem(id, request);

        return ResponseEntity.ok()
                .body(response);
    }


    // TODO : deleteItem()
}
