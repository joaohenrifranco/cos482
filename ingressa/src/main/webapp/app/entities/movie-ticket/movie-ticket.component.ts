import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IMovieTicket } from '@/shared/model/movie-ticket.model';

import MovieTicketService from './movie-ticket.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class MovieTicket extends Vue {
  @Inject('movieTicketService') private movieTicketService: () => MovieTicketService;
  private removeId: number = null;

  public movieTickets: IMovieTicket[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllMovieTickets();
  }

  public clear(): void {
    this.retrieveAllMovieTickets();
  }

  public retrieveAllMovieTickets(): void {
    this.isFetching = true;

    this.movieTicketService()
      .retrieve()
      .then(
        res => {
          this.movieTickets = res.data;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public handleSyncList(): void {
    this.clear();
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
