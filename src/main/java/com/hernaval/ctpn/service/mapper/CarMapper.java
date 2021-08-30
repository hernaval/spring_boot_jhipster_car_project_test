package com.hernaval.ctpn.service.mapper;

import com.hernaval.ctpn.domain.*;
import com.hernaval.ctpn.service.dto.CarDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Car} and its DTO {@link CarDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CarMapper extends EntityMapper<CarDTO, Car> {
    @Named("id")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CarDTO toDtoId(Car car);
}
