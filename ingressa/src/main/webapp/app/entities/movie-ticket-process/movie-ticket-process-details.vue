<template>
  <div class="row justify-content-center">
    <div class="col-10">
      <div v-if="movieTicketProcess.processInstance">
        <h2 class="jh-entity-heading" data-cy="movieTicketProcessDetailsHeading">
          <span v-text="$t('ingressaApp.movieTicketProcess.detail.title')">MovieTicketProcess</span> {{ movieTicketProcess.id }}
        </h2>
        <akip-show-process-instance :processInstance="movieTicketProcess.processInstance">
          <template v-slot:title>
            <span v-text="$t('ingressaApp.movieTicketProcess.detail.title')">MovieTicketProcess</span> #{{ movieTicketProcess.id }}
          </template>
          <template v-slot:details>
            <div class="card" v-if="movieTicketProcess.movieTicket">
              <h5 class="card-header">
                <span class="title"> Movie Ticket #{{ movieTicketProcess.movieTicket.id }} </span>
              </h5>
              <div class="card-body">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('ingressaApp.movieTicketProcess.name')">name</label>
                  <input
                    readonly
                    type="text"
                    class="form-control form-control-sm"
                    name="name"
                    id="movie-ticket-name"
                    data-cy="name"
                    v-model="movieTicketProcess.movieTicket.name"
                  />
                </div>
              </div>
              <div class="card-body">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('ingressaApp.movieTicketProcess.movieName')">movieName</label>
                  <input
                    readonly
                    type="text"
                    class="form-control form-control-sm"
                    name="movieName"
                    id="movie-ticket-movieName"
                    data-cy="movieName"
                    v-model="movieTicketProcess.movieTicket.movieName"
                  />
                </div>
              </div>
              <div class="card-body">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('ingressaApp.movieTicketProcess.sessionDate')">sessionDate</label>
                  <input
                    readonly
                    type="text"
                    class="form-control form-control-sm"
                    name="sessionDate"
                    id="movie-ticket-sessionDate"
                    data-cy="sessionDate"
                    v-model="movieTicketProcess.movieTicket.sessionDate"
                  />
                </div>
              </div>
              <div class="card-body">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('ingressaApp.movieTicketProcess.seatNumber')">seatNumber</label>
                  <input
                    readonly
                    type="text"
                    class="form-control form-control-sm"
                    name="seatNumber"
                    id="movie-ticket-seatNumber"
                    data-cy="seatNumber"
                    v-model="movieTicketProcess.movieTicket.seatNumber"
                  />
                </div>
              </div>
              <div class="card-body">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('ingressaApp.movieTicketProcess.creditCardNumber')">creditCardNumber</label>
                  <input
                    readonly
                    type="text"
                    class="form-control form-control-sm"
                    name="creditCardNumber"
                    id="movie-ticket-creditCardNumber"
                    data-cy="creditCardNumber"
                    v-model="movieTicketProcess.movieTicket.creditCardNumber"
                  />
                </div>
              </div>
            </div>
          </template>
        </akip-show-process-instance>
        <br />
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./movie-ticket-process-details.component.ts"></script>
