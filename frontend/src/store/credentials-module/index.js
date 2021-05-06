export default {
  namespaced: true,
  state: {
    jwtToken: String
  },
  getters: {
    getToken (state) {
      return state.jwtToken;
    },
    isAuthenticated (state) {
      return !!state.jwtToken;
    }
  },
  mutations: {
    setToken (state, jwtToken) {
      state.jwtToken = jwtToken;
    },
    clearToken (state) {
      state.jwtToken = undefined;
    }
  },
  actions: {

  }
}
