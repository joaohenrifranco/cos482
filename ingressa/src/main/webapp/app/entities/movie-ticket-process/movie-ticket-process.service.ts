import axios from 'axios';

import { IMovieTicketProcess } from '@/shared/model/movie-ticket-process.model';

const baseApiUrl = 'api/movie-ticket-processes';

export default class MovieTicketProcessService {
  public find(id: number): Promise<IMovieTicketProcess> {
    return new Promise<IMovieTicketProcess>((resolve, reject) => {
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

  public create(entity: IMovieTicketProcess): Promise<IMovieTicketProcess> {
    return new Promise<IMovieTicketProcess>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}`, entity)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
