package com.elice.team1.dbmarket.item.dto;



import com.elice.team1.dbmarket.item.entity.Item;
import com.elice.team1.dbmarket.category.service.CategoryMappingService;
import com.elice.team1.dbmarket.common.validation.ValidFile;
import com.elice.team1.dbmarket.user.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@Setter
@Getter
public class ItemPostDto{

    private Item itemInfo;
    private CategoryMappingService categoryMapper;
//    @NotNull(message = "notnull 이미지를 넣어주세요") file은 null값 반환 X
//    @NotBlank(message = "blank 이미지를 넣어주세요") 이미지 작동 X
//    @NotEmpty(message = "empty 이미지를 넣어주세요") 이미지 작동 X

//    org.springframework.web.bind.MethodArgumentNotValidException: Validation failed for argument [0] in public java.lang.String
    // 리퀘스트 바디의 유효성이 실패했을 경우
    //BindException은 쿼리 파라미터의 유효성이 실패할경우의 예외에 대한 처리 코드이다.

//  java.io.IOException
//    @ValidFile(message = "이미지 파일은 필수입니다.")
    private MultipartFile itemImage;


    public Item toEntity(User user, String imagePath) {

        return Item.builder()
                .name(getItemInfo().getName())
                .price(getItemInfo().getPrice())
                .category(getItemInfo().getCategory())
                .imagePath(imagePath)
                .description(getItemInfo().getDescription())
                .build();
    }
}