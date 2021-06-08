package com.cos482.ingressa.process.movieTicketProcess;

import com.cos482.ingressa.service.dto.MovieTicketProcessDTO;
import com.cos482.ingressa.service.dto.TaskInstanceDTO;

public class TaskSendPaymentDataContextDTO {

    private MovieTicketProcessDTO movieTicketProcess;
    private TaskInstanceDTO taskInstance;

    public MovieTicketProcessDTO getMovieTicketProcess() {
        return movieTicketProcess;
    }

    public void setMovieTicketProcess(MovieTicketProcessDTO movieTicketProcess) {
        this.movieTicketProcess = movieTicketProcess;
    }

    public TaskInstanceDTO getTaskInstance() {
        return taskInstance;
    }

    public void setTaskInstance(TaskInstanceDTO taskInstance) {
        this.taskInstance = taskInstance;
    }
}
