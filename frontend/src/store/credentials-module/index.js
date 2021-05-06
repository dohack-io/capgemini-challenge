export default {
  namespaced: true,
  state: {
    username: ''
  },
  getters: {
    getUsername (state) {
      return state.username;
    },
    isAuthenticated (state) {
      return state.username && typeof state.username === 'string';
    }
  },
  mutations: {
    setUsername (state, username) {
      state.username = username;
    },
    clearUsername (state) {
      state.username = undefined;
    }
  }
}
