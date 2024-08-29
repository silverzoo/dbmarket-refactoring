package com.example.team1.Prometheus.service;

import com.example.team1.Prometheus.entity.Item;
import com.example.team1.Prometheus.entity.ItemPostDto;
import com.example.team1.Prometheus.entity.User;
import com.example.team1.Prometheus.exception.ImageUploadException;
import com.example.team1.Prometheus.property.FileUploadProperties;
import com.example.team1.Prometheus.repository.ItemPostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

// TODO AOP 예외처리 or exception 처리
@Service
@Slf4j
//    file.{변수명} 값 주입, 자료 넘길시 사용
@RequiredArgsConstructor
public class RegisterItemService {
    private final ItemPostRepository itemPostRepository;
    private final FileUploadProperties fileUploadProperties;
    private String fullPath = "";
    private String dbImagePath = "";


    //TODO 아이템 업로드할때 쓰는 HttpServletRequest 에도 session이 있는가?
    //TODO ItemUploadException 체크
    public Item uploadItemToDb(ItemPostDto itemPostDto, User user) throws ImageUploadException {
        log.info("uploadItemToDb={}", user.getUserId());
//        편의 메서드
        log.info("size={}", itemPostDto.getItemImage().getSize()); //이미지 크기 체크
        log.info("submittedFileName={}", itemPostDto.getItemImage().getOriginalFilename());
        //uuid만 저장하면 uuid 파일이 완성됨, 확장자 필요.
        String extention = itemPostDto.getItemImage().getOriginalFilename()
                .substring(itemPostDto.getItemImage().getOriginalFilename().lastIndexOf("."));
        // 저장경로 설정해서 활용
//                String fullPath = fileDir + itemPostDto.getItemImage().getOriginalFilename();
        log.info("filedir = {}" , fileUploadProperties.getDir());
        String fileName = UUID.randomUUID() + extention;
        // UUID.randomUUID() 또 하면 값이 달라짐!
        if(fileUploadProperties.getEnvName().equals("dev")){
            fullPath = fileUploadProperties.getRootPath()+ fileUploadProperties.getDir() + fileName;
            //파일 저장하기(static/img 경로)
            //Dto 경로 저장
            dbImagePath = fileUploadProperties.getImagePath() + fileName;
        }else{
            fullPath = fileUploadProperties.getRootPath()+ fileUploadProperties.getDir() + fileName;
            dbImagePath = fileUploadProperties.getDir() + fileName;
        }
        log.info("파일 저장 fullPath={}", fullPath);
        // 저장 경로 생각해보기
        try {
            itemPostDto.getItemImage().transferTo(new File(fullPath));
        } catch (IOException e) {
            throw new ImageUploadException(e.getMessage());
        }
        return itemPostRepository.save(itemPostDto.toEntity(user.getUserId(), dbImagePath));
    }


    public void updateItemToDb(Long itemId, ItemPostDto itemPostDto) throws ImageUploadException {

            String extention = itemPostDto.getItemImage().getOriginalFilename()
                    .substring(itemPostDto.getItemImage().getOriginalFilename().lastIndexOf("."));
        String fileName = UUID.randomUUID() + extention;

        if(fileUploadProperties.getEnvName().equals("dev")){
            fullPath = fileUploadProperties.getRootPath()+ fileUploadProperties.getDir() + fileName;
            dbImagePath = fileUploadProperties.getImagePath() + fileName;
        }else{
            fullPath = fileUploadProperties.getRootPath()+ fileUploadProperties.getDir() + fileName;
            dbImagePath = fileUploadProperties.getDir() + fileName;
        }

        // Find the item using itemId
        Optional<Item> itemOptional = itemPostRepository.findById(itemId);

        if (!itemOptional.isPresent()) {
            throw new NoSuchElementException("Problem not found");
        }
        Item existingItem = itemOptional.get();
        // Update existingItem with updatedItemData using Builder pattern

        // Update existing with updatedData using Builder pattern
        Item updatedItem = Item.builder()
                .userId(existingItem.getUserId())
                .itemId(existingItem.getItemId())
                .name(itemPostDto.getItemInfo().getName())
                .price(itemPostDto.getItemInfo().getPrice())
                .category(itemPostDto.getItemInfo().getCategory())
                .imagePath(dbImagePath)
                .description(itemPostDto.getItemInfo().getImagePath()).build();

        try {
            itemPostDto.getItemImage().transferTo(new File(fullPath));
        } catch (IOException e) {
            throw new ImageUploadException(itemId);
        }
        //TODO 저장소 관리를 위해 기존 file삭제가 필요함
        // Save the updated
        itemPostRepository.save(updatedItem);
        fileDelete(existingItem.getImagePath());
    }
    public void fileDelete(String dir){
        File file = new File(dir);

        if( file.exists() ){ //파일존재여부확인

            if(file.isDirectory()){ //파일이 디렉토리인지 확인

                File[] files = file.listFiles();

                for( int i=0; i<files.length; i++){
                    if( files[i].delete() ){
                        System.out.println(files[i].getName()+" 삭제성공");
                    }else{
                        System.out.println(files[i].getName()+" 삭제실패");
                    }
                }

            }
            if(file.delete()){
                System.out.println("파일삭제 성공");
            }else{
                System.out.println("파일삭제 실패");
            }

        }else{
            System.out.println("파일이 존재하지 않습니다.");
        }
    }
}
// TODO Mapper 사용 Mapstruct 적용
//TODO repository 사용해서 데이터 저장만을 하는 서비스를 구현할 것