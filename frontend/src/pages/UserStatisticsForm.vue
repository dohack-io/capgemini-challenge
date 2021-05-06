<template>
  <q-page class="q-ma-md">
    <div class="text-h4">{{ $t('userStatisticsForm.title') }}</div>

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
      <div class="row q-mt-lg">
        <q-icon class="col-2" name="local_cafe" size="lg"/>
        <q-input class="col" outlined type="number" :label="$t('userStatisticsForm.coffee.label')" v-model="coffee"/>
      </div>

      <div class="row q-mt-lg">
        <q-icon class="col-2" name="today" size="lg"/>
        <q-checkbox class="col" outlined :label="$t('userStatisticsForm.dailyChallenge.label')" v-model="dailyChallenge"/>
      </div>

      <q-card class="q-mt-lg">
        <q-card-section>
          <div class="text-h5">{{ $t('userStatisticsForm.food.title') }}</div>
          <div class="q-mt-md">{{ $t('userStatisticsForm.food.score') }}: {{ calculateFoodScore }}</div>
        </q-card-section>

        <q-card-section v-for="(food, index) in foods.value" v-bind:key="index">
          <div class="row">
            <q-select outlined type="number" class="col-4" :label="$t('userStatisticsForm.food.type')"
                      v-model="food.score" :options="foodTypes">
              <template v-slot:selected-item="props">
                {{$t(props.opt.label)}}
              </template>

              <template v-slot:option="scope">
                <q-item v-bind="scope.itemProps" v-on="scope.itemEvents">
                  <q-item-section>
                    <q-item-label v-html="$t(scope.opt.label)"/>
                  </q-item-section>
                </q-item>
              </template>
            </q-select>

            <q-input outlined type="number" class="col-4 offset-1" :label="$t('userStatisticsForm.food.weight')"
                     v-model="food.amount"/>

            <q-btn unelevated size="sm" tile color="negative" v-on:click="() => removeFromFood(index)" label="x"
                   class="col-2 offset-1"/>
          </div>
        </q-card-section>

        <q-card-actions>
          <q-btn unelevated tile color="primary" v-on:click="addToFood" :label="$t('userStatisticsForm.food.add')"/>
        </q-card-actions>
      </q-card>

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
              <q-icon class="col-2" name="straighten" size="lg"/>

              <q-input class="col" outlined type="number" :label="$t('userStatisticsForm.commuteInput.commuteDistance.label')"
                       v-model.number="commution.commuteDistance"/>
            </div>
          </q-card-section>
        </q-card>
      </div>

      <div class="row q-mt-lg">
        <q-btn unelevated tile color="primary" v-on:click="addToCommutions" :label="$t('userStatisticsForm.commuteInput.add')"/>
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
import foodTypes from "src/model/FoodTypes";

export default {
  name: "UserStatisticsForm",
  data() {
    const coffee = undefined;
    const dailyChallenge = false;
    const commutions = ref([]);
    const foods = ref([]);
    let sendStatus = 'unsend';

    const addToFood = function () {
      foods.value.push({score: undefined, amount: undefined});
    }

    const removeFromFood = function (index) {
      foods.value.splice(index, 1)
    }

    const addToCommutions = function() {
      commutions.value.push({commuteType: undefined, commuteDistance: undefined});
    }

    function removeFromCommutions(index) {
      commutions.value.splice(index, 1);
    }

    return {
      coffee,
      commutions,
      foods,
      dailyChallenge,
      sendStatus,
      commuteTypes,
      foodTypes,
      addToFood,
      removeFromFood,
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
          lunchScore: this.calculateFoodScore,
          dailyChallengeCompleted: this.dailyChallenge,
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
  },
  computed: {
    calculateFoodScore: function() {
      if (this.foods.value.length > 0) {
        return this.foods.value
          .map(food => (food.score ? food.score.value : 0) * (food.amount ? food.amount : 0) / 1000)
          .reduce((total, a) => total + a).toFixed(2);
      } else {
        return 0;
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
