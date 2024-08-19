package com.example.team1.Prometheus.mapper;

// 지시사항에 맞게 코드를 수정해 주세요.

import com.example.team1.Prometheus.entity.Contact;
import com.example.team1.Prometheus.entity.ContactPostDto;
import com.example.team1.Prometheus.entity.ContactResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContactMapper {

    // 지시사항에 맞게 코드를 수정해 주세요.
    Contact toContact(ContactPostDto contactPostDto); //엔티티 반환
    ContactResponseDto toResponseDto(Contact contact); //responseDTO 반환

}
