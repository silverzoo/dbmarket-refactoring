package com.example.team1.Prometheus.service;

import com.example.team1.Prometheus.entity.*;
import com.example.team1.Prometheus.mapper.ItemMapper;
import com.example.team1.Prometheus.repository.ItemDetailRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemDetailService {
    private final ItemDetailRepository itemDetailRepository;
    private final ItemMapper itemMapper;

    @Transactional
    public ItemResponse findById(long id) {

        Item item = itemDetailRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Item not found: " + id));

        return itemMapper.toItemResponse(item);
    }


    @Transactional
    public ItemModifyResponse updateItem(long id, ItemModifyRequest request) {

        // 1. 엔티티를 데이터베이스에서 조회
        Item item = itemDetailRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));

        // 2. DTO를 엔티티로 변환
        Item updatedItem = itemMapper.toEntity(request);

        // 3. 빌더 패턴을 사용하여 수정된 엔티티 생성
        // Q: 빌더 패턴으로 값을 수정할 때 수정데이터가 들어온 것만 업데이트를 하는 방법이 있을까?
        Item finalItem = Item.builder()
                .itemId(item.getItemId())
                .userId(item.getUserId())
                .name(updatedItem.getName())
                .price(item.getPrice())
                .category(item.getCategory())
                // .category(updatedItem.getCategory())
                .imagePath(item.getImagePath())
                // .imagePath(updatedItem.getImagePath())
                .description(updatedItem.getDescription())
                .createdAt(item.getCreatedAt())
                .build();

        // 4. 업데이트된 엔티티를 저장
        Item savedItem = itemDetailRepository.save(finalItem);

        // 5. 저장된 엔티티를 DTO로 변환하여 반환
        return itemMapper.toModifyResponse(savedItem);
    }

    @Transactional
    public ItemDeleteResponse deleteItem(long id) {

        Item item = itemDetailRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));

        itemDetailRepository.deleteById(id);

        return itemMapper.toDeleteResponse(item);
    }

}
