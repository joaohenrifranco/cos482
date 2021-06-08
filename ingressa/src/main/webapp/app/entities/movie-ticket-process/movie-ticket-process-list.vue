<template>
  <div>
    <h2 class="jh-entity-heading" data-cy="movieTicketProcessDetailsHeading">
      <span v-text="$t('ingressaApp.movieTicketProcess.home.title')">MovieTicketProcess</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('ingressaApp.movieTicketProcess.home.refreshListLabel')">Refresh List</span>
        </button>

        <router-link :to="`/process-definition/MovieTicketProcess/init`" tag="button" class="btn btn-primary mr-2">
          <font-awesome-icon icon="plus"></font-awesome-icon>
          <span v-text="$t('ingressaApp.movieTicketProcess.home.createLabel')"> Create a new Travel Plan Process Instance </span>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && movieTicketProcessList && movieTicketProcessList.length === 0">
      <span v-text="$t('ingressaApp.movieTicketProcess.home.notFound')">No movieTicketProcess found</span>
    </div>
    <div class="table-responsive" v-if="movieTicketProcessList && movieTicketProcessList.length > 0">
      <table class="table table-striped" aria-describedby="movieTicketProcessList">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span v-text="$t('ingressaApp.movieTicketProcess.processInstance')">Process Instance</span></th>
            <th scope="row">Movie Ticket</th>
            <th scope="row"><span v-text="$t('ingressaApp.processInstance.status')">Status</span></th>
            <th scope="row"><span v-text="$t('ingressaApp.processInstance.startDate')">Start Date</span></th>
            <th scope="row"><span v-text="$t('ingressaApp.processInstance.endDate')">End Date</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="movieTicketProcess in movieTicketProcessList" :key="movieTicketProcess.id" data-cy="entityTable">
            <td>{{ movieTicketProcess.id }}</td>
            <td>
              <div v-if="movieTicketProcess.processInstance">
                <router-link :to="`/process-definition/MovieTicketProcess/instance/${movieTicketProcess.processInstance.id}/view`">
                  {{ movieTicketProcess.processInstance.businessKey }}
                </router-link>
              </div>
            </td>
            <td>
              <div v-if="movieTicketProcess.movieTicket">
                <router-link :to="{ name: 'MovieTicketView', params: { movieTicketId: movieTicketProcess.movieTicket.id } }">{{
                  movieTicketProcess.movieTicket.id
                }}</router-link>
              </div>
            </td>
            <td>
              <akip-show-process-instance-status :status="movieTicketProcess.processInstance.status"></akip-show-process-instance-status>
            </td>
            <td>
              {{
                movieTicketProcess.processInstance.startDate ? $d(Date.parse(movieTicketProcess.processInstance.startDate), 'short') : ''
              }}
            </td>
            <td>
              {{ movieTicketProcess.processInstance.endDate ? $d(Date.parse(movieTicketProcess.processInstance.endDate), 'short') : '' }}
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="`/process-definition/MovieTicketProcess/instance/${movieTicketProcess.processInstance.id}/view`"
                  tag="button"
                  class="btn btn-info btn-sm details"
                  data-cy="entityDetailsButton"
                >
                  <font-awesome-icon icon="eye"></font-awesome-icon>
                  <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                </router-link>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
      <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
    </button>
  </div>
</template>

<script lang="ts" src="./movie-ticket-process-list.component.ts"></script>
