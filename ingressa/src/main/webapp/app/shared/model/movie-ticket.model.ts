export interface IMovieTicket {
  id?: number;
  name?: string | null;
  movieName?: string | null;
  sessionDate?: string | null;
  seatNumber?: string | null;
  creditCardNumber?: string | null;
}

export class MovieTicket implements IMovieTicket {
  constructor(
    public id?: number,
    public name?: string | null,
    public movieName?: string | null,
    public sessionDate?: string | null,
    public seatNumber?: string | null,
    public creditCardNumber?: string | null
  ) {}
}
