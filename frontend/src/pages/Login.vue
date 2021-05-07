<template>
  <q-page class="q-ma-lg">
    <q-banner class="bg-negative text-white q-mb-lg" v-if="errors.length > 0">
      <div v-for="(error, index) in errors" :key="index">
        {{ error }}
      </div>
    </q-banner>

    <q-form ref="myForm" @submit="onSubmit">
      <div class="text-h4 text-no-wrap">{{ $t('login.title') }}</div>

      <div class="row q-mt-lg">
        <q-input class="col" outlined type="text" :label="$t('login.username')" v-model="username"/>
      </div>

      <div class="row q-mt-lg">
        <q-input class="col" outlined type="password" :label="$t('login.password')" v-model="password"/>
      </div>

      <div class="row q-mt-lg">
        <q-btn unelevated tile outline :label="$t('login.register')" to="/register" color="primary" class="q-mr-sm"/>
        <q-btn unelevated tile :label="$t('login.send')" type="submit" color="primary"/>
      </div>
    </q-form>
  </q-page>
</template>

<script>
import {ref} from "@vue/composition-api";
import {getUserInfo, login} from "src/services/backendService";

export default {
  name: "Login",
  setup() {
    const username = ref('');
    const password = ref('');
    const errors = ref([]);

    async function onSubmit() {
      this.errors = [];
      if (username.value && password.value) {
        const loginResult = await login(username.value, password.value);
        if (loginResult.ok) {
          const userResult = await getUserInfo(username.value);
          const user = await userResult.json();
          this.$store.commit("credentials/setUser", user);
          this.$router.push('/');
        } else {
          errors.value.push(this.$t('login.errors.usernamePasswordWrong'))
        }
      } else {
        errors.value.push(this.$t('login.errors.noUsernamePassword'));
      }
    }

    return {
      username,
      password,
      errors,
      onSubmit
    }
  }
}
</script>

<style scoped>

</style>
