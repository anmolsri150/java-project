import Vue from 'vue'
import Vuex from 'vuex'
import swal from 'sweetalert2'
import createPersistedState from 'vuex-persistedstate'
import axios from 'axios'
Vue.use(Vuex)

export const store = new Vuex.Store({
  plugins: [createPersistedState()],
  state: {
    user: null,
    cart: [],
    lastorder: {},
    orders: [],
    statuses: [{id: 'id', name: 'name', status: 'asd'}],
    menu: [{id: 'j48fMMgZNHdtBKitCf6Y', category: 'italian', desc: 'PIJJAaaasaaaaaaaaaaaaaaaaaaaaaaaaadasdasdasdaaa', name: 'Tomato-pizza', price: 3407}, {id: 'j48fMMgZNHdtBKitCf6Y', category: 'italian', desc: 'PIJJAaaasaaaaaaaaaaaaaaaaaaaaaaaaadasdasdasdaaa', name: 'Tomato-pizza', price: 3407}],
    userData: {username: 'michael23', email: 'abc@gmail.com', fname: 'Anmol', lname: 'Srivastava', age: '19', gender: 'Male', height: '5\'6"', bloodgroup: 'O+', weight: 55, bmi: 23, phone: '1231231234', address: 'New Door Hostels, Manipal University Jaipur, Dehmi Kalan, Jaipur', emergencyContact: {'name': 'Ishan', phone: '1231241234'}},
    userProfile: {name: 'Anmol Srivastava', usertype: 'admin'}
  },
  actions: {
    createorder ({commit, state}, e) {
      let ids = []
      let t = e.t
      let refe = e.reference
      state.cart.forEach(function (item, index) {
        for (let x = 1; x <= item.quantity; x++) {
          ids.push(item.id)
        }
      })
      commit('setCart', [])
      let d = ids.join(' ')
      console.log('dada')
      console.log(d)
      console.log(t)
      let dat = {'name': t.name, 'phone': t.phone, 'items': d}
      console.log(dat)
      let sessionurl = 'http://localhost:8098/api/data/createOrder/'
      axios.post(sessionurl, dat, {}).then(res => {
        console.log(res.data)
        state.lastorder = res.data
        swal({
          title: `Order Placed!`,
          text: 'Bill = ' + res.data.bill + ' Order id : ' + res.data.orderid,
          buttonsStyling: false,
          confirmButtonClass: 'btn btn-success btn-fill',
          type: 'success'
        })
        let u = '/order/' + res.data.id
        refe.$router.push(u).catch(err => {
          console.log(err)
        })
        // window.location.reload()
      }).catch(err => {
        console.log(err)
      })
    },
    addtocart ({commit, state}, payload) {
      let me = state.menu[payload]
      me['quantity'] = 1
      let ids = []
      state.cart.forEach(function (item, index) {
        ids.push(item.id)
      })
      console.log(me.id)
      console.log(ids)
      if (ids.includes(me.id)) {
        state.cart.forEach(function (item, index) {
          if (item.id === me.id) {
            item.quantity = item.quantity + 1
          }
        })
      } else {
        state.cart.push(me)
      }
      console.log(state.cart)
    },
    fetchUserProfile ({ commit, state }) {
      console.log('FETCHING PROFILE')
      console.log(state.user.uid)
      let sessionurl = 'http://localhost:8098/api/getUser/' + state.user.uid
      let uname = 'admin'
      let pass = 'password'
      axios.post(sessionurl, {}, {
        auth: {
          username: uname,
          password: pass
        }
      }).then(res => {
        console.log(res.data)
        commit('setUserProfile', res.data)
      }).catch(err => {
        console.log(err)
      })
    },
    fetchMenu ({ commit, state }) {
      console.log('FETCHING Menu')
      let sessionurl = 'http://localhost:8098/api/data/menu'
      axios.post(sessionurl, {}, {}).then(res => {
        console.log(res.data)
        commit('setMenu', res.data)
      }).catch(err => {
        console.log(err)
      })
    }
  },
  mutations: {
    setCart (state, val) {
      state.cart = val
    },
    setStatuses (state, val) {
      state.statuses = val
    },
    setOrders (state, val) {
      state.orders = val
    },
    setMenu (state, val) {
      state.menu = val
    },
    setCurrentUser (state, val) {
      state.user = val
    },
    setUserProfile (state, val) {
      state.userProfile = val
      console.log(state.userProfile)
    }
  },
  getters: {
    user (state) {
      return state.currentUser
    },
    userdata (state) {
      return state.userProfile
    }
  }
})
