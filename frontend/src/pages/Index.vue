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
            {{ $t("index.activities") }}
          </q-timeline-entry>

          <q-timeline-entry v-for="(entry, index) in challengeList" :title="entry.title" :subtitle="entry.date"
                            :key="index">
            <div>{{ entry.description }}</div>
          </q-timeline-entry>
        </q-timeline>
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
export default {
  name: "PageIndex",
  data() {
    const challengeList = [];
    const requestStatus = 'sending';

    return {
      progress: 0.65,
      points: 650,
      nextLevel: 1000,
      requestStatus,
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
    }
  },
  async mounted() {
    const requestResult = await fetch('http://localhost:8081/challenge/all', {
      method: 'GET'
    });
    if (requestResult.ok) {
      this.challengeList = await requestResult.json();
      this.requestStatus = 'success'
    } else {
      this.requestStatus = 'error'
    }
  }
};
</script>
