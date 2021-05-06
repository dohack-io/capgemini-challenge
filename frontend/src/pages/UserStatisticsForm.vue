<template>
  <q-page class="q-ma-md">
    <q-inner-loading v-if="sendStatus === 'sending'">
      <q-spinner-gears size="50px" color="primary" />
    </q-inner-loading>

    <q-banner class="bg-positive text-white" v-if="sendStatus === 'send'">
      {{ $t('userStatisticsForm.sending.success') }}
    </q-banner>

    <q-banner class="bg-negative text-white" v-if="sendStatus === 'error'">
      {{ $t('userStatisticsForm.sending.error') }}
    </q-banner>

    <q-form ref="myForm" @submit="onSubmit" v-if="sendStatus === 'unsend'">
      <div class="text-h4">{{ $t('userStatisticsForm.title') }}</div>

      <div class="row q-mt-lg">
        <q-icon class="col-2" name="local_cafe" size="lg"/>
        <q-input class="col" outlined type="number" :label="$t('userStatisticsForm.coffee.label')" v-model="coffee"/>
      </div>

      <div class="row q-mt-lg">
        <q-icon class="col-2" name="map" size="lg"/>
        <q-input class="col" outlined type="text" :label="$t('userStatisticsForm.lunch.label')" v-model="lunch"/>
      </div>

      <div v-for="(commution, index) in commutions.value" v-bind:key="index" class="q-mt-lg">
        <q-btn unelevated tile color="negative" v-on:click="() => removeFromCommutions(index)" label="x" class="commute-remove"/>

        <q-card>
          <q-card-section>
            <div class="text-h5">{{ $t('userStatisticsForm.commuteInput.title', {index: `${index + 1}`}) }}</div>
          </q-card-section>

          <q-card-section>
            <div class="row q-mt-lg">
              <q-icon class="col-2" name="commute" size="lg"/>

              <q-select class="col" outlined :label="$t('userStatisticsForm.commuteInput.commuteType.label')"
                        v-model="commution.commuteType" :options="commuteTypes">
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
              <q-icon class="col-2" name="local_cafe" size="lg"/>

              <q-input class="col" outlined type="number" :label="$t('userStatisticsForm.commuteInput.commuteDistance.label')"
                       v-model.number="commution.commuteDistance"/>
            </div>
          </q-card-section>
        </q-card>
      </div>

      <div class="row q-mt-lg">
        <q-btn unelevated tile color="primary" v-on:click="addToCommutions" :label="$t('userStatisticsForm.commuteInput.add')"
               class="cat-button"/>
      </div>

      <div class="row q-mt-lg">
        <q-btn unelevated tile outline :label="$t('userSettings.cancel')" @click="$router.push('/')" color="primary" class="q-mr-sm"/>
        <q-btn unelevated tile :label="$t('userSettings.send')" type="submit" color="primary"/>
      </div>
    </q-form>
  </q-page>
</template>

<script>
import {ref} from '@vue/composition-api';
import commuteTypes from "src/model/CommuteTypes";

export default {
  name: "UserStatisticsForm",
  data() {
    const coffee = undefined;
    const lunch = '';
    const commutions = ref([]);
    let sendStatus = 'unsend';

    const addToCommutions = function() {
      commutions.value.push({commuteType: undefined, commuteDistance: undefined});
    }

    function removeFromCommutions(index) {
      commutions.value.splice(index, 1);
    }

    return {
      coffee,
      lunch,
      commutions,
      sendStatus,
      commuteTypes,
      addToCommutions,
      removeFromCommutions
    }
  },
  methods: {
    onSubmit: async function () {
      this.sendStatus = 'sending';
      const username = this.$store.getters["credentials/getUsername"];
      const sendResult = await fetch('http://localhost:8081/statistics/daily/create', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json;charset=UTF-8',
        },
        body: JSON.stringify({
          username: username,
          numberOfCoffees: this.coffee,
          lunchScore: 5,
          dailyChallengeCompleted: true,
          dailyCommuteList: this.commutions.value.map(commute => {
            return {
              distance: commute.commuteDistance,
              type: commute.commuteType.value
            }
          })
        })
      });
      if (sendResult.ok) {
        this.sendStatus = 'send';
      } else {
        this.sendStatus = 'error';
      }
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
