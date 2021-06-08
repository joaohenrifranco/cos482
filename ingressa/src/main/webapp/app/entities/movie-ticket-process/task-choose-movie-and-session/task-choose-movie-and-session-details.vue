<template>
  <div class="row justify-content-center">
    <div class="col-10">
      <h2 id="page-heading" data-cy="TaskInstanceHeading">
        <span v-text="$t('ingressaApp.taskInstance.details.title')" id="task-instance-heading">Task Details</span>
      </h2>
      <div v-if="taskContext.taskInstance">
        <akip-show-task-instance :taskInstance="taskContext.taskInstance">
          <template v-slot:body>
            <hr />
            <div class="form-group">
              <label class="form-control-label" v-text="$t('ingressaApp.taskChooseMovieAndSession.movieName')">movieName</label>
              <input
                readonly
                type="text"
                class="form-control form-control-sm"
                name="movieName"
                id="movie-ticket-movieName"
                data-cy="movieName"
                v-model="taskContext.movieTicketProcess.movieTicket.movieName"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('ingressaApp.taskChooseMovieAndSession.sessionDate')">sessionDate</label>
              <input
                readonly
                type="text"
                class="form-control form-control-sm"
                name="sessionDate"
                id="movie-ticket-sessionDate"
                data-cy="sessionDate"
                v-model="taskContext.movieTicketProcess.movieTicket.sessionDate"
              />
            </div>
          </template>
        </akip-show-task-instance>
        <br />
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>

        <router-link
          v-if="taskContext.taskInstance.status == 'NEW' || taskContext.taskInstance.status == 'ASSIGNED'"
          :to="`/process-definition/MovieTicketProcess/task/ChooseMovieAndSession/${taskContext.taskInstance.id}/execute`"
          tag="button"
          class="btn btn-primary"
          data-cy="entityDetailsButton"
        >
          <font-awesome-icon icon="play"></font-awesome-icon>&nbsp;Execute
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./task-choose-movie-and-session-details.component.ts"></script>
