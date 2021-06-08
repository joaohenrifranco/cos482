<template>
  <div>
    <h2 id="page-heading" data-cy="MovieTicketHeading">
      <span v-text="$t('ingressaApp.movieTicket.home.title')" id="movie-ticket-heading">Movie Tickets</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('ingressaApp.movieTicket.home.refreshListLabel')">Refresh List</span>
        </button>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && movieTickets && movieTickets.length === 0">
      <span v-text="$t('ingressaApp.movieTicket.home.notFound')">No movieTickets found</span>
    </div>
    <div class="table-responsive" v-if="movieTickets && movieTickets.length > 0">
      <table class="table table-striped" aria-describedby="movieTickets">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span v-text="$t('ingressaApp.movieTicket.name')">Name</span></th>
            <th scope="row"><span v-text="$t('ingressaApp.movieTicket.movieName')">Movie Name</span></th>
            <th scope="row"><span v-text="$t('ingressaApp.movieTicket.sessionDate')">Session Date</span></th>
            <th scope="row"><span v-text="$t('ingressaApp.movieTicket.seatNumber')">Seat Number</span></th>
            <th scope="row"><span v-text="$t('ingressaApp.movieTicket.creditCardNumber')">Credit Card Number</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="movieTicket in movieTickets" :key="movieTicket.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'MovieTicketView', params: { movieTicketId: movieTicket.id } }">{{ movieTicket.id }}</router-link>
            </td>
            <td>{{ movieTicket.name }}</td>
            <td>{{ movieTicket.movieName }}</td>
            <td>{{ movieTicket.sessionDate }}</td>
            <td>{{ movieTicket.seatNumber }}</td>
            <td>{{ movieTicket.creditCardNumber }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'MovieTicketView', params: { movieTicketId: movieTicket.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <b-modal ref="removeEntity" id="removeEntity">
      <span slot="modal-title"
        ><span id="ingressaApp.movieTicket.delete.question" data-cy="movieTicketDeleteDialogHeading" v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-movieTicket-heading" v-text="$t('ingressaApp.movieTicket.delete.question', { id: removeId })">
          Are you sure you want to delete this Movie Ticket?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-movieTicket"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeMovieTicket()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./movie-ticket.component.ts"></script>
