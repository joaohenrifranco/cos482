package com.cos482.ingressa.process.movieTicketProcess;

import com.cos482.ingressa.domain.MovieTicket;
import com.cos482.ingressa.domain.MovieTicketProcess;
import com.cos482.ingressa.service.dto.MovieTicketDTO;
import com.cos482.ingressa.service.dto.MovieTicketProcessDTO;
import com.cos482.ingressa.service.mapper.ProcessInstanceMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { ProcessInstanceMapper.class })
public interface TaskSendPaymentDataMapper {
    @Mapping(target = "processInstance", source = "processInstance", qualifiedByName = "loadTaskContext")
    MovieTicketProcessDTO toMovieTicketProcessDTO(MovieTicketProcess movieTicketProcess);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "creditCardNumber", source = "creditCardNumber")
    MovieTicketDTO toMovieTicketDTO(MovieTicket movieTicket);
}
