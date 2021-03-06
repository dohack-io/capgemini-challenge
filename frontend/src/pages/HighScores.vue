<template>
  <q-page class="q-ma-lg">
    <q-inner-loading v-if="requestStatus === 'sending'">
      <q-spinner-gears size="50px" color="primary" />
    </q-inner-loading>

    <q-banner class="bg-negative text-white" v-if="requestStatus === 'error'">
      {{ $t('highScores.sending.error') }}
    </q-banner>

    <q-table :data="users" :columns="columns" v-if="requestStatus === 'success'" :pagination="pagination" class="q-mt-md"
             :title="$t('highScores.title')" dense>
      <template v-slot:body="cellProperties">
        <q-tr :props="cellProperties">
          <q-td key="placement" :props="cellProperties">
            #{{ cellProperties.pageIndex + 1 }}
          </q-td>

          <q-td key="name" :props="cellProperties">
            {{ cellProperties.row.displayName }}
          </q-td>

          <q-td key="badges" :props="cellProperties">
            <q-icon v-if="Math.random() > 0.5" name="dinner_dining" size="sm" :class="getColorClass()"/>
            <q-icon v-if="Math.random() > 0.5" name="fas fa-medal" size="xs" :class="getColorClass()"/>
            <q-icon v-if="Math.random() > 0.5" name="fas fa-leaf" size="xs" :class="getColorClass()"/>
          </q-td>

          <q-td key="co2Score" :props="cellProperties">
            {{ cellProperties.row.co2Score.toFixed(2) }}
          </q-td>
        </q-tr>
      </template>

      <template v-slot:bottom/>
    </q-table>
  </q-page>
</template>

<script>
import {getLeaderboard} from "src/services/backendService";

export default {
  name: "HighScores",
  data() {
    const users = [];
    const requestStatus = 'sending';
    const columns = [
      { name: 'placement', label: this.$t('highScores.table.placement'), field: 'name', align: 'left' },
      { name: 'name', label: this.$t('highScores.table.name'), field: 'name', align: 'left' },
      { name: 'badges', label: this.$t('highScores.table.badges'), field: 'badges', align: 'left' },
      { name: 'co2Score', label: this.$t('highScores.table.co2Score'), field: 'co2Score'},
    ];

    const pagination = {
      rowsPerPage: 0
    };

    return {
      users,
      requestStatus,
      columns,
      pagination
    }
  },
  methods: {
    getColorClass: function () {
      if (Math.random() > 0.8) {
        return 'color-gold';
      } else if (Math.random() > 0.4) {
        return 'color-silver';
      } else {
        return 'color-brass';
      }
    }
  },
  async mounted() {
    const requestResult = await getLeaderboard();
    if (requestResult.ok) {
      this.users = await requestResult.json();
      this.requestStatus = 'success';
    } else {
      this.requestStatus = 'error';
    }
  }
}
</script>

<style scoped>
.color-gold {
  color: #FFD700;
}

.color-silver {
 color: #c0c0c0;
}

.color-brass {
  color: #E1C16E;
}
</style>
