package com.example.team1.Prometheus.controller;

import com.example.team1.Prometheus.service.ItemDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ItemDetailApiController {
    private final ItemDetailService itemDetailService;

    // TODO : findItem()
    // TODO : updateItem()
    // TODO : deleteItem()
}
