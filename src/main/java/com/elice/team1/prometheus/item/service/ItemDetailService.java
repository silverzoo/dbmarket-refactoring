package com.elice.team1.prometheus.item.service;

import com.elice.team1.prometheus.item.dto.ItemDeleteResponse;
import com.elice.team1.prometheus.item.dto.ItemModifyRequest;
import com.elice.team1.prometheus.item.dto.ItemModifyResponse;
import com.elice.team1.prometheus.item.dto.ItemResponse;
import com.elice.team1.prometheus.item.entity.Item;
import com.elice.team1.prometheus.common.exception.NotFoundItemById;
import com.elice.team1.prometheus.common.exception.UnauthorizedDeleteByUser;
import com.elice.team1.prometheus.common.exception.UnauthorizedModifyByUser;
import com.elice.team1.prometheus.item.mapper.ItemMapper;
import com.elice.team1.prometheus.item.repository.ItemDetailRepository;
import com.elice.team1.prometheus.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemDetailService {
    private final ItemDetailRepository itemDetailRepository;
    private final UserService userService;
    private final ItemMapper itemMapper;

    @Transactional
    public ItemResponse viewItem(long id) {

        Item item = itemDetailRepository.findById(id)
                .orElseThrow(() ->  new NotFoundItemById(id));

        return itemMapper.toItemResponse(item);
    }

    @Transactional
    public ItemResponse findById(long id, HttpServletRequest httpServletRequest) {

        Item item = itemDetailRepository.findById(id)
                .orElseThrow(() -> new NotFoundItemById(id));

        Long userId = userService.getSessionUser(httpServletRequest).getUserId();
        String userName = userService.getSessionUser(httpServletRequest).getUserName();
        if(item.getUserId()!=(userId)) {
            throw new UnauthorizedModifyByUser(userName);
        }

        log.info("\n\n세션정보: {}\n\n", userId);

        return itemMapper.toItemResponse(item);
    }

    @Transactional
    public ItemModifyResponse updateItem(long id, ItemModifyRequest request) {

        // 1. 엔티티를 데이터베이스에서 조회
        Item item = itemDetailRepository.findById(id)
                .orElseThrow(() -> new NotFoundItemById(id));

        // 2. DTO를 엔티티로 변환
//        Item updatedItem = itemMapper.toEntity(request);

        // 3. 빌더 패턴을 사용하여 수정된 엔티티 생성
        Item finalItem = Item.builder()
                .itemId(item.getItemId())
                .userId(item.getUserId())
                .name(request.getName())
                .price(request.getPrice())
//                .categoryId(request.getCategoryId())
                .category(request.getCategory())
                .imagePath(request.getImagePath())
                .imagePath(request.getImagePath())
                .description(request.getDescription())
                .createdAt(item.getCreatedAt())
                .build();

        // 4. 업데이트된 엔티티를 저장
        Item savedItem = itemDetailRepository.save(finalItem);

        // 5. 저장된 엔티티를 DTO로 변환하여 반환
        return itemMapper.toModifyResponse(savedItem);
    }

    @Transactional
    public ItemDeleteResponse deleteItem(long id, HttpServletRequest httpServletRequest) {

        Item item = itemDetailRepository.findById(id)
                .orElseThrow(() -> new NotFoundItemById(id));

        Long userId = userService.getSessionUser(httpServletRequest).getUserId();
        String userName = userService.getSessionUser(httpServletRequest).getUserName();
        if(item.getUserId()!=(userId)) {
            throw new UnauthorizedDeleteByUser(userName);
        }

        itemDetailRepository.deleteById(id);

        return itemMapper.toDeleteResponse(item);
    }

}
