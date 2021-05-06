<template>
  <q-page class="q-ma-lg">
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
export default {
  name: "Login",
  data() {
    const username = '';
    const password = '';

    function onSubmit() {

    }

    return {
      username,
      password,
      onSubmit
    }
  }
}
</script>

<style scoped>

</style>
