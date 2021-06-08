import { Authority } from '@/shared/security/authority';
/* tslint:disable */
// prettier-ignore

// prettier-ignore
const MovieTicket = () => import('@/entities/movie-ticket/movie-ticket.vue');
// prettier-ignore
const MovieTicketDetails = () => import('@/entities/movie-ticket/movie-ticket-details.vue');
// prettier-ignore
const MovieTicketProcessDetails = () => import('@/entities/movie-ticket-process/movie-ticket-process-details.vue');
// prettier-ignore
const MovieTicketProcessList = () => import('@/entities/movie-ticket-process/movie-ticket-process-list.vue');
// prettier-ignore
const MovieTicketStartFormInit = () => import('@/entities/movie-ticket-process/movie-ticket-start-form-init.vue');
// prettier-ignore
const MovieTicketProcess_TaskChooseMovieAndSessionDetails = () => import('@/entities/movie-ticket-process/task-choose-movie-and-session/task-choose-movie-and-session-details.vue');
// prettier-ignore
const MovieTicketProcess_TaskChooseMovieAndSessionExecute = () => import('@/entities/movie-ticket-process/task-choose-movie-and-session/task-choose-movie-and-session-execute.vue');
// prettier-ignore
const MovieTicketProcess_TaskChooseSeatDetails = () => import('@/entities/movie-ticket-process/task-choose-seat/task-choose-seat-details.vue');
// prettier-ignore
const MovieTicketProcess_TaskChooseSeatExecute = () => import('@/entities/movie-ticket-process/task-choose-seat/task-choose-seat-execute.vue');
// prettier-ignore
const MovieTicketProcess_TaskSendPaymentDataDetails = () => import('@/entities/movie-ticket-process/task-send-payment-data/task-send-payment-data-details.vue');
// prettier-ignore
const MovieTicketProcess_TaskSendPaymentDataExecute = () => import('@/entities/movie-ticket-process/task-send-payment-data/task-send-payment-data-execute.vue');
// jhipster-needle-add-entity-to-router-import - JHipster will import entities to the router here

export default [
  {
    path: '/movie-ticket',
    name: 'MovieTicket',
    component: MovieTicket,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/movie-ticket/:movieTicketId/view',
    name: 'MovieTicketView',
    component: MovieTicketDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/MovieTicketProcess/instance/:processInstanceId/view',
    name: 'MovieTicketProcessView',
    component: MovieTicketProcessDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/MovieTicketProcess/instances',
    name: 'MovieTicketProcessList',
    component: MovieTicketProcessList,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/MovieTicketProcess/init',
    name: 'MovieTicketStartFormInit',
    component: MovieTicketStartFormInit,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/MovieTicketProcess/task/ChooseMovieAndSession/:taskInstanceId/view',
    name: 'MovieTicketProcess_TaskChooseMovieAndSessionDetails',
    component: MovieTicketProcess_TaskChooseMovieAndSessionDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/MovieTicketProcess/task/ChooseMovieAndSession/:taskInstanceId/execute',
    name: 'MovieTicketProcess_TaskChooseMovieAndSessionExecute',
    component: MovieTicketProcess_TaskChooseMovieAndSessionExecute,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/MovieTicketProcess/task/ChooseSeat/:taskInstanceId/view',
    name: 'MovieTicketProcess_TaskChooseSeatDetails',
    component: MovieTicketProcess_TaskChooseSeatDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/MovieTicketProcess/task/ChooseSeat/:taskInstanceId/execute',
    name: 'MovieTicketProcess_TaskChooseSeatExecute',
    component: MovieTicketProcess_TaskChooseSeatExecute,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/MovieTicketProcess/task/SendPaymentData/:taskInstanceId/view',
    name: 'MovieTicketProcess_TaskSendPaymentDataDetails',
    component: MovieTicketProcess_TaskSendPaymentDataDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/MovieTicketProcess/task/SendPaymentData/:taskInstanceId/execute',
    name: 'MovieTicketProcess_TaskSendPaymentDataExecute',
    component: MovieTicketProcess_TaskSendPaymentDataExecute,
    meta: { authorities: [Authority.USER] },
  },
  // jhipster-needle-add-entity-to-router - JHipster will add entities to the router here
];
