package com.example.team1.Prometheus.controller;

import com.example.team1.Prometheus.entity.ItemDeleteResponse;
import com.example.team1.Prometheus.service.ItemDetailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/items")
public class ItemDetailApiController {
    private final ItemDetailService itemDetailService;


    // NOTE : 상세페이지의 데이터 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<ItemDeleteResponse> deleteItem(@PathVariable("id") long id) {
        log.info("\n\n상품 삭제 되었나요?: {}\n\n", itemDetailService.deleteItem(id));

        return ResponseEntity.ok()
                .build();
    }
}
