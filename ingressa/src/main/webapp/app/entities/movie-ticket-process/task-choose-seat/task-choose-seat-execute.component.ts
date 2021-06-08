import { Component, Vue, Inject } from 'vue-property-decorator';

import TaskChooseSeatService from './task-choose-seat.service';
import { TaskChooseSeatContext } from './task-choose-seat.model';

const validations: any = {
  taskContext: {
    movieTicketProcess: {
      movieTicket: {
        seatNumber: {},
      },
    },
  },
};

@Component({
  validations,
})
export default class TaskChooseSeatExecuteComponent extends Vue {
  private taskChooseSeatService: TaskChooseSeatService = new TaskChooseSeatService();
  private taskContext: TaskChooseSeatContext = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.claimTaskInstance(to.params.taskInstanceId);
      }
    });
  }

  public claimTaskInstance(taskInstanceId) {
    this.taskChooseSeatService.claim(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }

  public complete() {
    this.taskChooseSeatService.complete(this.taskContext).then(res => {
      this.$router.go(-1);
    });
  }
}
