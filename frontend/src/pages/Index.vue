<template>
  <q-page padding>
    <q-card class="my-card">
      <q-card-section>
        <div class="text-h6">{{ $t("index.next_level") }}</div>
        <q-linear-progress
          size="25px"
          :value="progress"
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
    <q-card class="my-card">
      <q-timeline color="secondary">
        <q-timeline-entry heading>
          {{ $t("index.activities") }}
        </q-timeline-entry>
        <q-timeline-entry title="Event Title" subtitle="06.05.2020">
          <div>
            Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do
            eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim
            ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut
            aliquip ex ea commodo consequat.
          </div>
        </q-timeline-entry>
        <q-timeline-entry title="Event Title" subtitle="05.05.2020">
          <div>
            Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do
            eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim
            ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut
            aliquip ex ea commodo consequat.
          </div>
        </q-timeline-entry>
        <q-timeline-entry title="Event Title" subtitle="04.05.2020">
          <div>
            Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do
            eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim
            ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut
            aliquip ex ea commodo consequat.
          </div>
        </q-timeline-entry>
        <q-timeline-entry title="Event Title" subtitle="03.05.2020">
          <div>
            Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do
            eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim
            ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut
            aliquip ex ea commodo consequat.
          </div>
        </q-timeline-entry>
        <q-timeline-entry title="Event Title" subtitle="02.05.2020">
          <div>
            Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do
            eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim
            ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut
            aliquip ex ea commodo consequat.
          </div>
        </q-timeline-entry>
        <q-timeline-entry title="Event Title" subtitle="01.05.2020">
          <div>
            Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do
            eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim
            ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut
            aliquip ex ea commodo consequat.
          </div>
        </q-timeline-entry>
      </q-timeline>
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
    const itemList = [
      {
        name: "favorite",
        label: "ba",
        caption: "ba"
      },
      {
        name: "star",
        label: "ba",
        caption: "ba"
      }
    ];

    return {
      progress: 0.65,
      points: 650,
      nextLevel: 1000,
      itemList
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
    // Fetch data
  }
};
</script>
