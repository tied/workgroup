<template>
  <vue-split-penes class="default-theme">
    <vue-split-pene min-size="25" max-size="50">
      <dept-tree></dept-tree>
    </vue-split-pene>
    <vue-split-pene>
      <jira-projects-table></jira-projects-table>
    </vue-split-pene>
  </vue-split-penes>
</template>

<script>
import { Splitpanes, Pane } from "splitpanes";
import "splitpanes/dist/splitpanes.css";
import DeptTree from "./DeptTree.vue";
import JiraProjectsTable from "./JiraProjectsTable.vue";

export default {
  components: {
    "vue-split-penes": Splitpanes,
    "vue-split-pene": Pane,
    DeptTree,
    JiraProjectsTable,
  },
};
</script>

<style>
.splitpanes__pane {
  border: none;
  padding: 1px;
}
.splitpanes__splitter {
  border-left: 0;
}
.default-theme.splitpanes--vertical > .splitpanes__splitter {
  border: 0;
  margin: 0;
}
</style>