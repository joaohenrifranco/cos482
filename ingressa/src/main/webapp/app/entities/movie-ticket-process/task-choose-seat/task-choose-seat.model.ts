import { ITaskInstance } from '@/shared/model/task-instance.model';
import { IMovieTicketProcess } from '@/shared/model/movie-ticket-process.model';

export class TaskChooseSeatContext {
  taskInstance?: ITaskInstance = {};
  movieTicketProcess?: IMovieTicketProcess = {};
}
