package com.elice.team1.prometheus.comment.mapper;

import com.elice.team1.prometheus.comment.entity.Comment;
import com.elice.team1.prometheus.comment.dto.CommentRequest;
import com.elice.team1.prometheus.comment.dto.CommentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    Comment toEntity(CommentRequest commentRequest);

    CommentResponse toResponse(Comment comment);

}
