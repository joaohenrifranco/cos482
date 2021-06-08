/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import MovieTicketDetailComponent from '@/entities/movie-ticket/movie-ticket-details.vue';
import MovieTicketClass from '@/entities/movie-ticket/movie-ticket-details.component';
import MovieTicketService from '@/entities/movie-ticket/movie-ticket.service';
import router from '@/router';

const localVue = createLocalVue();
localVue.use(VueRouter);

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('MovieTicket Management Detail Component', () => {
    let wrapper: Wrapper<MovieTicketClass>;
    let comp: MovieTicketClass;
    let movieTicketServiceStub: SinonStubbedInstance<MovieTicketService>;

    beforeEach(() => {
      movieTicketServiceStub = sinon.createStubInstance<MovieTicketService>(MovieTicketService);

      wrapper = shallowMount<MovieTicketClass>(MovieTicketDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { movieTicketService: () => movieTicketServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundMovieTicket = { id: 123 };
        movieTicketServiceStub.find.resolves(foundMovieTicket);

        // WHEN
        comp.retrieveMovieTicket(123);
        await comp.$nextTick();

        // THEN
        expect(comp.movieTicket).toBe(foundMovieTicket);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundMovieTicket = { id: 123 };
        movieTicketServiceStub.find.resolves(foundMovieTicket);

        // WHEN
        comp.beforeRouteEnter({ params: { movieTicketId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.movieTicket).toBe(foundMovieTicket);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        comp.previousState();
        await comp.$nextTick();

        expect(comp.$router.currentRoute.fullPath).toContain('/');
      });
    });
  });
});
