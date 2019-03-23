<!--<template>-->
  <!--<ul class="sidebar-menu">-->
    <!--<li class="header">TOOLS</li>-->
    <!--<router-link tag="li" class="pageLink" to="/dashboard">-->
      <!--<a>-->
        <!--<i class="fa fa-desktop"></i>-->
        <!--<span class="page">Dashboard</span>-->
      <!--</a>-->
    <!--</router-link>-->
    <!--<router-link tag="li" class="pageLink" to="/tables">-->
      <!--<a>-->
        <!--<i class="fa fa-table"></i>-->
        <!--<span class="page">Tables</span>-->
      <!--</a>-->
    <!--</router-link>-->

    <!--<li class="header">ME</li>-->
    <!--<router-link tag="li" class="pageLink" to="/tasks">-->
      <!--<a>-->
        <!--<i class="fa fa-tasks"></i>-->
        <!--<span class="page">Tasks</span>-->
      <!--</a>-->
    <!--</router-link>-->
    <!--<router-link tag="li" class="pageLink" to="/setting">-->
      <!--<a>-->
        <!--<i class="fa fa-cog"></i>-->
        <!--<span class="page">Settings</span>-->
      <!--</a>-->
    <!--</router-link>-->
    <!--<li class="treeview">-->
      <!--<a href="#">-->
        <!--<i class="fa fa-folder-o"></i>-->
        <!--<span class="treeview-title">Files</span>-->
        <!--<span class="pull-right-container pull-right">-->
          <!--<i class="fa fa-angle-left fa-fw"></i>-->
        <!--</span>-->
      <!--</a>-->
      <!--<ul class="treeview-menu">-->
        <!--<li>-->
          <!--<a href="#">-->
            <!--<i class="fa fa-file-word-o"></i> Item 1-->
          <!--</a>-->
        <!--</li>-->
        <!--<li>-->
          <!--<a href="#">-->
            <!--<i class="fa fa-file-picture-o"></i> Item 2-->
          <!--</a>-->
        <!--</li>-->
        <!--<li>-->
          <!--<a href="#">-->
            <!--<i class="fa fa-file-pdf-o"></i> Item 3-->
          <!--</a>-->
        <!--</li>-->
      <!--</ul>-->
    <!--</li>-->

    <!--<li class="header">LOGS</li>-->
    <!--<router-link tag="li" class="pageLink" to="/access">-->
      <!--<a>-->
        <!--<i class="fa fa-book"></i>-->
        <!--<span class="page">Access</span>-->
      <!--</a>-->
    <!--</router-link>-->
    <!--<router-link tag="li" class="pageLink" to="/repos">-->
      <!--<a>-->
        <!--<i class="fa fa-heart"></i>-->
        <!--<span class="page">Repos</span>-->
        <!--<small class="label pull-right bg-green">AJAX</small>-->
      <!--</a>-->
    <!--</router-link>-->

    <!--<li class="header">PAGES</li>-->
    <!--<router-link tag="li" class="pageLink" to="/404">-->
      <!--<a>-->
        <!--<i class="fa fa-circle-o text-red"></i>-->
        <!--<span class="page"> 404</span>-->
      <!--</a>-->
    <!--</router-link>-->
  <!--</ul>-->
<!--</template>-->
<!--<script>-->
<!--export default {-->
  <!--name: 'SidebarMenu'-->
<!--}-->
<!--</script>-->
<!--<style>-->
<!--/* override default */-->
<!--.sidebar-menu > li > a {-->
  <!--padding: 12px 15px 12px 15px;-->
<!--}-->

<!--.sidebar-menu li.active > a > .fa-angle-left,-->
<!--.sidebar-menu li.active > a > .pull-right-container > .fa-angle-left {-->
  <!--animation-name: rotate;-->
  <!--animation-duration: 0.2s;-->
  <!--animation-fill-mode: forwards;-->
<!--}-->

