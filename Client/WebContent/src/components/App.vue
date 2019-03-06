<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<script>
  export default {
    name: 'App',
    data () {
      return {
        section: 'Head'
      }
    },
	created() {
      window.addEventListener('beforeunload', () => {
		//I CAN ACCESS TO this VARIABLE
		console.log("reloaded");
		this.$store.commit('SET_USER', null)
		this.$store.commit('SET_TOKEN', null)
		if (window.localStorage) {
			window.localStorage.setItem('user', null)
			window.localStorage.setItem('token', null)
		}
	}, false)
		this.$router.replace('/login')
    },
    methods: {
      logout () {
        this.$store.commit('SET_USER', null)
        this.$store.commit('SET_TOKEN', null)

        if (window.localStorage) {
          window.localStorage.setItem('user', null)
          window.localStorage.setItem('token', null)
        }

        this.$router.push('/login')
      }
    }
  }
</script>
