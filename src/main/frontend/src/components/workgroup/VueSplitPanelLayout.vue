<template>
  <vue-split-penel>
    <vue-split-area class="tree-area" :size="35">
      <dept-tree></dept-tree>
    </vue-split-area>
    <vue-split-area class="table-area" :size="65" >
      <jira-projects-table></jira-projects-table>
    </vue-split-area>
  </vue-split-penel>
</template>

<script>
import { Split, SplitArea } from "vue-split-panel";
import DeptTree from "./DeptTree.vue";
import JiraProjectsTable from "./JiraProjectsTable.vue";

export default {
  components: {
    "vue-split-penel": Split,
    "vue-split-area": SplitArea,
    DeptTree,
    JiraProjectsTable,
  },
};
</script>

<style>
.gutter.gutter-horizontal {
  display:list-item;
  height: 400px;
  background-color: white;
}
#workgroup .tree-area {
  padding: 1px;
  min-width: calc(25% - 4px);
  max-width: calc(50% - 4px);
}
#workgroup .table-area {
  padding: 1px;
  min-width: calc(50% - 4px);
  max-width: calc(75% - 4px);
}
</style>