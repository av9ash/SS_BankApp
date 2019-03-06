<template>
  <div id="login">
    <div class="text-center col-sm-12">
      <i class="fa fa-university fa-3x" aria-hidden="true" style="color:white;"></i>
      <br>
      <br>
      <!-- login form -->
      <form @submit.prevent="login">
      <!--<form @submit="checkForm" action="https://vuejs.org/" method="post"> -->

        <p v-if="errors.length" style="color:white;">
          <b>Please correct the following error(s):</b>
        <ul>
          <li v-for="error in errors" :key="error.id">{{ error }}</li>
        </ul>
        </p>

        <div class="input-group">
          <span class="input-group-addon"><i class="fa fa-envelope"></i></span>
          <input class="form-control" name="username" placeholder="Username" type="text" v-model="username">
        </div>

        <div class="input-group">
          <span class="input-group-addon"><i class="fa fa-lock"></i></span>
          <input class="form-control" name="password" placeholder="Password" type="password" v-model="password">
        </div>
        <!-- <button type="submit" class="btn btn-primary btn-lg" v-on:click="login()">Login</button> -->
        <!--<button type="submit" class="btn btn-primary btn-lg" v-on:click="login()">Login</button>-->
        <button type="submit" v-bind:class="'btn btn-primary btn-lg ' + loading">Login</button>
      </form>

      <!-- errors -->
      <div v-if=response class="text-red"><p class="vertical-5p lead">{{response}}</p></div>
    </div>
  </div>
</template>

<script>
import api from '../api'

export default {
  name: 'Login',
  data(router) {
    return {
      // section: 'Login',
      loading: '',
      // username: '',
      // password: '',
      response: '',
      errors: [],
      username: null,
      password: null
    }
  },
  methods: {
    // checkCreds() {
    //   const { username, password } = this
    //
    //   this.toggleLoading()
    //   this.resetResponse()
    //   this.$store.commit('TOGGLE_LOADING')
    //
    //   /* Making API call to authenticate a user */
    //   api
    //     .request('post', '/login', { username, password })
    //     .then(response => {
    //       this.toggleLoading()
    //
    //       var data = response.data
    //       /* Checking if error object was returned from the server */
    //       if (data.error) {
    //         var errorName = data.error.name
    //         if (errorName) {
    //           this.response =
    //             errorName === 'InvalidCredentialsError'
    //               ? 'Username/Password incorrect. Please try again.'
    //               : errorName
    //         } else {
    //           this.response = data.error
    //         }
    //
    //         return
    //       }
    //
    //       /* Setting user in the state and caching record to the localStorage */
    //       if (data.user) {
    //         var token = 'Bearer ' + data.token
    //
    //         this.$store.commit('SET_USER', data.user)
    //         this.$store.commit('SET_TOKEN', token)
    //
    //         if (window.localStorage) {
    //           window.localStorage.setItem('user', JSON.stringify(data.user))
    //           window.localStorage.setItem('token', token)
    //         }
    //
    //         this.$router.push(data.redirect ? data.redirect : '/')
    //       }
    //     })
    //     .catch(error => {
    //       this.$store.commit('TOGGLE_LOADING')
    //       console.log(error)
    //
    //       this.response = 'Server appears to be offline'
    //       this.toggleLoading()
    //     })
    // },
     toggleLoading() {
       this.loading = this.loading === '' ? 'loading' : ''
     },
     resetResponse() {
       this.response = ''
     },

    checkForm: function (e) {
      e.preventDefault()
    },
    login () {
	this.toggleLoading()
    this.resetResponse()
		const { username, password } = this
      this.errors = []

      if (!this.username) {
        this.errors.push('Username required.')
      }
      if (!this.password) {
        this.errors.push('Password required.')
      }
      if (this.username !== '' && this.password !== '') {
        /*if (this.username === 'sberad' && this.password === 'password') {
          this.$emit('authenticated', true)
          this.$router.replace({ name: 'Dashboard' })
        } else {
          this.errors.push('The login and / or password is incorrect')
        }
		
		*/
		api
        .request('post', './rest/searchUser', { username, password })
         .then(response => {
		 var data = response.data
		 if(data === undefined)
		 {
			this.response = "Invalid Credentials"
		 }
		 else
		 {
			this.$store.commit('SET_USER', data.username)
			this.$emit('authenticated', true)
			this.$router.replace({ name: 'Dashboard' })
		 }
		 })
		 .catch(error => {
           this.$store.commit('TOGGLE_LOADING')
           console.log(error)
    
           this.response = 'Server appears to be offline'
           //this.toggleLoading()
         })
      }
    }
  }
}
</script>

<style>
#login {
  padding: 10em;
}

html,
body,
.container-table {
  height: 100%;
  background-color: #282b30 !important;
}
.container-table {
  display: table;
  color: white;
}
.vertical-center-row {
  display: table-cell;
  vertical-align: middle;
}
.vertical-20p {
  padding-top: 20%;
}
.vertical-10p {
  padding-top: 10%;
}
.vertical-5p {
  padding-top: 5%;
}
.logo {
  width: 15em;
  padding: 3em;
}

.input-group {
  padding-bottom: 2em;
  height: 4em;
  width: 100%;
}

.input-group span.input-group-addon {
  width: 2em;
  height: 4em;
}

@media (max-width: 1241px) {
  .input-group input {
    height: 4em;
  }
}
@media (min-width: 1242px) {
  form {
    padding-left: 20em;
    padding-right: 20em;
  }

  .input-group input {
    height: 6em;
  }
}

.input-group-addon i {
  height: 15px;
  width: 15px;
}
</style>
