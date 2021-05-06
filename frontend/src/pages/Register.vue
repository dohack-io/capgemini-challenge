<template>
  <q-page class="q-ma-lg">
    <q-form ref="myForm" @submit="onSubmit">
      <div class="text-h4 text-no-wrap">{{ $t('register.title') }}</div>

      <div class="row q-mt-lg">
        <q-input class="col" outlined type="text" :label="$t('register.username')" v-model="username"/>
      </div>

      <div class="row q-mt-lg">
        <q-input class="col" outlined type="password" :label="$t('register.password')" v-model="password"/>
      </div>

      <div class="row q-mt-lg">
        <q-input class="col" outlined type="password" :label="$t('register.repeatPassword')" v-model="repeatPassword"/>
      </div>

      <div class="row q-mt-lg">
        <q-btn unelevated tile outline :label="$t('register.login')" to="/login" color="primary" class="q-mr-sm"/>
        <q-btn unelevated tile :label="$t('register.send')" type="submit" color="primary"/>
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
    const repeatPassword = '';

    function onSubmit() {

    }

    return {
      username,
      password,
      repeatPassword,
      onSubmit
    }
  }
}
</script>

<style scoped>

</style>
