<template>
  <q-page class="q-pa-md">
    <q-card class="my-card q-mb-md">
      <q-card-section>
        <div class="text-h6">{{ $t("index.nextLevel") }}</div>

        <q-linear-progress size="25px" :value="progress" color="secondary" @click="randomize" label="Change Model">
          <div class="absolute-full flex flex-center">
            <q-badge color="white" text-color="black" :label="progressLabel" />
          </div>
        </q-linear-progress>
      </q-card-section>
    </q-card>

    <q-inner-loading v-if="requestStatus === 'sending'">
      <q-spinner-gears size="50px" color="primary" />
    </q-inner-loading>

    <q-banner class="bg-negative text-white" v-if="requestStatus === 'error'">
      {{ $t('index.sending.error') }}
    </q-banner>

    <q-card class="my-card" v-if="requestStatus === 'success'">
      <q-card-section>
        <q-timeline color="secondary">
          <q-timeline-entry heading>
            <div class="text-h5">{{ $t("index.activities") }}</div>
          </q-timeline-entry>

          <q-timeline-entry v-for="(entry, index) in challengeList" :title="entry.title" :subtitle="entry.date"
                            :key="index">
            <div>{{ entry.description }}</div>
          </q-timeline-entry>
        </q-timeline>
      </q-card-section>
    </q-card>

    <q-card v-if="requestStatus === 'success' && dailyChallenge" class="q-mt-lg">
      <q-card-section>
        <div class="text-h6 q-mb-sm">{{ $t("index.scoreTips.title") }}</div>

        <q-chat-message bg-color="primary" text-color="white" :text="messageArray" :avatar="require('../assets/images/co2_guru.jpg')"/>
      </q-card-section>
    </q-card>
  </q-page>
</template>

<style lang="sass" scoped>
.my-card
  width: 100%
  max-width: 90vw
</style>

<script>
import commuteTypes from "src/model/CommuteTypes";

export default {
  name: "PageIndex",
  data() {
    const challengeList = [];
    const dailyChallenge = undefined;
    const requestStatus = 'sending';
    const scoreTips = [
      {key: 'energyConsumption', tip: this.$t('index.scoreTips.energyConsumption')},
      {key: 'lunch', tip: this.$t('index.scoreTips.lunch')},
      {key: 'coffee', tip: this.$t('index.scoreTips.coffee')},
      {key: 'commute', tip: this.$t('index.scoreTips.commute')}
    ]

    return {
      progress: 0.65,
      points: 650,
      nextLevel: 1000,
      requestStatus,
      dailyChallenge,
      challengeList
    };
  },
  methods: {
    randomize() {
      this.progress = Math.random();
    },
    relativePoints() {
      this.progress = this.points / this.nextLevel;
    }
  },
  computed: {
    progressLabel() {
      return this.points + "/" + this.nextLevel;
    },
    commuteScore() {
      if (this.dailyChallenge && this.dailyChallenge.commuteList.length > 0) {
        return this.dailyChallenge.commuteList
          .map(commute => commute.distance * commuteTypes.find(type => type.value === commute.type).co2)
          .reduce((total, a) => total + a).toFixed(2);
      }
      return 0;
    },
    messageArray() {
      const messageArray = [];
      if (this.dailyChallenge.numberOfCoffees > 5) {
        messageArray.push(this.$t('index.scoreTips.coffee.text'));
      }
      if (this.dailyChallenge.lunchScore > 5) {
        messageArray.push(this.$t('index.scoreTips.lunch.text'));
      }
      if (this.dailyChallenge.energyConsumption > 7.5) {
        messageArray.push(this.$t('index.scoreTips.energyConsumption.text'));
      }
      if (this.commuteScore > 5) {
        messageArray.push(this.$t('index.scoreTips.commute.text'));
      }
      return messageArray;
    }
  },
  async mounted() {
    const username = this.$store.getters["credentials/getUsername"];
    const requestResult = await fetch('http://localhost:8081/challenge/all', {
      method: 'GET'
    });
    const statisticsResult = await fetch(`http://localhost:8081/statistics/daily/${username}/current`, {
      method: 'GET'
    });
    if (requestResult.ok && statisticsResult.ok) {
      this.challengeList = await requestResult.json();
      try {
        this.dailyChallenge = await statisticsResult.json();
      } catch (e) {
        this.dailyChallenge = undefined;
      }
      console.log(this.dailyChallenge);
      this.requestStatus = 'success';
    } else {
      this.requestStatus = 'error';
    }
  }
};
</script>
