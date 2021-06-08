import axios from 'axios';
import { TaskChooseSeatContext } from './task-choose-seat.model';

const baseApiUrl = 'api/movie-ticket-process/task-choose-seat';

export default class TaskChooseSeatService {
  public loadContext(taskId: number): Promise<TaskChooseSeatContext> {
    return new Promise<TaskChooseSeatContext>((resolve, reject) => {
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

  public claim(taskId: number): Promise<TaskChooseSeatContext> {
    return new Promise<TaskChooseSeatContext>((resolve, reject) => {
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

  public complete(taskChooseSeatContext: TaskChooseSeatContext): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/complete`, taskChooseSeatContext)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
