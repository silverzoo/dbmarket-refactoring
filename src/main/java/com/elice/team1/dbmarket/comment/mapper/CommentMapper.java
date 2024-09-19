package com.elice.team1.dbmarket.comment.mapper;

import com.elice.team1.dbmarket.comment.entity.Comment;
import com.elice.team1.dbmarket.comment.dto.CommentRequest;
import com.elice.team1.dbmarket.comment.dto.CommentResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    Comment toEntity(CommentRequest commentRequest);

    CommentResponse toResponse(Comment comment);

}
