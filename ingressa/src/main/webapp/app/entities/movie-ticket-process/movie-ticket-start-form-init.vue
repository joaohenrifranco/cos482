<template>
  <div class="row justify-content-center">
    <div class="col-10">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="ingressaApp.movieTicketStartForm.home.createOrEditLabel"
          data-cy="MovieTicketStartFormCreateUpdateHeading"
          v-text="$t('ingressaApp.movieTicketStartForm.home.createOrEditLabel')"
        >
          Create or edit a MovieTicketStartForm
        </h2>
        <div v-if="movieTicketProcess.processInstance">
          <akip-show-process-definition :processDefinition="movieTicketProcess.processInstance.processDefinition">
            <template v-slot:body>
              <hr />
              <div v-if="movieTicketProcess.movieTicket">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('ingressaApp.movieTicketStartForm.name')" for="movie-ticket-start-form-name"
                    >Name</label
                  >
                  <input
                    type="text"
                    class="form-control"
                    name="name"
                    id="movie-ticket-start-form-name"
                    data-cy="name"
                    :class="{
                      valid: !$v.movieTicketProcess.movieTicket.name.$invalid,
                      invalid: $v.movieTicketProcess.movieTicket.name.$invalid,
                    }"
                    v-model="$v.movieTicketProcess.movieTicket.name.$model"
                  />
                </div>
              </div>
            </template>
          </akip-show-process-definition>
          <br />
          <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
            <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
          </button>
          <button
            type="submit"
            id="save-entity"
            data-cy="entityCreateSaveButton"
            :disabled="$v.movieTicketProcess.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="play"></font-awesome-icon>&nbsp;<span>Start</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./movie-ticket-start-form-init.component.ts"></script>
