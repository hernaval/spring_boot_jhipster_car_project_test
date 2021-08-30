package com.hernaval.ctpn.service.mapper;

import com.hernaval.ctpn.domain.*;
import com.hernaval.ctpn.service.dto.ClientDTO;
import java.util.Set;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Client} and its DTO {@link ClientDTO}.
 */
@Mapper(componentModel = "spring", uses = { RoleMapper.class })
public interface ClientMapper extends EntityMapper<ClientDTO, Client> {
    @Mapping(target = "roles", source = "roles", qualifiedByName = "idSet")
    ClientDTO toDto(Client s);

    @Named("id")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ClientDTO toDtoId(Client client);

    @Mapping(target = "removeRole", ignore = true)
    Client toEntity(ClientDTO clientDTO);
}
