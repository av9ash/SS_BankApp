// Import ES6 Promise
import 'es6-promise/auto'

// Import System requirements
import Vue from 'vue'
import IdleVue from 'idle-vue'
import VueRouter from 'vue-router'
import Vuex from 'vuex'


import { sync } from 'vuex-router-sync'
import routes from './routes'
import store from './store'

// Import Helpers for filters
import { domain, count, prettyDate, pluralize, changeAcc, changeStat } from './filters'

// Import Views - Top level
import AppView from './components/App.vue'

//import { Alert } from 'bootstrap-vue'
//Vue.use(Alert)

// Import Install and register helper items

const eventsHub = new Vue()

const stores = new Vuex.Store({
  // ...
})

Vue.filter('count', count)
Vue.filter('domain', domain)
Vue.filter('prettyDate', prettyDate)
Vue.filter('pluralize', pluralize)
Vue.filter('changeAcc', changeAcc)
Vue.filter('changeStat', changeStat)

import VueTouchKeyboard from "vue-touch-keyboard";
import style from "vue-touch-keyboard/dist/vue-touch-keyboard.css";
Vue.use(VueRouter)
Vue.use(VueTouchKeyboard);

// Vue.use(IdleVue, { eventEmitter: eventsHub, stores })

Vue.use(IdleVue, {
  eventEmitter: eventsHub,
  idleTime: 300000
})

var router = new VueRouter({
  routes: routes,
  base: __dirname,
  linkExactActiveClass: 'active',
  scrollBehavior: function(to, from, savedPosition) {
    return savedPosition || { x: 0, y: 0 }
  }
})

const vm = new Vue({
  onIdle() {
    //console.log("idle");
	
    self.location = '#/login';
  },
  onActive() {
    //console.log("active");
  }
})


// Routing logic


// Some middleware to help us ensure the user is authenticated.
router.beforeEach((to, from, next) => {

  //console.log("token::"+router.app.$store.state.token)
  if (
    to.matched.some(record => record.meta.requiresAuth) &&
    (!router.app.$store.state.token || router.app.$store.state.token === 'null')
  ) {
    // this route requires auth, check if logged in
    // if not, redirect to login page.
    //console.log('inside if')
    window.console.log('Not authenticated')
    next({
      path: '/login',
      query: { redirect: to.fullPath }
    })
  } else {
	//console.log('inside else')
    next()
  }
})

sync(store, router)

// Check local storage to handle refreshes
if (window.localStorage) {
  var localUserString = window.localStorage.getItem('user') || 'null'
  var localUser = JSON.parse(localUserString)

  if (localUser && store.state.user !== localUser) {
    store.commit('SET_USER', localUser)
    store.commit('SET_TOKEN', window.localStorage.getItem('token'))
  }
}

// Start out app!
// eslint-disable-next-line no-new
new Vue({
  el: '#root',
  router: router,
  store: store,
  render: h => h(AppView)
})

// change this. demo
window.bugsnagClient = window.bugsnag('02fe1c2caaf5874c50b6ee19534f5932')
window.bugsnagClient.use(window.bugsnag__vue(Vue))
