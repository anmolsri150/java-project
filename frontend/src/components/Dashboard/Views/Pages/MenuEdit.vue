<template>
  <div>
    <div v-if="this.$store.state.userProfile.usertype === 'admin'">
      <h3 style="align-content: center;text-align: center;font-size:5rem;">Menu</h3>
      <v-container>
        <v-row>
          <div class="card" style="width: 100%!important;">
            <div class="card-header">
              <h4 class="card-title">All Menu Items</h4>
              <p class="category">Edit/Delete</p>
            </div>
            <div class="card-content row">
              <div class="col-sm-12">
                <el-table v-show="this.bool" :data="menu">
                  <el-table-column type="index">

                  </el-table-column>
                  <el-table-column prop="name"
                                   label="Name">
                  </el-table-column>
                  <el-table-column prop="category"
                                   label="Category">
                  </el-table-column>
                  <el-table-column prop="desc"
                                   label="Description">
                  </el-table-column>
                  <el-table-column prop="price"
                                   label="Price">
                  </el-table-column>
                  <el-table-column
                    label="Actions">
                    <template slot-scope="props">
                      <a class="btn btn-simple btn-warning btn-xs btn-icon edit" @click="handleEdit(props.$index, props.row)"><i class="ti-pencil-alt"></i></a>
                      <a class="btn btn-simple btn-danger btn-xs btn-icon remove"  @click="handleDelete(props.$index, props.row)"><i class="ti-close"></i></a>
                    </template>
                  </el-table-column>
                </el-table>
                <div v-show="!this.bool" class="card">
                  <form class="form-horizontal">
                    <div class="card-header">
                      <h4 class="card-title">
                        Add Item
                      </h4>
                    </div>
                    <div class="card-content">
                      <fieldset>
                        <div class="form-group">
                          <label class="col-sm-2 control-label">Item Name</label>
                          <div class="col-sm-6">
                            <input type="text"
                                   name="requiredText"
                                   v-validate="modelValidations.requiredText"
                                   v-model="model.name"
                                   class="form-control">
                            <small class="text-danger" v-show="requiredText.invalid">
                              {{ getError('requiredText') }}
                            </small>
                          </div>
                        </div>
                      </fieldset>

                      <fieldset>
                        <div class="form-group">
                          <label class="col-sm-2 control-label">Item Category</label>
                          <div class="col-sm-6">
                            <input type="text"
                                   name="requiredText"
                                   v-validate="modelValidations.requiredText"
                                   v-model="model.category"
                                   class="form-control">
                            <small class="text-danger" v-show="requiredText.invalid">
                              {{ getError('requiredText') }}
                            </small>
                          </div>
                        </div>
                      </fieldset>

                      <fieldset>
                        <div class="form-group">
                          <label class="col-sm-2 control-label">Item Description</label>
                          <div class="col-sm-6">
                            <input type="text"
                                   name="requiredText"
                                   v-validate="modelValidations.requiredText"
                                   v-model="model.desc"
                                   class="form-control">
                            <small class="text-danger" v-show="requiredText.invalid">
                              {{ getError('requiredText') }}
                            </small>
                          </div>
                        </div>
                      </fieldset>

                      <fieldset>
                        <div class="form-group">
                          <label class="col-sm-2 control-label">Item Price</label>
                          <div class="col-sm-6">
                            <input type="number"
                                   name="number"
                                   v-validate="modelValidations.number"
                                   v-model="model.price"
                                   class="form-control">
                            <small class="text-danger" v-show="number.invalid">
                              {{ getError('number') }}
                            </small>
                          </div>
                        </div>
                      </fieldset>
                    </div>
                    <div class="card-footer text-center">
                      <button type="submit" @click.prevent="validate" class="btn btn-fill btn-info btn-wd">Confirm Edit</button>
                      <button type="submit" @click.prevent="bool = !bool" class="btn btn-fill btn-danger btn-wd">Cancel Edit</button>
                    </div>
                  </form>

                </div>
              </div>
            </div>
          </div>
        </v-row>
      </v-container>
    </div>
    <div v-else class="card">
    <h1 class="danger">You don't have the rights to view this page!</h1>
  </div>
  </div>
</template>

