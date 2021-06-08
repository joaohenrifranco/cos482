package com.cos482.ingressa.service.mapper;

import com.cos482.ingressa.domain.*;
import com.cos482.ingressa.service.dto.MovieTicketProcessDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link MovieTicketProcess} and its DTO {@link MovieTicketProcessDTO}.
 */
@Mapper(componentModel = "spring", uses = { ProcessInstanceMapper.class, MovieTicketMapper.class })
public interface MovieTicketProcessMapper extends EntityMapper<MovieTicketProcessDTO, MovieTicketProcess> {
    @Mapping(target = "processInstance", source = "processInstance")
    @Mapping(target = "movieTicket", source = "movieTicket")
    MovieTicketProcessDTO toDto(MovieTicketProcess s);
}
