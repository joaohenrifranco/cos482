package com.cos482.ingressa.process.movieTicketProcess;

import com.cos482.ingressa.repository.MovieTicketProcessRepository;
import com.cos482.ingressa.repository.TaskInstanceRepository;
import com.cos482.ingressa.service.MovieTicketService;
import com.cos482.ingressa.service.TaskInstanceService;
import com.cos482.ingressa.service.dto.MovieTicketDTO;
import com.cos482.ingressa.service.dto.MovieTicketProcessDTO;
import com.cos482.ingressa.service.dto.TaskInstanceDTO;
import com.cos482.ingressa.service.mapper.TaskInstanceMapper;
import org.springframework.stereotype.Component;

@Component
public class TaskSendPaymentDataService {

    private final TaskInstanceService taskInstanceService;

    private final MovieTicketService movieTicketService;

    private final TaskInstanceRepository taskInstanceRepository;

    private final MovieTicketProcessRepository movieTicketProcessRepository;

    private final TaskInstanceMapper taskInstanceMapper;

    private final TaskSendPaymentDataMapper taskSendPaymentDataMapper;

    public TaskSendPaymentDataService(
        TaskInstanceService taskInstanceService,
        MovieTicketService movieTicketService,
        TaskInstanceRepository taskInstanceRepository,
        MovieTicketProcessRepository movieTicketProcessRepository,
        TaskInstanceMapper taskInstanceMapper,
        TaskSendPaymentDataMapper taskSendPaymentDataMapper
    ) {
        this.taskInstanceService = taskInstanceService;
        this.movieTicketService = movieTicketService;
        this.taskInstanceRepository = taskInstanceRepository;
        this.movieTicketProcessRepository = movieTicketProcessRepository;
        this.taskInstanceMapper = taskInstanceMapper;
        this.taskSendPaymentDataMapper = taskSendPaymentDataMapper;
    }

    public TaskSendPaymentDataContextDTO loadContext(Long taskInstanceId) {
        TaskInstanceDTO taskInstanceDTO = taskInstanceRepository
            .findById(taskInstanceId)
            .map(taskInstanceMapper::toDTOLoadTaskContext)
            .orElseThrow();

        MovieTicketProcessDTO movieTicketProcess = movieTicketProcessRepository
            .findByProcessInstanceId(taskInstanceDTO.getProcessInstance().getId())
            .map(taskSendPaymentDataMapper::toMovieTicketProcessDTO)
            .orElseThrow();

        TaskSendPaymentDataContextDTO taskSendPaymentDataContext = new TaskSendPaymentDataContextDTO();
        taskSendPaymentDataContext.setTaskInstance(taskInstanceDTO);
        taskSendPaymentDataContext.setMovieTicketProcess(movieTicketProcess);

        return taskSendPaymentDataContext;
    }

    public TaskSendPaymentDataContextDTO claim(Long taskInstanceId) {
        taskInstanceService.claim(taskInstanceId);
        return loadContext(taskInstanceId);
    }

    public void save(TaskSendPaymentDataContextDTO taskSendPaymentDataContext) {
        MovieTicketDTO movieTicketDTO = movieTicketService
            .findOne(taskSendPaymentDataContext.getMovieTicketProcess().getMovieTicket().getId())
            .orElseThrow();
        movieTicketDTO.setCreditCardNumber(taskSendPaymentDataContext.getMovieTicketProcess().getMovieTicket().getCreditCardNumber());
        movieTicketService.save(movieTicketDTO);
    }

    public void complete(TaskSendPaymentDataContextDTO taskSendPaymentDataContext) {
        save(taskSendPaymentDataContext);
        taskInstanceService.complete(taskSendPaymentDataContext.getTaskInstance(), taskSendPaymentDataContext.getMovieTicketProcess());
    }
}
