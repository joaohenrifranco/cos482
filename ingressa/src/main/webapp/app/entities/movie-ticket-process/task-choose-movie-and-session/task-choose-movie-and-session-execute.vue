<template>
  <div class="row justify-content-center">
    <div class="col-10">
      <div v-if="taskContext.taskInstance">
        <h2 id="page-heading" data-cy="TaskInstanceHeading">
          <span v-text="$t('ingressaApp.taskInstance.execute.title')" id="task-instance-heading">Task Execution</span>
        </h2>
        <akip-show-task-instance :taskInstance="taskContext.taskInstance">
          <template v-slot:body>
            <hr />
            <div class="form-group">
              <label class="form-control-label" v-text="$t('ingressaApp.taskChooseMovieAndSession.movieName')" for="movie-ticket-movieName"
                >movieName</label
              >
              <input
                type="text"
                class="form-control"
                name="movieName"
                id="task-choose-movie-and-session-movieName"
                data-cy="movieName"
                :class="{
                  valid: !$v.taskContext.movieTicketProcess.movieTicket.movieName.$invalid,
                  invalid: $v.taskContext.movieTicketProcess.movieTicket.movieName.$invalid,
                }"
                v-model="$v.taskContext.movieTicketProcess.movieTicket.movieName.$model"
              />
            </div>
            <div class="form-group">
              <label
                class="form-control-label"
                v-text="$t('ingressaApp.taskChooseMovieAndSession.sessionDate')"
                for="movie-ticket-sessionDate"
                >sessionDate</label
              >
              <input
                type="text"
                class="form-control"
                name="sessionDate"
                id="task-choose-movie-and-session-sessionDate"
                data-cy="sessionDate"
                :class="{
                  valid: !$v.taskContext.movieTicketProcess.movieTicket.sessionDate.$invalid,
                  invalid: $v.taskContext.movieTicketProcess.movieTicket.sessionDate.$invalid,
                }"
                v-model="$v.taskContext.movieTicketProcess.movieTicket.sessionDate.$model"
              />
            </div>
          </template>
        </akip-show-task-instance>
        <br />
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <button type="submit" v-on:click.prevent="complete()" class="btn btn-success" data-cy="complete">
          <font-awesome-icon icon="check-circle"></font-awesome-icon>&nbsp;Complete
        </button>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./task-choose-movie-and-session-execute.component.ts"></script>
