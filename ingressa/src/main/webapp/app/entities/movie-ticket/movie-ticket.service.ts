import axios from 'axios';

import { IMovieTicket } from '@/shared/model/movie-ticket.model';

const baseApiUrl = 'api/movie-tickets';

export default class MovieTicketService {
  public find(id: number): Promise<IMovieTicket> {
    return new Promise<IMovieTicket>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}/${id}`)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public retrieve(): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .get(baseApiUrl)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
