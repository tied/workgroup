<template>
  <v-app>
    <v-main>
      <ShortcutLayout></ShortcutLayout>
    </v-main>
  </v-app>
</template>

<script>
import ShortcutLayout from './components/shortcut/ShortcutLayout.vue';

export default {
  name: 'App',

  components: {
    ShortcutLayout,
  },

  data: () => ({
    //
  }),
};
</script>
