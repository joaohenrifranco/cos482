package com.cos482.ingressa.process.movieTicketProcess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movie-ticket-process/task-choose-movie-and-session")
public class TaskChooseMovieAndSessionController {

    private final Logger log = LoggerFactory.getLogger(TaskChooseMovieAndSessionController.class);

    private final TaskChooseMovieAndSessionService taskChooseMovieAndSessionService;

    public TaskChooseMovieAndSessionController(TaskChooseMovieAndSessionService taskChooseMovieAndSessionService) {
        this.taskChooseMovieAndSessionService = taskChooseMovieAndSessionService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskChooseMovieAndSessionContextDTO> loadContext(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        TaskChooseMovieAndSessionContextDTO taskChooseMovieAndSessionContext = taskChooseMovieAndSessionService.loadContext(id);
        return ResponseEntity.ok(taskChooseMovieAndSessionContext);
    }

    @GetMapping("/{id}/claim")
    public ResponseEntity<TaskChooseMovieAndSessionContextDTO> claim(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        TaskChooseMovieAndSessionContextDTO taskChooseMovieAndSessionContext = taskChooseMovieAndSessionService.claim(id);
        return ResponseEntity.ok(taskChooseMovieAndSessionContext);
    }

    @PostMapping("/complete")
    public ResponseEntity<Void> complete(@RequestBody TaskChooseMovieAndSessionContextDTO taskChooseMovieAndSessionContext) {
        log.debug(
            "REST request to complete MovieTicketProcess.TaskChooseMovieAndSession {}",
            taskChooseMovieAndSessionContext.getTaskInstance().getId()
        );
        taskChooseMovieAndSessionService.complete(taskChooseMovieAndSessionContext);
        return ResponseEntity.noContent().build();
    }
}
