import axios from 'axios';
import { TaskSendPaymentDataContext } from './task-send-payment-data.model';

const baseApiUrl = 'api/movie-ticket-process/task-send-payment-data';

export default class TaskSendPaymentDataService {
  public loadContext(taskId: number): Promise<TaskSendPaymentDataContext> {
    return new Promise<TaskSendPaymentDataContext>((resolve, reject) => {
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

  public claim(taskId: number): Promise<TaskSendPaymentDataContext> {
    return new Promise<TaskSendPaymentDataContext>((resolve, reject) => {
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

  public complete(taskSendPaymentDataContext: TaskSendPaymentDataContext): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/complete`, taskSendPaymentDataContext)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
