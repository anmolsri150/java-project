<template>
  <div>
    <nav class="navbar navbar-transparent navbar-absolute">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle"
                  data-toggle="collapse"
                  data-target="#navigation-example-2"
                  @click="toggleNavbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <router-link class="navbar-brand" to="/admin">
            <div style="display:inline-block">
            <img src="static/img/vue-logo.png" style="width:90px;">
            <span>Restaurant Management System</span>
            </div></router-link>
        </div>
        <div class="collapse navbar-collapse">
          <ul class="nav navbar-nav navbar-right">
            <router-link to="/register" tag="li">
              <a>Register</a>
            </router-link>
            <router-link to="/statusPage" tag="li">
              <a>Order Statuses</a>
            </router-link>
          </ul>
        </div>
      </div>
    </nav>

    <div class="wrapper wrapper-full-page">
      <div class="full-page login-page" data-color=""
           data-image="static/img/background/background-2.jpg">
        <!--   you can change the color of the filter page using: data-color="blue | azure | green | orange | red | purple" -->
        <div class="content">
          <div class="container">
            <div class="row">
              <div class="col-md-4 col-sm-6 col-md-offset-4 col-sm-offset-3">
                <form id="myForm">
                  <div class="card" data-background="color" data-color="blue">
                    <div class="card-header">
                      <h3 class="card-title">Login</h3>
                    </div>
                    <div class="card-content">
                      <div class="form-group">
                        <label>Email address</label>
                        <input type="email" v-model="email" placeholder="Enter email" class="form-control input-no-border">
                      </div>
                      <div class="form-group">
                        <label>Password</label>
                        <input type="password" v-model="password" placeholder="Password" class="form-control input-no-border">
                      </div>
                    </div>
                    <transition name="fade">
                      <div v-if="this.errorMsg!==''" class="alert alert-danger">
                        <p>{{this.errorMsg}}</p>
                      </div>
                    </transition>
                    <div class="card-footer text-center">
                      <button @click="login" class="btn btn-fill btn-wd ">Let's go</button>
                      <div class="forgot">
                        <router-link to="/register">
                          Forgot your password?
                        </router-link>
                      </div>
                    </div>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>

        <footer class="footer footer-transparent">
          <div class="container">
            <div class="copyright">
              &copy;
              <a href="http://localhost:8098" target="_blank">ManipalX</a>
            </div>
          </div>
        </footer>
        <div class="full-page-background" style="background-image: url(static/img/background/background-2.jpg) "></div>
      </div>
    </div>
    <div class="collapse navbar-collapse off-canvas-sidebar">
      <ul class="nav nav-mobile-menu">
        <router-link to="/register" tag="li">
          <a>Register</a>
        </router-link>
        <router-link to="/statusPage" tag="li">
          <a>Order Statuses</a>
        </router-link>
      </ul>
    </div>
  </div>
</template>
<script>
  import fb from '../../../../firebase'
  export default {
    data () {
      return {
        errorMsg: '',
        email: null,
        password: null
      }
    },
    computed: {
      baseurl () {
        return window.location.origin
      }
    },
    methods: {
      login () {
        let vm = this
        this.errorMsg = 'hhae'
        fb.firebase.auth().signInWithEmailAndPassword(this.email, this.password).then(user => {
          console.log(user)
          this.$store.commit('setCurrentUser', user.user)
          console.log(user.user.uid)
          this.$store.dispatch('fetchUserProfile')
          setTimeout(() => {
            this.$router.push('/')
            window.location.reload()
          }, 1500)
        }).catch(err => {
          vm.errorMsg = err.message
          alert(vm.errorMsg)
          console.log(vm.errorMsg)
        })
      }
    },
    // methods: {
    //   login () {
    //     if (this.email !== null && this.password !== null) {
    //       this.$store.state.user = 'user'
    //       setTimeout(() => {
    //         this.$router.push('/')
    //       }, 500)
    //     }
    //   },
    //   toggleNavbar () {
    //     document.body.classList.toggle('nav-open')
    //   },
    //   closeMenu () {
    //     document.body.classList.remove('nav-open')
    //     document.body.classList.remove('off-canvas-sidebar')
    //   }
    // },
    beforeDestroy () {
      this.closeMenu()
    }
  }
</script>
<style>
</style>
