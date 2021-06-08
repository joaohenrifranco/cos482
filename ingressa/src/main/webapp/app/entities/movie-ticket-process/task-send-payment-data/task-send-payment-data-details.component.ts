import { Component, Vue, Inject } from 'vue-property-decorator';

import TaskSendPaymentDataService from './task-send-payment-data.service';
import { TaskSendPaymentDataContext } from './task-send-payment-data.model';

@Component
export default class TaskSendPaymentDataDetailsComponent extends Vue {
  private taskSendPaymentDataService: TaskSendPaymentDataService = new TaskSendPaymentDataService();
  private taskContext: TaskSendPaymentDataContext = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.retrieveContext(to.params.taskInstanceId);
      }
    });
  }

  public retrieveContext(taskInstanceId) {
    this.taskSendPaymentDataService.loadContext(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
