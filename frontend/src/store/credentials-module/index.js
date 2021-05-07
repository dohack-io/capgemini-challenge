export default {
  namespaced: true,
  state: {
    user: undefined
  },
  getters: {
    getUser (state) {
      return state.user ? state.user : JSON.parse(sessionStorage.getItem('user'));
    },
    isAuthenticated (state) {
      return state.user && typeof state.user.username === 'string' ? true : !!sessionStorage.getItem('user');
    }
  },
  mutations: {
    setUser (state, user) {
      sessionStorage.setItem('user', JSON.stringify(user));
      state.user = user;
    },
    clearUser (state) {
      sessionStorage.removeItem('user');
      state.user = undefined;
    }
  }
}
