package com.elice.team1.prometheus.category.service;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class CategoryMappingService {
    private static final Map<String, Long> CATEGORY_MAP = new HashMap<>();

    static {
        CATEGORY_MAP.put("핸드폰", 1L);
        CATEGORY_MAP.put("의류", 2L);
        CATEGORY_MAP.put("반려동물", 3L);
        CATEGORY_MAP.put("서적", 4L);
        CATEGORY_MAP.put("기타", 5L);
    }

    public static Long getCategoryId(String category) {
        return CATEGORY_MAP.getOrDefault(category, null);
    }
}

