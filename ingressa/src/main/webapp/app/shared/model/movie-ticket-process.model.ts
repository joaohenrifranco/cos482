import { IProcessInstance } from '@/shared/model/process-instance.model';
import { IMovieTicket } from '@/shared/model/movie-ticket.model';

export interface IMovieTicketProcess {
  id?: number;
  processInstance?: IProcessInstance | null;
  movieTicket?: IMovieTicket | null;
}

export class MovieTicketProcess implements IMovieTicketProcess {
  constructor(public id?: number, public processInstance?: IProcessInstance | null, public movieTicket?: IMovieTicket | null) {}
}
