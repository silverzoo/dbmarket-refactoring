package com.example.team1.Prometheus.mapper;

import com.example.team1.Prometheus.entity.Comment;
import com.example.team1.Prometheus.entity.CommentRequest;
import com.example.team1.Prometheus.entity.CommentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(source = "userId", target = "user.userId")
    Comment toEntity(CommentRequest commentRequest);

    @Mapping(source = "user.userId", target = "userId")
    CommentResponse toResponse(Comment comment);

}
