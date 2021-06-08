import { Component, Vue, Inject } from 'vue-property-decorator';
import { IProcessDefinition } from '@/shared/model/process-definition.model';
import { IMovieTicketProcess } from '@/shared/model/movie-ticket-process.model';

import ProcessDefinitionService from '@/entities/process-definition/process-definition.service';
import MovieTicketProcessService from './movie-ticket-process.service';

@Component
export default class MovieTicketProcessListComponent extends Vue {
  @Inject('processDefinitionService') private processDefinitionService: () => ProcessDefinitionService;
  @Inject('movieTicketProcessService') private movieTicketProcessService: () => MovieTicketProcessService;

  public bpmnProcessDefinitionId: string = 'MovieTicketProcess';
  public processDefinition: IProcessDefinition = {};
  public movieTicketProcessList: IMovieTicketProcess[] = [];

  public isFetchingProcessDefinition = false;
  public isFetchingProcessInstances = false;

  public mounted(): void {
    this.init();
  }

  public init(): void {
    this.retrieveProcessDefinition();
    this.retrieveProcessInstances();
  }

  public retrieveProcessDefinition() {
    this.isFetchingProcessDefinition = true;
    this.processDefinitionService()
      .find(this.bpmnProcessDefinitionId)
      .then(
        res => {
          this.processDefinition = res;
          this.isFetchingProcessDefinition = false;
        },
        err => {
          this.isFetchingProcessDefinition = false;
        }
      );
  }

  public retrieveProcessInstances(): void {
    this.isFetchingProcessInstances = true;
    this.movieTicketProcessService()
      .retrieve()
      .then(
        res => {
          this.movieTicketProcessList = res.data;
          this.isFetchingProcessInstances = false;
        },
        err => {
          this.isFetchingProcessInstances = false;
        }
      );
  }

  get isFetching(): boolean {
    return this.isFetchingProcessDefinition && this.isFetchingProcessInstances;
  }

  public handleSyncList(): void {
    this.retrieveProcessInstances();
  }

  public previousState(): void {
    this.$router.go(-1);
  }
}
