<template>
  <div>
	<h1 class="text-center">Transfer Between Accounts</h1>
	  <section class="content">
			<div class="box box-info">
				<div class="box-header with-border">
				  <h3 class="box-title">Inputs</h3>
				</div>
				<div class="box-body">
					  <div class="row">
						  <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 col-xs-offset-4">
						  <label>Enter Account Number<span style="color:red">*</span></label>
							<input class="form-control" type="number" pattern=".{8,8}" required />
						  </div>
						  <div class="col-xs-3">
								<button class="btn btn-success" v-on:click="retrieveData()">Search</button>
						  </div>
					  </div>
					  
				</div>
			</div>
			
			<div v-if="showResult">
			<div class="box box-info">
				<div class="box-header with-border">
				  <h3 class="box-title">Account</h3>
				</div>
				<div class="box-body">
					  <div class="row">
						  <div class="col-xs-12">
							<table class="table table-responsive">
								<thead>
									<tr>
										<th>Account Number</th>
										<th>Name</th>
										<th>Account Type</th>
									</tr>
								</thead>
								
								<tbody>
									<tr class="active">
										<td>23234232</td>
										<td>QWds Dfdfd</td>
										<td>Checking</td>
									</tr>
								</tbody>
							</table>
						  </div>
					  </div>
					  <div class="row">
				  <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					  <div class="input-group">
						<span class="input-group-addon">
						  <i class="fa fa-fw fa-usd" aria-hidden="true"></i>
						</span>
						<h4>Enter Amount<span style="color:red">*</span></h4>
						<input class="form-control" type="number" min="0.01" step="0.01" max="100000" oninput="validity.valid||(value='');" required>
					  </div>
				  </div>
			  </div>
				</div>
			</div>
			<div class="text-right">
		  <button type="submit" class="btn btn-primary">Submit</button>
		  </div>
			</div>
	  </section>
  </div>
</template>

<script>
import api from '../../api'
import store from '../../store'

export default {
  name: 'AccountTransfer',
  data(router) {
    return {
      showResult: false,
	  accountList: []
    }
  },
  methods: {
	retrieveData() {
		console.log("ins")
		this.showResult = true;
	}
  },
  
  created() {
	console.log("inside created");
	const userId = store.state.user
	api
        .request('post', './rest/userAccounts', {userId})
		.then(response => {
			var response = response.data;
			this.accountList = response;
			console.log("on load::"+JSON.stringify(response));
		})
		.catch(error => {
           console.log("error");
		   //alert("Error in Transaction! Please contact administrator");
         })
  }
  }
</script>