<template>
  <!-- Main content -->
  <section class="content">
    <!-- GitHub hint -->
    <!-- /.row -->
    <div class="row center-block">
      <h2>Accounts: </h2>
      <div class="col-md-12">
        <div class="box">
          <div class="box-header">
          </div>
          <!-- /.box-header -->
          <div class="box-body">
            <div>
              <div class="row">
                <div class="col-sm-6">
                  <div id="example1_length" class="dataTables_length">
                  </div>
                </div>
              </div>
              <div class="row">
                <div class="col-sm-12 table-responsive">
                  <table class="table table-bordered table-striped dataTable">
                    <thead>
                    <tr role="row">
                      
                      <th >Name</th>
                      <th>User Type</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr class="even" role="row" v-for="account in accountList"
					v-bind:class="{selected: selectedAccount === account}"
					v-on:click="accountFilter(account)">
                      <td>{{account.tblUserProfile.firstName}} {{account.tblUserProfile.firstName}}</td>
                      <td>{{account.isExternalUser}}</td>
                    </tr>
                    
                  </table>
                </div>
              </div>
              <div class="text-right">
                <button type="submit" class="btn btn-primary" v-on:click="update()">Unlock</button>
              </div>
            </div>
            <!-- /.box-body -->
          </div>
        </div>
      </div>
    </div>
  </section>
  <!-- /.content -->
</template>
<script>
  import $ from 'jquery'
  // Require needed datatables modules
  require('datatables.net')
  require('datatables.net-bs')
  // export default {
  //   name: 'Tables',
  //   mounted() {
  //     this.$nextTick(() => {
  //       $('#example1').DataTable()
  //     })
  //   }
  // }
  import Chart from 'chart.js'
  import Alert from '../widgets/Alert'
  import InfoBox from '../widgets/InfoBox'
  import ProcessInfoBox from '../widgets/ProcessInfoBox'
  import api from '../../api'
import store from '../../store'

  export default {
    name: 'unlockUser',
    components: {
      Alert,
      InfoBox,
      ProcessInfoBox
    },
    data (router) {
      return {
        accountList: [],
		selectedAccount: undefined,
      }
    },
    computed: {
      
    },
    
	
	created () {
		const userId = store.state.user
		api.request('post','./rest/getAllLockedUsers',{})
		.then(response => {
			console.log("this.accountList::"+JSON.stringify(response.data));
			const key = "users"
					if(response.data.length > 0)
					{
						this.accountList = response.data
					}
					else
					{
						this.accountList = []
					}
			
		})
		.catch(error => {
			alert("Error in retrieving locked accounts")
		})
	},
	
	methods: {
		accountFilter(account) {
			console.log("in select");
            this.selectedAccount = account;
        },
		update() {
			
			if(this.selectedAccount != undefined)
			{
				const username = this.selectedAccount.username
				api.request('post','./rest/unlockUser',{username})
				.then(response => {
					//this.accountList = response.data
					alert("User Unlocked")
					
					api.request('post','./rest/getAllLockedUsers',{})
					.then(response => {
						console.log("this.accountList::"+response.data);
						const key = "users"
						if(response.data.length > 0)
						{
							this.accountList = response.data.users
						}
						else
						{
							this.accountList = []
						}
						
						
					})
					.catch(error => {
						alert("Error in retrieving locked accounts")
					})
					
				})
				.catch(error => {
					alert("Error in Unlocking Account")
				})
			}
			else{
				alert("Select Account")
			}
		}
		
	}
  }
</script>
<style>
  .info-box {
    cursor: pointer;
  }
  .info-box-content {
    text-align: center;
    vertical-align: middle;
    display: inherit;
  }
  .fullCanvas {
    width: 100%;
  }
tbody tr.selected {
    color: white;
    background-color: #204874 !important;
}
</style>