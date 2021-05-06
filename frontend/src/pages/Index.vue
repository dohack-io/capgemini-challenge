<template>
  <q-page padding>
    <q-card class="my-card q-ma-md q-pa-md">
      <q-linear-progress
        size="25px"
        :value="progress"
        color="primary"
        @click="randomize"
        label="Change Model"
      >
        <div class="absolute-full flex flex-center">
          <q-badge color="white" text-color="black" :label="progressLabel" />
        </div>
      </q-linear-progress>
    </q-card>
    <q-card class="my-card q-ma-md">
      <q-img
        src="https://images.unsplash.com/photo-1523810192022-5a0fb9aa7ff8?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8"
        :ratio="15 / 9"
      />

      <q-list>
        <q-item clickable :key="index" v-for="(item, index) in itemList">
          <q-item-section avatar>
            <q-icon color="accent" :name="item.name" />
          </q-item-section>

          <q-item-section>
            <q-item-label>{{ item.label }}</q-item-label>
            <q-item-label caption>{{ item.caption }}</q-item-label>
          </q-item-section>
        </q-item>
      </q-list>
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
  }
};
</script>
