<template>
  <q-page class="q-ma-lg">
    <div class="text-h4 text-no-wrap">{{ $t('userSettings.title') }}</div>

    <div class="row q-mt-lg">
      <q-input class="col" outlined type="email" :label="$t('userSettings.email.label')" v-model="email"/>
    </div>

    <div class="row q-mt-lg">
      <q-input class="col" outlined type="email" :label="$t('userSettings.displayName.label')" v-model="displayName"/>
    </div>

    <div class="row q-mt-lg">
      <q-input class="col" outlined type="email" :label="$t('userSettings.seat.label')" v-model="seat"/>
    </div>

    <div class="row q-mt-lg">
      <q-select class="col" outlined :label="$t('userSettings.defaultCommuteType.label')"
                v-model="defaultCommuteType" :options="commuteTypes">
        <template v-slot:selected-item="props">
          <q-icon :name="props.opt.icon"/>
          {{$t(props.opt.label)}}
        </template>

        <template v-slot:option="scope">
          <q-item v-bind="scope.itemProps" v-on="scope.itemEvents">
            <q-item-section avatar>
              <q-icon :name="scope.opt.icon" />
            </q-item-section>

            <q-item-section>
              <q-item-label v-html="$t(scope.opt.label)"/>
            </q-item-section>
          </q-item>
        </template>
      </q-select>
    </div>

    <div class="row q-mt-lg">
      <q-input class="col" outlined type="number" :label="$t('userSettings.defaultCommuteDistance.label')"
               v-model.number="defaultCommuteDistance"/>
    </div>

    <div class="row q-mt-lg">
      <q-btn unelevated tile outline :label="$t('userSettings.cancel')" @click="$router.push('/')" color="primary" class="q-mr-sm"/>
      <q-btn unelevated tile :label="$t('userSettings.send')" @click="saveSettings()" type="submit" color="primary"/>
    </div>

    <q-inner-loading :showing="visible">
      <q-spinner-gears size="50px" color="primary" />
    </q-inner-loading>
  </q-page>
</template>

<script>
import commuteTypes from "src/model/CommuteTypes";

export default {
  name: "UserSettings",
  data() {
    const email = '';
    const displayName = '';
    const seat = '';
    const defaultCommuteDistance = undefined;
    const defaultCommuteType = undefined;

    function onSubmit() {

    }

    return {
      email,
      displayName,
      seat,
      defaultCommuteDistance,
      defaultCommuteType,
      commuteTypes,
      visible: false
    }
  },
  mounted() {
    let data = JSON.parse(localStorage.getItem('data'));
    this.email = data?.email ?? '';
    this.displayName = data?.displayName ?? '';
    this.seat = data?.seat ?? '';
    this.defaultCommuteDistance = data?.defaultCommuteDistance ?? undefined;
    this.defaultCommuteType = data?.defaultCommuteType ?? undefined;
  },
  methods: {
    saveSettings() {
      this.visible = true;
      localStorage.setItem('data', JSON.stringify(this.$data));
      setTimeout(() => {
        this.visible = false;
        this.$router.push('/');
      }, 1000);
    }
  }
}
</script>

<style scoped>
</style>