<script>
  import {mapFields} from 'vee-validate'
  import Vue from 'vue'
  import swal from 'sweetalert2'
  import {Table, TableColumn} from 'element-ui'
  import PSwitch from 'src/components/UIComponents/Switch.vue'
  import axios from 'axios'
  import { Carousel, Slide } from 'vue-carousel'
  import Hospitalcard from '../../../UIComponents/Cards/hospitalcard'
  Vue.use(Table)
  Vue.use(TableColumn)
  export default {
    name: 'menuedit',
    data () {
      return {
        model: {
          id: '',
          name: '',
          price: Number,
          category: '',
          desc: ''
        },
        modelValidations: {
          requiredText: {
            required: true
          },
          email: {
            required: true,
            email: true
          },
          number: {
            required: true,
            decimal: true
          },
          url: {
            required: true,
            url: true
          },
          idSource: {
            required: true
          },
          idDestination: {
            required: true,
            confirmed: 'idSource'
          }
        },
        bool: true,
        menu: null,
        imgg: 'https://source.unsplash.com/collection/1353633/800x600'
      }
    },
    components: {
      PSwitch,
      Hospitalcard,
      Carousel,
      Slide
    },
    computed: {
      ...mapFields(['requiredText', 'email', 'number', 'url', 'idSource', 'idDestination'])
      // menu () {
      //   return this.$store.state.menu
      // }
    },
    methods: {
      getError (fieldName) {
        return this.errors.first(fieldName)
      },
      validate () {
        this.$validator.validateAll().then(isValid => {
          this.$emit('on-submit', this.registerForm, isValid)
          this.updateItem()
        })
      },
      updateItem () {
        let sessionurl = 'https://rms-anmolsri150.herokuapp.com/api/data/menu/updateItem/'
        let vm = this
        axios.post(sessionurl, vm.model, {}).then(res => {
          console.log(res.data)
          console.log('FETCHING Menu')
          let sessionurl = 'https://rms-anmolsri150.herokuapp.com/api/data/menu'
          axios.post(sessionurl, {}, {}).then(res => {
            console.log(res.data)
            vm.menu = res.data
            vm.$store.commit('setMenu', res.data)
            console.log(vm.$store.state.menu)
            swal({
              title: `Success!`,
              text: 'Item has been updated!',
              buttonsStyling: false,
              confirmButtonClass: 'btn btn-success btn-fill',
              type: 'success'
            })
          }).catch(err => {
            console.log(err)
          })
          vm.bool = !vm.bool
        }).catch(err => {
          console.log(err)
          vm.bool = !vm.bool
        })
      },
      handleEdit (index, row) {
        // alert(`Your want to edit ${row.name},${row.id}`)
        this.model = row
        this.bool = !this.bool
      },
      handleDelete (index, row) {
        let vm = this
        // alert(`Your want to delete ${row.name},${row.id}`)
        swal({
          title: 'Are you sure?',
          text: `You won't be able to revert this!`,
          type: 'warning',
          showCancelButton: true,
          confirmButtonClass: 'btn btn-success btn-fill',
          cancelButtonClass: 'btn btn-danger btn-fill',
          confirmButtonText: 'Yes, delete it!',
          buttonsStyling: false
        }).then(function () {
          let url = 'https://rms-anmolsri150.herokuapp.com/api/data/menu/deleteItem/' + row.id
          axios.get(url).then(resp => {
            swal({
              title: 'Deleted!',
              text: 'Item has been deleted.',
              type: 'success',
              confirmButtonClass: 'btn btn-success btn-fill',
              buttonsStyling: false
            })
            console.log(resp.data)
            console.log('FETCHING Menu')
            let sessionurl = 'https://rms-anmolsri150.herokuapp.com/api/data/menu'
            axios.post(sessionurl, {}, {}).then(res => {
              console.log(res.data)
              vm.menu = res.data
              vm.$store.commit('setMenu', res.data)
              console.log(vm.$store.state.menu)
            }).catch(err => {
              console.log(err)
            })
          })
        })
      }
    },
    mounted () {
      // this.$store.dispatch('fetchMenu')
      console.log('FETCHING Menu')
      let vm = this
      let sessionurl = 'https://rms-anmolsri150.herokuapp.com/api/data/menu'
      axios.post(sessionurl, {}, {}).then(res => {
        console.log(res.data)
        vm.menu = res.data
        vm.$store.commit('setMenu', res.data)
        console.log(vm.$store.state.menu)
      }).catch(err => {
        console.log(err)
      })
    }
  }
</script>

<style>
  .paral {
    min-height: 500px;
    background-attachment: fixed;
    background-size: cover;
    background-position: 50% 50%;
  }

  /* Paragrapgh for Parallax Section */
  .paral p {
    font-size: 24px;
    color:#f5f5f5;
    text-align: center;
    line-height: 60px;
  }

  /* Heading for Parallax Section */
  .paral h1 {
    color: rgba(255, 255, 255, 0.8);
    font-size: 60px;
    text-align: center;
    padding-top: 60px;
    line-height: 100px;
  }

  /* Image for Parallax Section */
  .paralsec {
    background-image: url("https://img.webnots.com/2017/05/parallax.jpg");
  }

  .paralsec1 {
    background-image: url("https://img.webnots.com/2017/05/parallax1.jpg");}

  .paralsec2 {
    background-image: url("https://img.webnots.com/2015/11/parallax2.jpg");
  }

  /* Add more images for more sections */

  /* Remove Bottom Margin from Jumbotron */
  .jumbotron{margin-bottom: 0;}

  /* Footer */
  .wn-footer {
    padding: 2.5rem 0;
    text-align: center;
    color: white;
    background-color: #607D8B;
    border-top: .05rem solid #e5e5e5;
  }

  .wn-footer a {
    color: yellow;
  }
</style>
