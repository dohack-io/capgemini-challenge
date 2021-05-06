import Vue from 'vue';
import Vuex from 'vuex';
import credentialsModule from './credentials-module';

Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    credentials: credentialsModule
  }
});
