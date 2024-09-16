package com.elice.team1.prometheus.comment.mapper;

import com.elice.team1.prometheus.comment.entity.Comment;
import com.elice.team1.prometheus.comment.dto.CommentRequest;
import com.elice.team1.prometheus.comment.dto.CommentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(source = "userId", target = "user.userId")
    Comment toEntity(CommentRequest commentRequest);

    @Mapping(source = "user.userId", target = "userId")
    CommentResponse toResponse(Comment comment);

}
