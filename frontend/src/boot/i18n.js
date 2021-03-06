import VueCompositionApi from '@vue/composition-api';
import Vue from 'vue'
import VueI18n from 'vue-i18n'
import messages from 'src/i18n'

Vue.use(VueI18n);
Vue.use(VueCompositionApi);

const i18n = new VueI18n({
  locale: 'de',
  fallbackLocale: 'de',
  messages
});

export default ({ app }) => {
  // Set i18n instance on app
  app.i18n = i18n
};

export { i18n };
