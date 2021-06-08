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
public class TaskChooseSeatService {

    private final TaskInstanceService taskInstanceService;

    private final MovieTicketService movieTicketService;

    private final TaskInstanceRepository taskInstanceRepository;

    private final MovieTicketProcessRepository movieTicketProcessRepository;

    private final TaskInstanceMapper taskInstanceMapper;

    private final TaskChooseSeatMapper taskChooseSeatMapper;

    public TaskChooseSeatService(
        TaskInstanceService taskInstanceService,
        MovieTicketService movieTicketService,
        TaskInstanceRepository taskInstanceRepository,
        MovieTicketProcessRepository movieTicketProcessRepository,
        TaskInstanceMapper taskInstanceMapper,
        TaskChooseSeatMapper taskChooseSeatMapper
    ) {
        this.taskInstanceService = taskInstanceService;
        this.movieTicketService = movieTicketService;
        this.taskInstanceRepository = taskInstanceRepository;
        this.movieTicketProcessRepository = movieTicketProcessRepository;
        this.taskInstanceMapper = taskInstanceMapper;
        this.taskChooseSeatMapper = taskChooseSeatMapper;
    }

    public TaskChooseSeatContextDTO loadContext(Long taskInstanceId) {
        TaskInstanceDTO taskInstanceDTO = taskInstanceRepository
            .findById(taskInstanceId)
            .map(taskInstanceMapper::toDTOLoadTaskContext)
            .orElseThrow();

        MovieTicketProcessDTO movieTicketProcess = movieTicketProcessRepository
            .findByProcessInstanceId(taskInstanceDTO.getProcessInstance().getId())
            .map(taskChooseSeatMapper::toMovieTicketProcessDTO)
            .orElseThrow();

        TaskChooseSeatContextDTO taskChooseSeatContext = new TaskChooseSeatContextDTO();
        taskChooseSeatContext.setTaskInstance(taskInstanceDTO);
        taskChooseSeatContext.setMovieTicketProcess(movieTicketProcess);

        return taskChooseSeatContext;
    }

    public TaskChooseSeatContextDTO claim(Long taskInstanceId) {
        taskInstanceService.claim(taskInstanceId);
        return loadContext(taskInstanceId);
    }

    public void save(TaskChooseSeatContextDTO taskChooseSeatContext) {
        MovieTicketDTO movieTicketDTO = movieTicketService
            .findOne(taskChooseSeatContext.getMovieTicketProcess().getMovieTicket().getId())
            .orElseThrow();
        movieTicketDTO.setSeatNumber(taskChooseSeatContext.getMovieTicketProcess().getMovieTicket().getSeatNumber());
        movieTicketService.save(movieTicketDTO);
    }

    public void complete(TaskChooseSeatContextDTO taskChooseSeatContext) {
        save(taskChooseSeatContext);
        taskInstanceService.complete(taskChooseSeatContext.getTaskInstance(), taskChooseSeatContext.getMovieTicketProcess());
    }
}
