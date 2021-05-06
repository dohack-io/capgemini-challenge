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
      return state.username && typeof state.username === 'string' ? true : !!sessionStorage.getItem('username');
    }
  },
  mutations: {
    setUsername (state, username) {
      sessionStorage.setItem('username', username);
      state.username = username;
    },
    clearUsername (state) {
      sessionStorage.removeItem('username');
      state.username = undefined;
    }
  }
}
