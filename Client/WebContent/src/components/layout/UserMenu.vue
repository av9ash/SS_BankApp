<template>
  <li class="dropdown user user-menu">
    <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown">
      <!-- The user image in the navbar-->
      <!-- hidden-xs hides the username on small devices so only the image appears. -->
      <span class="hidden-xs">{{username}}</span>
    </a>
    <!-- Account Info and Menu -->
    <ul class="dropdown-menu">
      <li class="user-header" style="height:auto;min-height:85px;padding-bottom:15px;">
        <p>
          <span>{{username}}</span>
          
        </p>
      </li>
      <li class="user-footer">

        <router-link tag="li" class="pageLink" to="/">
          <a href="javascript:;" class="btn btn-default btn-flat btn-block pageLink" to="/">
            <i class="fa fa-sign-out"></i>
            <span>Logout</span>
          </a>
        </router-link>
        <!--<a href="javascript:;" class="btn btn-default btn-flat btn-block pageLink" to="/">-->
          <!--<i class="fa fa-sign-out"></i>-->
          <!--<span>Logout</span>-->
        <!--</a>-->
      </li>
    </ul>
  </li>
</template>

<script>
import store from '../../store'
export default {

  name: 'UserMenu',
  props: ['user'],
  data(router)
  {
	username: undefined
  },
  created () {
	this.username = store.state.name
  }
  
}
</script>
