package com.elice.team1.dbmarket.item.service;

import com.elice.team1.dbmarket.category.entity.Category;
import com.elice.team1.dbmarket.category.repository.CategoryRepository;
import com.elice.team1.dbmarket.common.exception.NotFoundCategoryById;
import com.elice.team1.dbmarket.item.dto.ItemDeleteResponse;
import com.elice.team1.dbmarket.item.dto.ItemModifyRequest;
import com.elice.team1.dbmarket.item.dto.ItemModifyResponse;
import com.elice.team1.dbmarket.item.dto.ItemResponse;
import com.elice.team1.dbmarket.item.entity.Item;
import com.elice.team1.dbmarket.common.exception.NotFoundItemById;
import com.elice.team1.dbmarket.common.exception.UnauthorizedDeleteByUser;
import com.elice.team1.dbmarket.common.exception.UnauthorizedModifyByUser;
import com.elice.team1.dbmarket.item.mapper.ItemMapper;
import com.elice.team1.dbmarket.item.repository.ItemRepository;
import com.elice.team1.dbmarket.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemDetailService {
    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;
    private final UserService userService;
    private final ItemMapper itemMapper;

    @Transactional
    public ItemResponse viewItem(long id) {

        Item item = itemRepository.findById(id)
                .orElseThrow(() ->  new NotFoundItemById(id));

        return itemMapper.toItemResponse(item);
    }

    @Transactional
    public ItemResponse findById(long id, HttpServletRequest httpServletRequest) {

        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new NotFoundItemById(id));

        Long userId = userService.getSessionUser(httpServletRequest).getId();
        String userName = userService.getSessionUser(httpServletRequest).getUsername();
        if(item.getUser().getId() != userId) {
            throw new UnauthorizedModifyByUser(userName);
        }

        log.info("\n\n세션정보: {}\n\n", userId);

        return itemMapper.toItemResponse(item);
    }

    @Transactional
    public ItemModifyResponse updateItem(long id, ItemModifyRequest request) {

        // 1. 엔티티를 데이터베이스에서 조회
        Item existingItem = itemRepository.findById(id)
                .orElseThrow(() -> new NotFoundItemById(id));

        log.info("\n\n수정 아이템 정보: {}\n\n", request.toString());
        log.info("\n\n수정 아이템의 카테고리정보: {}\n\n", request.getCategoryId());

        // 2. 카테고리 정보 가져오기
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new NotFoundCategoryById(request.getCategoryId()));

        // TODO: finalItem으로 새로 생성해서 빌더패턴 작성하지 않고 existingItem을 빌더패턴적용하고
        // save 없이도 업데이트 가능한지 보기

        // 3. 빌더 패턴을 사용하여 수정된 엔티티 생성
        Item finalItem = Item.builder()
                .id(existingItem.getId())
                .user(existingItem.getUser())
                .category(category)
                .name(request.getName())
                .price(request.getPrice())
                .imagePath(request.getImagePath())
                .description(request.getDescription())
                .build();

        // 4. 업데이트된 엔티티를 저장
        Item savedItem = itemRepository.save(finalItem);

        // 5. 저장된 엔티티를 DTO로 변환하여 반환
        return itemMapper.toModifyResponse(savedItem);
    }

    @Transactional
    public ItemDeleteResponse deleteItem(long id, HttpServletRequest httpServletRequest) {

        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new NotFoundItemById(id));

        Long userId = userService.getSessionUser(httpServletRequest).getId();
        String username = userService.getSessionUser(httpServletRequest).getUsername();
        if(item.getUser().getId() != userId) {
            throw new UnauthorizedDeleteByUser(username);
        }

        itemRepository.deleteById(id);

        return itemMapper.toDeleteResponse(item);
    }

}
