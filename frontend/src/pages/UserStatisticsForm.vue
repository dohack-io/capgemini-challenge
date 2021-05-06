<template>
  <q-page class="q-ma-md">
    <div class="text-h2">{{ $t('userStatisticsForm.title') }}</div>

    <div class="row q-mt-lg">
      <q-icon class="col-1" name="local_cafe" size="lg"/>
      <q-input class="col" outlined type="number" :label="$t('userStatisticsForm.coffee.label')" :value="coffee"/>
    </div>

    <div class="row q-mt-lg">
      <q-icon class="col-1" name="map" size="lg"/>
      <q-input class="col" outlined type="text" :label="$t('userStatisticsForm.lunch.label')" :value="lunch"/>
    </div>

    <div class="row q-mt-lg">
      <q-icon class="col-1" name="map" size="lg"/>
      <q-input class="col" outlined type="dining" :label="$t('userStatisticsForm.lunch.label')" :value="lunch"/>
    </div>

    <div class="row q-mt-lg">
      <q-icon class="col-1" name="map" size="lg"/>
      <q-input class="col" outlined type="number" :label="$t('userStatisticsForm.commuteDistance.label')" :value="commuteDistance"/>
    </div>
  </q-page>
</template>

<script>
import {ref} from '@vue/composition-api';

export default {
  name: "UserStatisticsForm",
  data() {
    const coffee = ref(0);
    const commuteDistance = ref(0);
    const lunch = ref('');

    return {
      coffee,
      commuteDistance,
      lunch
    }
  }
}
</script>

<style scoped>

</style>
