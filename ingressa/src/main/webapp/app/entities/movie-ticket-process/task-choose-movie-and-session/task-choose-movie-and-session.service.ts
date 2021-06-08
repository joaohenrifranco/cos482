import axios from 'axios';
import { TaskChooseMovieAndSessionContext } from './task-choose-movie-and-session.model';

const baseApiUrl = 'api/movie-ticket-process/task-choose-movie-and-session';

export default class TaskChooseMovieAndSessionService {
  public loadContext(taskId: number): Promise<TaskChooseMovieAndSessionContext> {
    return new Promise<TaskChooseMovieAndSessionContext>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}/${taskId}`)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public claim(taskId: number): Promise<TaskChooseMovieAndSessionContext> {
    return new Promise<TaskChooseMovieAndSessionContext>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}/${taskId}/claim`)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public complete(taskChooseMovieAndSessionContext: TaskChooseMovieAndSessionContext): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/complete`, taskChooseMovieAndSessionContext)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
