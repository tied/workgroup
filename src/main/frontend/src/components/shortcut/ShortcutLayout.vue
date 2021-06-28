<template>
  <div
      id="shortcut-gadget"
      class="d-flex flex-wrap justify-center"
  >
    <v-card
        v-for="(item, index) in shortcuts"
        :key="index"
        class="ma-2 shortcut-card"
        @mouseover="item.showMore = true"
        @mouseleave="item.showMore = false"
    >
      <v-card-text>
        <v-avatar
            color="teal"
            size="48"
            v-on:click="clickAvatar(item.url)"
        >
          <span class="white--text headline">{{ item.name.trim().substring(0, 1) }}</span>
        </v-avatar>
        <div class="mt-2 shortcut-name">{{ item.name.trim() }}</div>
      </v-card-text>
      <v-menu
          left
          bottom
      >
        <template v-slot:activator="{ on, attrs }">
          <v-btn
              icon
              v-bind="attrs"
              v-on="on"
              class="menu-btn"
              v-show="item.showMore"
          >
            <v-icon>mdi-dots-vertical</v-icon>
          </v-btn>
        </template>
        <v-list>
          <v-list-item  @click="editShortcut(index)">
            <v-list-item-title>Edit</v-list-item-title>
          </v-list-item>
          <v-list-item  @click="deleteShortcut(index)">
            <v-list-item-title>Delete</v-list-item-title>
          </v-list-item>
        </v-list>
      </v-menu>
    </v-card>
    <v-card class="ma-2 shortcut-card">
      <v-card-text>
        <v-avatar
            color="teal"
            size="48"
            v-on:click.stop="addShortcut"
        >
          <span class="white--text headline">+</span>
        </v-avatar>
        <div class="mt-2 shortcut-name">Add</div>
      </v-card-text>
    </v-card>
    <v-dialog
        v-model="dialog"
        persistent
        max-width="600px"
    >
      <v-card>
        <v-card-title>
          <span class="text-h5">{{ dialogMode.toUpperCase() }} Shortcut</span>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <v-col cols="12">
                <v-text-field
                    v-model="selectedShortcut.name"
                    label="Name"
                    required
                ></v-text-field>
              </v-col>
              <v-col cols="12">
                <v-text-field
                    v-model="selectedShortcut.url"
                    label="URL"
                    required
                ></v-text-field>
              </v-col>
            </v-row>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
              color="blue darken-1"
              text
              v-on:click="cancel"
          >
            Cancel
          </v-btn>
          <v-btn
              color="blue darken-1"
              text
              v-on:click="save"
          >
            Save
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
import axios from 'axios'

const SHORT_REST_URL = "/jira/rest/workgrouprestresource/1.0/workgroup/shortcuts";
export default {
  components: {},
  data() {
    return {
      dialog: false,
      dialogMode: "edit",
      shortcuts: [],
      selectedShortcut: {},
      selectedIndex: -1,
    }
  },
  mounted() {
    console.log("mounted!!!");
    this.getShortcuts("mounted!!!");
  },
  methods: {
    getShortcuts(str) {
      console.log(str);
      axios.get(`${SHORT_REST_URL}/userName/admin`).then((res) => {
        console.table(res.data);
        this.shortcuts = [];
        res.data.forEach((item) => {
          this.shortcuts.push({
            id: item.id,
            name: item.name,
            url: item.url,
            showMore: false,
          })
        });
      });
    },
    clickAvatar(url) {
      window.open(url, "_blank");
    },
    cancel() {
      this.dialog = false;
    },
    async save() {
      if (this.selectedIndex > -1) {
        // modify
        const {id, name, url } = this.selectedShortcut;
        await axios.put(`${SHORT_REST_URL}/userName/admin/id/${id}/name/${name}/url/${url}`).then((res) => {
          console.log(res.data);
        });
      } else {
        // add
        const { name, url } = this.selectedShortcut;
        await axios.post(`${SHORT_REST_URL}/userName/admin/name/${name}/url/${url}`).then((res) => {
          console.log(res.data);
        });
      }
      this.getShortcuts(`${this.dialogMode} shortcut !!!`);
      this.dialog = false;
    },
    addShortcut() {
      this.dialogMode = "add";
      this.selectedIndex = -1;
      this.selectedShortcut = {
        id: "",
        name: "",
        url: "",
        userId: "admin",
      }
      this.dialog = true;
    },
    editShortcut(index) {
      this.dialogMode = "edit";
      this.selectedIndex = index;
      this.selectedShortcut = {
        ...this.shortcuts[this.selectedIndex],
      }
      this.dialog = true;
    },
    async deleteShortcut(index) {
      const id = this.shortcuts[index].id;
      await axios.delete(`${SHORT_REST_URL}/userName/admin/id/${id}`).then((res) => {
        console.log(res.data);
      });
      this.getShortcuts("Click delete menu !!!");
    },
  },
};
</script>
<style>
#shortcut-gadget {
  max-width: calc(100vw - 18px);
}

#shortcut-gadget .shortcut-card {
  width: 100px;
  height: 100px;
  text-align: center;
  box-shadow: none;
}

#shortcut-gadget .shortcut-card:hover {
  background: #fafafa;
  box-shadow: 1px 1px 1px #eaeaea;
}

#shortcut-gadget .shortcut-name {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  flex: 1;
}

#shortcut-gadget .menu-btn {
  position: absolute;
  right: 4px;
  top: 4px;
}

.v-list-item {
  min-width: 120px !important;
  min-height: 32px !important;
}
</style>