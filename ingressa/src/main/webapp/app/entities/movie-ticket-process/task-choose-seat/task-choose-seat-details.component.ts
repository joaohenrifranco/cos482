import { Component, Vue, Inject } from 'vue-property-decorator';

import TaskChooseSeatService from './task-choose-seat.service';
import { TaskChooseSeatContext } from './task-choose-seat.model';

@Component
export default class TaskChooseSeatDetailsComponent extends Vue {
  private taskChooseSeatService: TaskChooseSeatService = new TaskChooseSeatService();
  private taskContext: TaskChooseSeatContext = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.retrieveContext(to.params.taskInstanceId);
      }
    });
  }

  public retrieveContext(taskInstanceId) {
    this.taskChooseSeatService.loadContext(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
