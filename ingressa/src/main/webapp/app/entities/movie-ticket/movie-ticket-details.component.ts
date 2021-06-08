import { Component, Vue, Inject } from 'vue-property-decorator';

import { IMovieTicket } from '@/shared/model/movie-ticket.model';
import MovieTicketService from './movie-ticket.service';

@Component
export default class MovieTicketDetails extends Vue {
  @Inject('movieTicketService') private movieTicketService: () => MovieTicketService;
  public movieTicket: IMovieTicket = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.movieTicketId) {
        vm.retrieveMovieTicket(to.params.movieTicketId);
      }
    });
  }

  public retrieveMovieTicket(movieTicketId) {
    this.movieTicketService()
      .find(movieTicketId)
      .then(res => {
        this.movieTicket = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
