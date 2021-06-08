import { Component, Vue, Inject } from 'vue-property-decorator';

import { IMovieTicketProcess, MovieTicketProcess } from '@/shared/model/movie-ticket-process.model';
import { ProcessInstance } from '@/shared/model/process-instance.model';
import { MovieTicket } from '@/shared/model/movie-ticket.model';

import MovieTicketProcessService from './movie-ticket-process.service';
import ProcessDefinitionService from '@/entities/process-definition/process-definition.service';

const validations: any = {
  movieTicketProcess: {
    movieTicket: {
      name: {},
    },
  },
};

@Component({
  validations,
})
export default class MovieTicketStartFormInitComponent extends Vue {
  @Inject('movieTicketProcessService') private movieTicketProcessService: () => MovieTicketProcessService;
  @Inject('processDefinitionService') private processDefinitionService: () => ProcessDefinitionService;

  public bpmnProcessDefinitionId: string = 'MovieTicketProcess';
  public movieTicketProcess: IMovieTicketProcess = new MovieTicketProcess();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      vm.initMovieTicketStartForm();
      vm.initRelationships();
    });
  }

  created(): void {
    this.currentLanguage = this.$store.getters.currentLanguage;
    this.$store.watch(
      () => this.$store.getters.currentLanguage,
      () => {
        this.currentLanguage = this.$store.getters.currentLanguage;
      }
    );
  }

  public save(): void {
    this.isSaving = true;

    this.movieTicketProcessService()
      .create(this.movieTicketProcess)
      .then(param => {
        this.isSaving = false;
        this.$router.go(-1);
        const message = this.$t('ingressaApp.movieTicketStartForm.created', { param: param.id });
        this.$root.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Success',
          variant: 'success',
          solid: true,
          autoHideDelay: 5000,
        });
      });
  }

  public initMovieTicketStartForm(): void {
    this.movieTicketProcess.movieTicket = new MovieTicket();
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.processDefinitionService()
      .find(this.bpmnProcessDefinitionId)
      .then(res => {
        this.movieTicketProcess.processInstance = new ProcessInstance();
        this.movieTicketProcess.processInstance.processDefinition = res;
      });
  }
}
