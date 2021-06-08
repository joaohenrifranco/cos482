package com.cos482.ingressa.process.movieTicketProcess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movie-ticket-process/task-choose-seat")
public class TaskChooseSeatController {

    private final Logger log = LoggerFactory.getLogger(TaskChooseSeatController.class);

    private final TaskChooseSeatService taskChooseSeatService;

    public TaskChooseSeatController(TaskChooseSeatService taskChooseSeatService) {
        this.taskChooseSeatService = taskChooseSeatService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskChooseSeatContextDTO> loadContext(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        TaskChooseSeatContextDTO taskChooseSeatContext = taskChooseSeatService.loadContext(id);
        return ResponseEntity.ok(taskChooseSeatContext);
    }

    @GetMapping("/{id}/claim")
    public ResponseEntity<TaskChooseSeatContextDTO> claim(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        TaskChooseSeatContextDTO taskChooseSeatContext = taskChooseSeatService.claim(id);
        return ResponseEntity.ok(taskChooseSeatContext);
    }

    @PostMapping("/complete")
    public ResponseEntity<Void> complete(@RequestBody TaskChooseSeatContextDTO taskChooseSeatContext) {
        log.debug("REST request to complete MovieTicketProcess.TaskChooseSeat {}", taskChooseSeatContext.getTaskInstance().getId());
        taskChooseSeatService.complete(taskChooseSeatContext);
        return ResponseEntity.noContent().build();
    }
}