<!--.treeview-title {-->
  <!--z-index: 1;-->
<!--}-->

<!--@keyframes rotate {-->
  <!--0% {-->
    <!--transform: rotate(0deg);-->
  <!--}-->

  <!--100% {-->
    <!--transform: rotate(-90deg);-->
  <!--}-->
<!--}-->
<!--</style>-->

<template>
  <ul class="sidebar-menu">
    <li class="header">TOOLS</li>
    <router-link tag="li" class="pageLink" to="/dashboard">
      <a>
        <i class="fa fa-desktop"></i>
        <span class="page">Dashboard</span>
      </a>
    </router-link>
    <!--<li class="header" v-if="moduleMap.AccountTransactionModule">Transact</li>-->
    <li class="header">Transact</li>
    <!--<router-link tag="li" class="pageLink" v-if="moduleMap.AccountTransactionModule" to="/creditdebit">-->
      <router-link tag="li" class="pageLink" to="/creditdebit">
      <a>
        <i class="fa fa-table"></i>
        <span class="page">Credit/Debit</span>
      </a>
    </router-link>
    <!--<router-link tag="li" class="pageLink" v-if="moduleMap.AccountTransactionModule" to="/account">-->
    <router-link tag="li" class="pageLink" to="/account">
    <a>
        <i class="fa fa-table"></i>
        <span class="page">Between Accounts</span>
      </a>
    </router-link>
    <!--<router-link tag="li" class="pageLink" v-if="moduleMap.AccountTransactionModule" to="/email">-->
    <router-link tag="li" class="pageLink" to="/email">
    <a>
        <i class="fa fa-table"></i>
        <span class="page">Email/Phone</span>
      </a>
    </router-link>
    <li class="header">View Transactions</li>
    <router-link tag="li" class="pageLink" to="/approvedecline">
      <a>
        <i class="fa fa-tasks"></i>
        <span class="page">Approve/Decline</span>
      </a>
    </router-link>

    <li class="header">Request</li>
    <router-link tag="li" class="pageLink" to="/accountCreation">
      <a>
        <i class="fa fa-book"></i>
        <span class="page">Account Creation</span>
      </a>
    </router-link>
    <router-link tag="li" class="pageLink" to="/repos">
      <a>
        <i class="fa fa-book"></i>
        <span class="page">Update Information</span>
      </a>
    </router-link>
    <router-link tag="li" class="pageLink" to="/createAppointment">
      <a>
        <i class="fa fa-book"></i>
        <span class="page">Create Appointment</span>
      </a>
    </router-link>
    <li class="header">Appointment</li>
    <router-link tag="li" class="pageLink" to="/viewAppointment">
      <a>
        <i class="fa fa-circle-o text-red"></i>
        <span class="page">View</span>
      </a>
    </router-link>

    <li class="header">Troubleshoot</li>
    <router-link tag="li" class="pageLink" to="/viewAccount">
      <a>
        <i class="fa fa-circle-o text-red"></i>
        <span class="page">View Accounts</span>
      </a>
    </router-link>

  </ul>
</template>
<script>
import store from '../../store'
  export default {
    name: 'SidebarMenu',
	data() {
	return
	{
		moduleMap: null
	}
	},
	created: function()
	{
		this.moduleMap = store.state.moduleMap
		console.log("this.moduleMap::"+this.moduleMap)
	}
  }
</script>
<style>
  /* override default */
  .sidebar-menu > li > a {
    padding: 12px 15px 12px 15px;
  }
  .sidebar-menu li.active > a > .fa-angle-left,
  .sidebar-menu li.active > a > .pull-right-container > .fa-angle-left {
    animation-name: rotate;
    animation-duration: 0.2s;
    animation-fill-mode: forwards;
  }
  .treeview-title {
    z-index: 1;
  }
  @keyframes rotate {
    0% {
      transform: rotate(0deg);
    }
    100% {
      transform: rotate(-90deg);
    }
  }
</style>
