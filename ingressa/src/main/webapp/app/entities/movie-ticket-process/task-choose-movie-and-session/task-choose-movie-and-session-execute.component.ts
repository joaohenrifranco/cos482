import { Component, Vue, Inject } from 'vue-property-decorator';

import TaskChooseMovieAndSessionService from './task-choose-movie-and-session.service';
import { TaskChooseMovieAndSessionContext } from './task-choose-movie-and-session.model';

const validations: any = {
  taskContext: {
    movieTicketProcess: {
      movieTicket: {
        movieName: {},
        sessionDate: {},
      },
    },
  },
};

@Component({
  validations,
})
export default class TaskChooseMovieAndSessionExecuteComponent extends Vue {
  private taskChooseMovieAndSessionService: TaskChooseMovieAndSessionService = new TaskChooseMovieAndSessionService();
  private taskContext: TaskChooseMovieAndSessionContext = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.claimTaskInstance(to.params.taskInstanceId);
      }
    });
  }

  public claimTaskInstance(taskInstanceId) {
    this.taskChooseMovieAndSessionService.claim(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }

  public complete() {
    this.taskChooseMovieAndSessionService.complete(this.taskContext).then(res => {
      this.$router.go(-1);
    });
  }
}
