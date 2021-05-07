<template>
  <q-layout view="lHh Lpr lFf">
    <q-header elevated>
      <q-toolbar>
        <q-icon name="fas fa-leaf" size="28px" color="white" />

        <q-space />

        <q-toggle
          v-model="darkMode"
          icon="dark_mode"
          color="secondary"
          unchecked-icon="light_mode"
          @input="toggleDarkMode()"
        />
        <q-btn flat icon="settings" to="/user-settings" />
        <q-btn
          flat
          v-if="$store.getters['credentials/isAuthenticated']"
          icon="logout"
          @click="logout"
        />
      </q-toolbar>
    </q-header>

    <q-page-container>
      <router-view />
    </q-page-container>

    <q-footer>
      <q-tabs class="text-dark bg-white shadow-2">
        <q-route-tab icon="home" to="/" />
        <q-route-tab icon="edit" to="/statistics/form" />
        <q-route-tab icon="today" to="/daily-challenge"/>
        <q-route-tab icon="format_list_numbered" to="/highscores"/>
      </q-tabs>
    </q-footer>
  </q-layout>
</template>

<script>
import { Dark } from "quasar";

export default {
  name: "MainLayout",
  setup() {
    function logout() {
      this.$store.commit("credentials/clearUsername");
      this.$router.push("/login");
    }

    return {
      logout
    };
  },
  data() {
    const darkMode = Dark.isActive;

    return {
      darkMode
    };
  },
  methods: {
    toggleDarkMode() {
      Dark.toggle();
      localStorage.setItem("darkMode", Dark.isActive);
    }
  },
  mounted() {
    if (!this.$store.getters["credentials/isAuthenticated"]) {
      this.$router.push('/login');
    }
    Dark.set(localStorage.getItem('darkMode') === 'true');
  }
};
</script>
