import { Component, Vue, Inject } from 'vue-property-decorator';

import TaskSendPaymentDataService from './task-send-payment-data.service';
import { TaskSendPaymentDataContext } from './task-send-payment-data.model';

const validations: any = {
  taskContext: {
    movieTicketProcess: {
      movieTicket: {
        creditCardNumber: {},
      },
    },
  },
};

@Component({
  validations,
})
export default class TaskSendPaymentDataExecuteComponent extends Vue {
  private taskSendPaymentDataService: TaskSendPaymentDataService = new TaskSendPaymentDataService();
  private taskContext: TaskSendPaymentDataContext = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.claimTaskInstance(to.params.taskInstanceId);
      }
    });
  }

  public claimTaskInstance(taskInstanceId) {
    this.taskSendPaymentDataService.claim(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }

  public complete() {
    this.taskSendPaymentDataService.complete(this.taskContext).then(res => {
      this.$router.go(-1);
    });
  }
}
