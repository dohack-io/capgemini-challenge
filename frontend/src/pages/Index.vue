<template>
  <q-page padding>
    <q-card class="my-card q-ma-md q-pa-md">
      <q-linear-progress size="25px" :value="progress" color="primary" @click="randomize" label="Change Model">
        <div class="absolute-full flex flex-center">
          <q-badge color="white" text-color="black" :label="progressLabel" />
        </div>
      </q-linear-progress>
    </q-card>
    <q-card class="my-card q-ma-md q-pa-md">
      <q-timeline color="secondary">
        <q-timeline-entry heading>
          Heute
        </q-timeline-entry>
        <q-timeline-entry title="Event Title" subtitle="February 22, 1986">
          <div>
            Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
          </div>
        </q-timeline-entry>
        <q-timeline-entry title="Event Title" subtitle="February 21, 1986" icon="delete">
          <div>
            Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
          </div>
        </q-timeline-entry>
        <q-timeline-entry heading>
          Gestern
        </q-timeline-entry>
        <q-timeline-entry title="Event Title" subtitle="February 22, 1986" avatar="https://cdn.quasar.dev/img/avatar2.jpg">
          <div>
            Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
          </div>
        </q-timeline-entry>
        <q-timeline-entry title="Event Title" subtitle="February 22, 1986">
          <div>
            Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
          </div>
        </q-timeline-entry>
        <q-timeline-entry title="Event Title" subtitle="February 22, 1986" color="orange" icon="done_all">
          <div>
            Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
          </div>
        </q-timeline-entry>
        <q-timeline-entry title="Event Title" subtitle="February 22, 1986">
          <div>
            Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
          </div>
        </q-timeline-entry>
        <q-timeline-entry title="Event Title" subtitle="February 22, 1986">
          <div>
            Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
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
  }
};
</script>
