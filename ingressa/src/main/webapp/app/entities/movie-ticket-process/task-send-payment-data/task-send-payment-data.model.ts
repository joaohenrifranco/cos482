import { ITaskInstance } from '@/shared/model/task-instance.model';
import { IMovieTicketProcess } from '@/shared/model/movie-ticket-process.model';

export class TaskSendPaymentDataContext {
  taskInstance?: ITaskInstance = {};
  movieTicketProcess?: IMovieTicketProcess = {};
}
