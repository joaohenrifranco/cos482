<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="movieTicket">
        <h2 class="jh-entity-heading" data-cy="movieTicketDetailsHeading">
          <span v-text="$t('ingressaApp.movieTicket.detail.title')">MovieTicket</span> {{ movieTicket.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('ingressaApp.movieTicket.name')">Name</span>
          </dt>
          <dd>
            <span>{{ movieTicket.name }}</span>
          </dd>
          <dt>
            <span v-text="$t('ingressaApp.movieTicket.movieName')">Movie Name</span>
          </dt>
          <dd>
            <span>{{ movieTicket.movieName }}</span>
          </dd>
          <dt>
            <span v-text="$t('ingressaApp.movieTicket.sessionDate')">Session Date</span>
          </dt>
          <dd>
            <span>{{ movieTicket.sessionDate }}</span>
          </dd>
          <dt>
            <span v-text="$t('ingressaApp.movieTicket.seatNumber')">Seat Number</span>
          </dt>
          <dd>
            <span>{{ movieTicket.seatNumber }}</span>
          </dd>
          <dt>
            <span v-text="$t('ingressaApp.movieTicket.creditCardNumber')">Credit Card Number</span>
          </dt>
          <dd>
            <span>{{ movieTicket.creditCardNumber }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./movie-ticket-details.component.ts"></script>
