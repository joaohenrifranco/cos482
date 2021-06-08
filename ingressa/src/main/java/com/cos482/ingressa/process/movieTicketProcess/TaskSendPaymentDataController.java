package com.cos482.ingressa.process.movieTicketProcess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movie-ticket-process/task-send-payment-data")
public class TaskSendPaymentDataController {

    private final Logger log = LoggerFactory.getLogger(TaskSendPaymentDataController.class);

    private final TaskSendPaymentDataService taskSendPaymentDataService;

    public TaskSendPaymentDataController(TaskSendPaymentDataService taskSendPaymentDataService) {
        this.taskSendPaymentDataService = taskSendPaymentDataService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskSendPaymentDataContextDTO> loadContext(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        TaskSendPaymentDataContextDTO taskSendPaymentDataContext = taskSendPaymentDataService.loadContext(id);
        return ResponseEntity.ok(taskSendPaymentDataContext);
    }

    @GetMapping("/{id}/claim")
    public ResponseEntity<TaskSendPaymentDataContextDTO> claim(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        TaskSendPaymentDataContextDTO taskSendPaymentDataContext = taskSendPaymentDataService.claim(id);
        return ResponseEntity.ok(taskSendPaymentDataContext);
    }

    @PostMapping("/complete")
    public ResponseEntity<Void> complete(@RequestBody TaskSendPaymentDataContextDTO taskSendPaymentDataContext) {
        log.debug(
            "REST request to complete MovieTicketProcess.TaskSendPaymentData {}",
            taskSendPaymentDataContext.getTaskInstance().getId()
        );
        taskSendPaymentDataService.complete(taskSendPaymentDataContext);
        return ResponseEntity.noContent().build();
    }
}
