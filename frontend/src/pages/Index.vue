<template>
  <q-page class="q-pa-md">
    <q-card class="my-card q-mb-md" v-if="statisticsStatus === 'success'">
      <q-card-section>
        <div class="text-h6">{{ $t("index.nextLevel") }}</div>

        <q-linear-progress
          size="25px"
          :value="relativePoints"
          color="secondary"
          @click="randomize"
          label="Change Model"
        >
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
      {{ $t("index.sending.error") }}
    </q-banner>

    <div
      class="row items-start q-gutter-xs"
      v-if="statisticsStatus === 'success'"
    >
      <q-card class="my-tile">
        <q-list>
          <q-item clickable>
            <q-item-section avatar>
              <q-icon color="primary" name="fas fa-tree" />
            </q-item-section>
            <q-item-section>
              <q-item-label>Green Power</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable>
            <q-item-section>
              <q-item-label>Heute: {{ latestCo2Rounded }}kg CO²</q-item-label>
            </q-item-section>
          </q-item>
          <q-item clickable>
            <q-item-section>
              <q-item-label>Gesamt: {{ allCo2Rounded }}kg CO²</q-item-label>
            </q-item-section>
          </q-item>
          <q-item clickable>
            <q-item-section>
              <q-item-label>Bäume geplanzt: {{ savedTrees }}</q-item-label>
            </q-item-section>
          </q-item>
        </q-list>
      </q-card>
      <q-card class="my-tile">
        <q-list>
          <q-item clickable>
            <q-item-section avatar>
              <q-icon color="secondary" name="fas fa-trophy" />
            </q-item-section>
            <q-item-section>
              <q-item-label>Awards</q-item-label>
            </q-item-section>
          </q-item>
          <q-item clickable>
            <q-item-section>
              <q-item-label
                >Heute:
                <q-icon color="orange" size="25px" :name="dailyChallengeSolved"
              /></q-item-label>
            </q-item-section>
          </q-item>
          <q-item clickable>
            <q-item-section>
              <q-item-label>Geschafft: {{ allChallengesSolved }}</q-item-label>
            </q-item-section>
          </q-item>
        </q-list>
      </q-card>
    </div>
    <q-card
      v-if="requestStatus === 'success' && dailyChallenge"
      class="q-mt-lg"
    >
      <q-card-section>
        <div class="text-h6 q-mb-sm">{{ $t("index.scoreTips.title") }}</div>

        <q-chat-message
          bg-color="primary"
          text-color="white"
          :text="messageArray"
          :avatar="require('../assets/images/co2_guru.jpg')"
        />
      </q-card-section>
    </q-card>
  </q-page>
</template>

<style lang="sass" scoped>
.my-card
  width: 100%
  max-width: 90vw
.my-tile
  width: 100%
  max-width: 45vw
</style>

<script>
import commuteTypes from "src/model/CommuteTypes";
import {
  getAllChallenges,
  getAllDailyChallenges,
  getCurrentDailyChallenge
} from "src/services/backendService";

export default {
  name: "PageIndex",
  data() {
    const statisticsData = undefined;
    const points = 650;
    const nextLevel = 1000;
    const challengeList = [];
    const dailyChallenge = undefined;
    const requestStatus = "sending";
    const statisticsStatus = "sending";

    return {
      points,
      nextLevel,
      requestStatus,
      dailyChallenge,
      challengeList,
      statisticsData,
      statisticsStatus
    };
  },
  methods: {
    randomize() {
      this.progress = Math.random();
    }
  },
  computed: {
    progressLabel() {
      if (this.statisticsData && this.statisticsData.length > 0) {
        return (
          this.statisticsData[statisticsData.length - 1].levelProgression +
          "/" +
          this.statisticsData[statisticsData.length - 1].levelUpThreshold
        );
      }
      return 0;
    },
    relativePoints() {
      if (this.statisticsData && this.statisticsData.length > 0) {
        return (
          this.statisticsData[statisticsData.length - 1].levelProgression /
          this.statisticsData[statisticsData.length - 1].levelUpThreshold
        );
      }
      return 0;
    },
    latestCo2Rounded() {
      if (this.dailyChallenge) {
        return this.dailyChallenge.co2.toFixed(2);
      }
      return 0;
    },
    allCo2Rounded() {
      if (this.statisticsData && this.statisticsData.length > 0) {
        let result = 0;
        this.statisticsData.forEach(item => {
          result += item.co2;
        });
        return result;
      }
      return 0;
    },
    allChallengesSolved() {
      if (this.statisticsData && this.statisticsData.length > 0) {
        let result = 0;
        this.statisticsData.forEach(item => {
          result += item.dailyChallengePoints;
        });
        return result;
      }
      return 0;
    },
    dailyChallengeSolved() {
      if (this.dailyChallenge && this.dailyChallenge.dailyChallengePoints > 0) {
        return "fas fa-smile";
      }
      return "fas fa-frown";
    },
    savedTrees() {
      if (this.dailyChallenge) {
        return (
          this.dailyChallenge.pointsEarned / this.dailyChallenge.level
        ).toFixed(0);
      }
      return 0;
    },
    commuteScore() {
      if (this.dailyChallenge && this.dailyChallenge.commuteList.length > 0) {
        return this.dailyChallenge.commuteList
          .map(
            commute =>
              commute.distance *
              commuteTypes.find(type => type.value === commute.type).co2
          )
          .reduce((total, a) => total + a)
          .toFixed(2);
      }
      return 0;
    },
    messageArray() {
      const messageArray = [];
      if (this.dailyChallenge.numberOfCoffees > 5) {
        messageArray.push(this.$t("index.scoreTips.coffee.text"));
      }
      if (this.dailyChallenge.lunchScore > 5) {
        messageArray.push(this.$t("index.scoreTips.lunch.text"));
      }
      if (this.dailyChallenge.energyConsumption > 7.5) {
        messageArray.push(this.$t("index.scoreTips.energyConsumption.text"));
      }
      if (this.commuteScore > 5) {
        messageArray.push(this.$t("index.scoreTips.commute.text"));
      }
      return messageArray;
    }
  },
  async mounted() {
    const allStatisticsResult = await getAllDailyChallenges(
      this.$store.getters["credentials/getUser"].username
    );
    const statisticsResult = await getCurrentDailyChallenge(
      this.$store.getters["credentials/getUser"].username
    );
    if (allStatisticsResult.ok) {
      this.statisticsData = await allStatisticsResult.json();
      this.statisticsStatus = "success";
    }
    if (statisticsResult.ok) {
      try {
        this.dailyChallenge = await statisticsResult.json();
      } catch (e) {
        this.dailyChallenge = undefined;
      }
      this.requestStatus = "success";
    } else {
      this.requestStatus = "error";
    }
  }
};
</script>
