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
    setCognitoId (state, jwtToken) {
      state.jwtToken = jwtToken;
    }
  },
  actions: {

  }
}
