export default {
  namespaced: true,
  state: {
    username: String
  },
  getters: {
    getUsername (state) {
      return state.username;
    },
    isAuthenticated (state) {
      return !!state.username;
    }
  },
  mutations: {
    setUsername (state, username) {
      state.username = username;
    },
    clearUsername (state) {
      state.username = undefined;
    }
  },
  actions: {

  }
}
