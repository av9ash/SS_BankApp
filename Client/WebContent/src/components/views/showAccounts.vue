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
                  <table  class="table table-bordered table-striped">
                    <thead>
                    <tr role="row">
                      <th>Account ID</th>
                      <th>Name</th>
                      <th>Account Type</th>
                      <th>Amount</th>
                    </tr>
                    </thead>
                    <tbody>
                    
                    <tr v-for="account in accountList">
                      <td>{{account.account_id}}</td>
                      <td>{{account.FirstName}} {{account.LastName}}</td>
                      <td>{{account.account_type}}</td>
                      <td>{{account.current_amount}}</td>
                    </tr>
                    </tbody>
                  </table>
                </div>
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
import api from '../../api'
import store from '../../store'
  export default {
    name: 'showAccounts',
    data (router) {
      return {
        accountList: undefined
      }
    },
	created () {
		const userId = store.state.user
		api.request('post','./rest/getAllAccounts',{userId})
		.then(response => {
			this.accountList = response.data
			
		})
		.catch(error => {
			alert("Error in retrieving Accuonts")
		})
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
</style>