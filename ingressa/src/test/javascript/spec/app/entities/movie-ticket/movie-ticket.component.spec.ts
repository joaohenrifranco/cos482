/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import MovieTicketComponent from '@/entities/movie-ticket/movie-ticket.vue';
import MovieTicketClass from '@/entities/movie-ticket/movie-ticket.component';
import MovieTicketService from '@/entities/movie-ticket/movie-ticket.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('b-badge', {});
localVue.directive('b-modal', {});
localVue.component('b-button', {});
localVue.component('router-link', {});

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  describe('MovieTicket Management Component', () => {
    let wrapper: Wrapper<MovieTicketClass>;
    let comp: MovieTicketClass;
    let movieTicketServiceStub: SinonStubbedInstance<MovieTicketService>;

    beforeEach(() => {
      movieTicketServiceStub = sinon.createStubInstance<MovieTicketService>(MovieTicketService);
      movieTicketServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<MovieTicketClass>(MovieTicketComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          movieTicketService: () => movieTicketServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      movieTicketServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllMovieTickets();
      await comp.$nextTick();

      // THEN
      expect(movieTicketServiceStub.retrieve.called).toBeTruthy();
      expect(comp.movieTickets[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
