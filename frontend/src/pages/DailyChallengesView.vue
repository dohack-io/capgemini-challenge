<template>
  <q-page class="q-ma-lg">
    <div class="text-h4">{{ $t('dailyChallengesView.title') }}</div>

    <q-inner-loading v-if="requestStatus === 'sending'">
      <q-spinner-gears size="50px" color="primary" />
    </q-inner-loading>

    <div v-if="requestStatus === 'success'" class="q-mt-lg">
      <div>{{ $t('dailyChallengesView.challengeName') }}: {{ dailyChallenge.title }}</div>
      <div>{{ $t('dailyChallengesView.description') }}: {{ dailyChallenge.description }}</div>
      <div>{{ $t('dailyChallengesView.points') }}: {{ dailyChallenge.challengePoints }}</div>
    </div>

    <q-banner class="bg-negative text-white" v-if="requestStatus === 'error'">
      {{ $t('dailyChallengesView.sending.error') }}
    </q-banner>
  </q-page>
</template>

<script>
export default {
  name: "DailyChallengesView",
  data() {
    const requestStatus = 'sending';
    const dailyChallenge = undefined;

    return {
      dailyChallenge,
      requestStatus
    }
  },
  async mounted() {
    const dailyChallengeResult = await fetch('http://localhost:8081/challenge/daily', {
      method: 'GET'
    });
    if (dailyChallengeResult.ok) {
      this.dailyChallenge = await dailyChallengeResult.json();
      this.requestStatus = 'success';
    } else {
      this.requestStatus = 'error';
    }
  }
}
</script>

<style scoped>

</style>
