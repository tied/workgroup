<template>
  <v-app>
    <v-main>
      <workgroup-layout></workgroup-layout>
    </v-main>
  </v-app>
</template>

<script>
import WorkgroupLayout from './components/workgroup/WorkgroupLayout.vue';

export default {
  name: 'App',

  components: {
    WorkgroupLayout,
  },

  data: () => ({
    //
  }),
};
</script>
