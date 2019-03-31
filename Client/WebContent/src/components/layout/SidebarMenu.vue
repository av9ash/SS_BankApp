<template>
  <ul class="sidebar-menu">
    <li class="header">TOOLS</li>
    <router-link tag="li" class="pageLink" to="/dashboard">
      <a>
        <i class="fa fa-desktop"></i>
        <span class="page">Dashboard</span>
      </a>
    </router-link>
    <li class="header" v-if="moduleMap.AccountTransactionModule">Transact</li>
    <!-- <li class="header">Transact</li> -->
    <router-link tag="li" class="pageLink" v-if="moduleMap.AccountTransactionModule" to="/creditdebit">
      <!-- <router-link tag="li" class="pageLink" to="/creditdebit"> -->
      <a>
        <i class="fa fa-table"></i>
        <span class="page">Credit/Debit</span>
      </a>
    </router-link>
<!-- <router-link tag="li" class="pageLink" to="/account"> -->
    <router-link tag="li" class="pageLink" v-if="moduleMap.AccountTransactionModule" to="/accountTransfer">
      <a>
        <i class="fa fa-table"></i>
        <span class="page">Between Accounts</span>
      </a>
    </router-link>
    <!-- <router-link tag="li" class="pageLink" to="/email"> -->
    <router-link tag="li" class="pageLink" v-if="moduleMap.AccountTransactionModule" to="/emailPhoneTransfer">
      <a>
        <i class="fa fa-table"></i>
        <span class="page">Email/Phone</span>
      </a>
    </router-link>
    <li class="header" v-if="moduleMap.RequestModule && !moduleMap.ViewAndModifyModule">Authorize</li>
    <router-link tag="li" class="pageLink" v-if="moduleMap.RequestModule && !moduleMap.ViewAndModifyModule" to="/approvedecline">
      <a>
        <i class="fa fa-tasks"></i>
        <span class="page">Approve/Decline Transactions</span>
      </a>
    </router-link>
	<router-link tag="li" class="pageLink" v-if="moduleMap.RequestModule && !moduleMap.ViewAndModifyModule" to="/accountApproval">
      <a>
        <i class="fa fa-tasks"></i>
        <span class="page">Approve/Decline Accounts</span>
      </a>
    </router-link>

    <li class="header" v-if="moduleMap.OpenAccount">Request</li>
    <router-link tag="li" class="pageLink" v-if="moduleMap.OpenAccount" to="/accountCreation">
      <a>
        <i class="fa fa-book"></i>
        <span class="page">Account Creation</span>
      </a>
    </router-link>
   <router-link tag="li" class="pageLink" v-if="moduleMap.OpenAccount" to="/updateInformation">
      <a>
        <i class="fa fa-book"></i>
        <span class="page">Update Information</span>
      </a>
    </router-link>
	<router-link tag="li" class="pageLink" v-if="moduleMap.OpenAccount" to="/createAppointment">
      <a>
        <i class="fa fa-book"></i>
        <span class="page">Create Appointment</span>
      </a>
    </router-link>
    <li class="header" v-if="moduleMap.RequestModule">Appointments</li>
    <router-link tag="li" class="pageLink" v-if="moduleMap.RequestModule" to="/viewAppointment">
      <a>
        <i class="fa fa-circle-o text-red"></i>
        <span class="page">View Appointments</span>
      </a>
    </router-link>

    <li class="header" v-if="moduleMap.ViewAccountModule">Troubleshoot</li>
    <router-link tag="li" class="pageLink" v-if="moduleMap.ViewAccountModule" to="/viewAccount">
      <a>
        <i class="fa fa-circle-o text-red"></i>
        <span class="page">View Accounts</span>
      </a>
    </router-link>
	<router-link tag="li" class="pageLink" v-if="moduleMap.ViewLogFile" to="/unlockUser">
      <a>
        <i class="fa fa-circle-o text-red"></i>
        <span class="page">Unlock User</span>
      </a>
    </router-link>

	<li class="header" v-if="moduleMap.ViewLogFile">System Logs</li>
    <router-link tag="li" v-if="moduleMap.ViewLogFile" class="pageLink" to="/viewLogs">
      <a>
        <i class="fa fa-circle-o text-red"></i>
        <span class="page">View Logs</span>
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
