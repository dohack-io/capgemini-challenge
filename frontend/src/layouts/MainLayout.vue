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
        <q-avatar v-if="$store.getters['credentials/isAuthenticated']">
          <img :src="$store.getters['credentials/getUser'].url"/>
        </q-avatar>
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

    <q-footer v-if="$store.getters['credentials/isAuthenticated']">
      <q-tabs align="center" class="text-dark bg-white shadow-2 height-md">
        <q-route-tab content-class="font-md" icon="home" to="/" />
        <q-route-tab content-class="font-md" icon="edit" to="/statistics/form" />
        <q-route-tab content-class="font-md" icon="today" to="/daily-challenge"/>
        <q-route-tab content-class="font-md" icon="format_list_numbered" to="/highscores"/>
      </q-tabs>
    </q-footer>
  </q-layout>
</template>

<script>
import { Dark } from "quasar";
import {getUserInfo} from "src/services/backendService";

export default {
  name: "MainLayout",
  setup() {
    function logout() {
      this.$store.commit("credentials/clearUser");
      this.$router.push("/login");
    }

    return {
      logout
    };
  },
  data() {
    const darkMode = Dark.isActive;
    const user = undefined;

    return {
      darkMode,
      user
    };
  },
  methods: {
    toggleDarkMode() {
      Dark.toggle();
      localStorage.setItem("darkMode", Dark.isActive);
    }
  },
  async mounted() {
    if (!this.$store.getters["credentials/isAuthenticated"]) {
      this.$router.push('/login');
    }
    Dark.set(localStorage.getItem('darkMode') === 'true');
  }
};
</script>

<style>
.font-md > i {
  font-size: 2rem !important;
}

.height-md {
  height: 4rem;
}
</style>
