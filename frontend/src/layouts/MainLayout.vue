<template>
  <q-layout view="lHh Lpr lFf">
    <q-header elevated>
      <q-toolbar>
        <q-toolbar-title>
          Green Leaf
        </q-toolbar-title>

        <q-space/>

        <q-btn unelevated icon="dark_mode" @click="toggleDarkMode()"/>
        <q-btn unelevated v-if="$store.getters['credentials/isAuthenticated']" icon="logout" @click="logout"/>
      </q-toolbar>
    </q-header>

    <q-page-container>
      <router-view />
    </q-page-container>

    <q-footer>
      <q-tabs>
        <q-route-tab icon="home" to="/"/>
        <q-route-tab icon="edit" to="/statistics/form"/>
        <q-route-tab icon="settings" to="/user-settings"/>
      </q-tabs>
    </q-footer>
  </q-layout>
</template>

<script>
import { Dark } from 'quasar';

export default {
  name: 'MainLayout',
  setup () {
    function logout() {
      this.$store.commit("credentials/clearToken");
      this.$router.push('/login');
    }

    return {
      logout
    }
  },
  methods: {
    toggleDarkMode() {
      Dark.toggle();
    }
  }
}
</script>
