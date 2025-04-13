package com.tpnam.spring_boot.mapper;


import com.tpnam.spring_boot.dto.PostDTO;
import com.tpnam.spring_boot.entity.Post;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostMapper {
    PostDTO toDto(Post post);
    Post toEntity(PostDTO dto);
}
