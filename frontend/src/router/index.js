import Vue from 'vue';
import VueRouter from 'vue-router';
import store from 'src/store';

Vue.use(VueRouter);

const routes = [
  {
    path: '/',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      { path: '', component: () => import('pages/Index.vue'), meta: { requiresAuth: true } },
      { path: 'statistics/form', component: () => import('pages/UserStatisticsForm.vue'), meta: { requiresAuth: true } },
      { path: 'user-settings', component: () => import('pages/UserSettings.vue'), meta: { requiresAuth: true } },
      { path: 'daily-challenge', component: () => import('pages/DailyChallengesView.vue'), meta: { requiresAuth: true } },
      { path: 'highscores', component: () => import('pages/HighScores.vue'), meta: { requiresAuth: true } },
      { path: 'login', component: () => import('pages/Login.vue'), meta: { requiresAuth: false } },
      { path: 'register', component: () => import('pages/Register.vue'), meta: { requiresAuth: false } },
    ]
  },
  {path: '*', component: () => import('pages/Error404.vue')}
];

const router = new VueRouter({
  scrollBehavior: () => ({ x: 0, y: 0 }),
  routes,
  mode: process.env.VUE_ROUTER_MODE,
  base: process.env.VUE_ROUTER_BASE
});

router.beforeResolve((to, from, next) => {
  if (to.matched.some(record => record.meta.requiresAuth)) {
    if (store.getters["credentials/isAuthenticated"]) {
      next();
    } else {
      next({path: '/login'});
    }
  }
  next();
});

export default router;
