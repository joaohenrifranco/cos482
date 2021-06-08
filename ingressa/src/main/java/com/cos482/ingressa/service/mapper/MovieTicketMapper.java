package com.cos482.ingressa.service.mapper;

import com.cos482.ingressa.domain.*;
import com.cos482.ingressa.service.dto.MovieTicketDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link MovieTicket} and its DTO {@link MovieTicketDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MovieTicketMapper extends EntityMapper<MovieTicketDTO, MovieTicket> {}
