package com.hernaval.ctpn.service.mapper;

import com.hernaval.ctpn.domain.*;
import com.hernaval.ctpn.service.dto.CommentDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Comment} and its DTO {@link CommentDTO}.
 */
@Mapper(componentModel = "spring", uses = { ClientMapper.class, CarMapper.class })
public interface CommentMapper extends EntityMapper<CommentDTO, Comment> {
    @Mapping(target = "client", source = "client", qualifiedByName = "id")
    @Mapping(target = "car", source = "car", qualifiedByName = "id")
    CommentDTO toDto(Comment s);
}
