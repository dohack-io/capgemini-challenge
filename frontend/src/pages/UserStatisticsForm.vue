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

    <q-card v-for="(commution, index) in commutions.value" v-bind:key="index" class="q-mt-lg">
      <q-btn unelevated tile color="negative" v-on:click="() => removeFromCommutions(index)" label="x" class="commute-remove"/>

      <q-card-section>
        <div class="text-h4">{{ $t('userStatisticsForm.commuteInput.title', {index: `${index + 1}`}) }}</div>
      </q-card-section>

      <q-card-section>
        <div class="row q-mt-lg">
          <q-icon class="col-1" name="commute" size="lg"/>
          <q-select outlined v-model="commution.commuteType" :options="commuteTypes" :label="$t('userStatisticsForm.commuteInput.commuteType.label')">
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
          <q-icon class="col-1" name="local_cafe" size="lg"/>
          <q-input class="col" outlined type="number" :label="$t('userStatisticsForm.commuteInput.commuteDistance.label')" :value="commution.commuteDistance"/>
        </div>
      </q-card-section>
    </q-card>

    <div class="row q-mt-lg">
      <q-btn unelevated tile color="primary" v-on:click="addToCommutions" :label="$t('userStatisticsForm.commuteInput.add')"
             class="cat-button"/>
    </div>
  </q-page>
</template>

<script>
import {ref} from '@vue/composition-api';
import commuteTypes from "src/model/CommuteTypes";

export default {
  name: "UserStatisticsForm",
  data() {
    const coffee = ref(0);
    const lunch = ref('');
    const commutions = ref([]);

    function addToCommutions() {
      commutions.value.push({commuteType: commuteTypes[0], commuteDistance: 0});
    }

    function removeFromCommutions(index) {
      commutions.value.splice(index, 1);
    }

    return {
      coffee,
      lunch,
      commutions,
      commuteTypes,
      addToCommutions,
      removeFromCommutions
    }
  }
}
</script>

<style scoped>
.commute-remove {
  position: relative;
  top: 50px;
  z-index: 5;
  left: calc(100% - 59px);
}
</style>
