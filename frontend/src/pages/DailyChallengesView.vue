<template>
  <q-page class="q-ma-lg">
    <div class="text-h4">{{ $t('dailyChallengesView.title') }}</div>

    <q-inner-loading v-if="requestStatus === 'sending'">
      <q-spinner-gears size="50px" color="primary" />
    </q-inner-loading>

    <div v-if="requestStatus === 'success'">
      <q-card class="q-mt-lg" v-for="(dailyChallenge, index) in dailyChallenges" :key="index">
        <q-img :src="dailyChallenge.url" basic>
          <div class="absolute-bottom text-subtitle2 text-center">
            {{ $t('dailyChallengesView.challengeName') }}: {{ dailyChallenge.title }}
          </div>
        </q-img>

        <q-card-section>
          <div>
            {{ $t('dailyChallengesView.date') }}:
            {{ date.formatDate(date.extractDate(dailyChallenge.date, 'YYYY-MM-DD'), 'DD.MM.YYYY') }}
          </div>
          <div>{{ $t('dailyChallengesView.description') }}: {{ dailyChallenge.description }}</div>
          <div>{{ $t('dailyChallengesView.points') }}: {{ dailyChallenge.challengePoints }}</div>
        </q-card-section>
      </q-card>
    </div>


    <q-banner class="bg-negative text-white" v-if="requestStatus === 'error'">
      {{ $t('dailyChallengesView.sending.error') }}
    </q-banner>
  </q-page>
</template>

<script>
import { date } from 'quasar';

export default {
  name: "DailyChallengesView",
  data() {
    const requestStatus = 'sending';
    const dailyChallenges = [];

    return {
      dailyChallenges,
      requestStatus,
      date
    }
  },
  async mounted() {
    const dailyChallengeResult = await fetch('http://localhost:8081/challenge/all', {
      method: 'GET'
    });
    if (dailyChallengeResult.ok) {
      const result = await dailyChallengeResult.json();
      this.dailyChallenges = result.slice(Math.max(result.length - 3, 1));
      this.requestStatus = 'success';
    } else {
      this.requestStatus = 'error';
    }
  }
}
</script>

<style scoped>

</style>
