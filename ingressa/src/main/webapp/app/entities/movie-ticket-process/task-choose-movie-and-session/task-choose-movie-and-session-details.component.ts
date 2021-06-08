import { Component, Vue, Inject } from 'vue-property-decorator';

import TaskChooseMovieAndSessionService from './task-choose-movie-and-session.service';
import { TaskChooseMovieAndSessionContext } from './task-choose-movie-and-session.model';

@Component
export default class TaskChooseMovieAndSessionDetailsComponent extends Vue {
  private taskChooseMovieAndSessionService: TaskChooseMovieAndSessionService = new TaskChooseMovieAndSessionService();
  private taskContext: TaskChooseMovieAndSessionContext = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.retrieveContext(to.params.taskInstanceId);
      }
    });
  }

  public retrieveContext(taskInstanceId) {
    this.taskChooseMovieAndSessionService.loadContext(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
