<template>
  <v-container id="workgroup">
    <v-card>
      <v-card-title>
        <span>My Project</span>
      </v-card-title>
      <v-card-text>
        <vue-split-panel-layout></vue-split-panel-layout>
      </v-card-text>
    </v-card>
  </v-container>
</template>

<script>
import VueSplitPanelLayout from "./VueSplitPanelLayout.vue";

export default {
  components: {
    VueSplitPanelLayout,
  },
  data() {
    return {
      isIE: !!document.documentMode,
    };
  },
};
</script>
