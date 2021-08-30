package com.hernaval.ctpn.service.mapper;

import com.hernaval.ctpn.domain.*;
import com.hernaval.ctpn.service.dto.RoleDTO;
import java.util.Set;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Role} and its DTO {@link RoleDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RoleMapper extends EntityMapper<RoleDTO, Role> {
    @Named("idSet")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    Set<RoleDTO> toDtoIdSet(Set<Role> role);
}
