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
public class TaskChooseMovieAndSessionService {

    private final TaskInstanceService taskInstanceService;

    private final MovieTicketService movieTicketService;

    private final TaskInstanceRepository taskInstanceRepository;

    private final MovieTicketProcessRepository movieTicketProcessRepository;

    private final TaskInstanceMapper taskInstanceMapper;

    private final TaskChooseMovieAndSessionMapper taskChooseMovieAndSessionMapper;

    public TaskChooseMovieAndSessionService(
        TaskInstanceService taskInstanceService,
        MovieTicketService movieTicketService,
        TaskInstanceRepository taskInstanceRepository,
        MovieTicketProcessRepository movieTicketProcessRepository,
        TaskInstanceMapper taskInstanceMapper,
        TaskChooseMovieAndSessionMapper taskChooseMovieAndSessionMapper
    ) {
        this.taskInstanceService = taskInstanceService;
        this.movieTicketService = movieTicketService;
        this.taskInstanceRepository = taskInstanceRepository;
        this.movieTicketProcessRepository = movieTicketProcessRepository;
        this.taskInstanceMapper = taskInstanceMapper;
        this.taskChooseMovieAndSessionMapper = taskChooseMovieAndSessionMapper;
    }

    public TaskChooseMovieAndSessionContextDTO loadContext(Long taskInstanceId) {
        TaskInstanceDTO taskInstanceDTO = taskInstanceRepository
            .findById(taskInstanceId)
            .map(taskInstanceMapper::toDTOLoadTaskContext)
            .orElseThrow();

        MovieTicketProcessDTO movieTicketProcess = movieTicketProcessRepository
            .findByProcessInstanceId(taskInstanceDTO.getProcessInstance().getId())
            .map(taskChooseMovieAndSessionMapper::toMovieTicketProcessDTO)
            .orElseThrow();

        TaskChooseMovieAndSessionContextDTO taskChooseMovieAndSessionContext = new TaskChooseMovieAndSessionContextDTO();
        taskChooseMovieAndSessionContext.setTaskInstance(taskInstanceDTO);
        taskChooseMovieAndSessionContext.setMovieTicketProcess(movieTicketProcess);

        return taskChooseMovieAndSessionContext;
    }

    public TaskChooseMovieAndSessionContextDTO claim(Long taskInstanceId) {
        taskInstanceService.claim(taskInstanceId);
        return loadContext(taskInstanceId);
    }

    public void save(TaskChooseMovieAndSessionContextDTO taskChooseMovieAndSessionContext) {
        MovieTicketDTO movieTicketDTO = movieTicketService
            .findOne(taskChooseMovieAndSessionContext.getMovieTicketProcess().getMovieTicket().getId())
            .orElseThrow();
        movieTicketDTO.setMovieName(taskChooseMovieAndSessionContext.getMovieTicketProcess().getMovieTicket().getMovieName());
        movieTicketDTO.setSessionDate(taskChooseMovieAndSessionContext.getMovieTicketProcess().getMovieTicket().getSessionDate());
        movieTicketService.save(movieTicketDTO);
    }

    public void complete(TaskChooseMovieAndSessionContextDTO taskChooseMovieAndSessionContext) {
        save(taskChooseMovieAndSessionContext);
        taskInstanceService.complete(
            taskChooseMovieAndSessionContext.getTaskInstance(),
            taskChooseMovieAndSessionContext.getMovieTicketProcess()
        );
    }
}
