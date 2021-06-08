import { Component, Vue, Inject } from 'vue-property-decorator';

import { IMovieTicketProcess } from '@/shared/model/movie-ticket-process.model';
import MovieTicketProcessService from './movie-ticket-process.service';

@Component
export default class MovieTicketProcessDetailsComponent extends Vue {
  @Inject('movieTicketProcessService') private movieTicketProcessService: () => MovieTicketProcessService;
  public movieTicketProcess: IMovieTicketProcess = {};

  public isFetching: boolean = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.processInstanceId) {
        vm.retrieveMovieTicketProcess(to.params.processInstanceId);
      }
    });
  }

  public retrieveMovieTicketProcess(movieTicketProcessId) {
    this.isFetching = true;
    this.movieTicketProcessService()
      .find(movieTicketProcessId)
      .then(
        res => {
          this.movieTicketProcess = res;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public previousState() {
    this.$router.go(-1);
  }
}
