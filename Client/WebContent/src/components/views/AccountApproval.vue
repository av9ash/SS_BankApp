<template>
  <div>
	<h1 class="text-center">Process Requests</h1>
	  <section class="content">
			<div class="box box-info">
				<div class="box-header with-border">
				  <h3 class="box-title">Pending Requests</h3>
				</div>
				<div class="box-body">
					  <div class="row">
						  <div class="col-xs-12">
						  
							<table class="table table-responsive table-bordered table-striped">
								<thead>
									<tr>
										<th>Requested By</th>
										<th>Type Of Account</th>
										<th>Date Requested On</th>
									</tr>
								</thead>
								
								<tbody>
									<tr v-for="request in requests"
										v-bind:class="{selected: selectedAccount === request}"
										v-on:click="accountFilter(request)">
										<td>{{request.tblUserByRequestedBy.tblUserProfile.firstName}} {{request.tblUserByRequestedBy.tblUserProfile.firstName}}</td>
										<td>{{request.typeOfAccount}}</td>
										<td>{{request.tblUserByRequestedBy.createdDate}}</td>
									</tr>
								</tbody>
							</table>
					  </div>
					  
				</div>
			</div>
			</div>
			<div class="text-right">
			<button type="submit" class="btn btn-primary" v-on:click="approve()">Approve</button>
			<!-- <button type="submit" class="btn btn-primary">Decline</button> -->
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
	  email: undefined,
	  phone: undefined,
	  firstName: undefined,
	  lastName: undefined,
	  accounts: [],
	  displayedEmail: undefined,
	  displayedPhone: undefined,
	  selectedAccount: undefined,
	  requests: [],
	  account: undefined
    }
  },
  methods: {
	retrieveData() {
		console.log("ins")
		this.selectedAccount = undefined;
		var validateValue = this.validate();
		if(validateValue == false)
		{
			alert("Enter either Phone/Email");
			return;
		}
		
		if(this.email == '')
		{
			this.email = undefined
		}
		if(this.phone == '')
		{
			this.phone = undefined
		}
		
		const email = this.email;
		const phone = this.phone;
		api
        .request('post', './rest/searchAccount', {email, phone  })
		.then(response => {
			var response = response.data;
			console.log("response:::"+JSON.stringify(response));
			this.firstName = response.first_name;
			this.lastName = response.last_name;
			this.accounts = response.accounts;
			this.showResult = true;
		})
		.catch(error => {
           console.log("error"+error);
		   alert("Error in Transaction! Please contact administrator");
         })
		
	},
	
	
	
	validate() {
		var validateValue = true;
		console.log("this.phone:::"+this.phone);
		console.log(this.phone == '');
		console.log(this.phone == undefined);
		if((this.email == undefined && this.phone == undefined) || (this.email == '' && this.phone == '')
		|| (this.email == '' && this.phone == undefined) || (this.email == undefined && this.phone == ''))
		{
			return false;
		}
	},
	accountFilter(account) {
			console.log("in select");
            this.selectedAccount = account;
        },
		
		submit() 
		{	
			var validateValue = this.validateTransaction();
			if(validateValue == false)
			{
				console.log("enter all");
				alert("Enter all required values");
				return;
			}
			const from_account = this.account;
		const to_account = this.selectedAccount.account_id;
		const transaction_amount = this.amount;
		const type = 3;
		const user_id = store.state.user
		
		api
        .request('post', './rest/transaction', {transaction_amount, from_account, to_account, type, user_id  })
		.then(response => {
			var response = response.data;
			console.log("response");
			if(response === 'OK')
			{
				console.log("good");
				alert("Transaction Submitted!");
			}
			else if(response === "")
			{
				alert("Transaction Declined! Insufficient Balance");
			}
		
		})
		.catch(error => {
           console.log("error");
		   alert("Error in Transaction! Please contact administrator");
         })
		},
		
		
		validateTransaction()
		{
			var validateValue = true;
			console.log("this.amount::"+this.amount);
			if(this.amount == undefined || this.amount <= 0 || this.amount > 100000)
			{
				return false;
			}
			console.log("this.selectedAccount::"+this.selectedAccount);
			if(this.selectedAccount == undefined)
			{
				return false;
			}
			console.log("this.account::"+this.account);
			if(this.account == undefined)
			{
				return false;
			}			
			
			return validateValue;
		},
		approve() {
			if(this.selectedAccount == undefined)
			{
				alert("Select an account");
				return;
			}
			const request = {"requestId":this.selectedAccount.requestId};
			const user = {"userId":store.state.user}
			api.request('post','./rest/approveNewAccount',{request,user })
			.then(response =>
			{
				var responseData = response.data;
				console.log("response::"+responseData)
				if(responseData != undefined)
				{
					const userId = store.state.user
					api
					.request('post', './rest/pendingRequests', {userId})
					.then(response => {
						var response = response.data;
						this.requests = [];
						if(response.length == 0)
						{
							return;
						}	
						for(var i=0;i<response.length;i++)
						{
							if(response[i].typeOfRequest == 5)
							{
								this.requests.push(response[i]);
							}
						}
						//this.requests = response;
						console.log("on load::"+this.requests);
					})
					.catch(error => {
					   console.log("error");
					   //alert("Error in Transaction! Please contact administrator");
				 })
				 alert("Account Created Successfully");
				}
				else
				{
					alert("Request Processing Failed");
				}
			}).
			catch(error =>
			{
				alert("Request Processing Failed");
			})
		  },
		  
		  decline() {
			if(this.selectedAccount == undefined)
			{
				alert("Select an account");
				return;
			}
			const request = {"requestId":this.selectedAccount.requestId};
			const user = {"userId":store.state.user}
			api.request('post','./rest/declineTransaction',{request,user })
			.then(response =>
			{
				var responseData = response.data
				if(responseData === "OK")
				{
					const userId = store.state.user
					api
					.request('post', './rest/pendingRequests', {userId})
					.then(response => {
						var response = response.data;
						this.requests = [];
						if(response.length == 0)
						{
							return;
						}	
						for(var i=0;i<response.length;i++)
						{
							if(response[i].typeOfRequest == 5)
							{
								this.requests.push(response[i]);
							}
						}
						//this.requests = response;
						console.log("on load::"+JSON.stringify(response));
					})
					.catch(error => {
					   console.log("error");
					   //alert("Error in Transaction! Please contact administrator");
				 })
				 alert("Request Decline Successfully");
				}
				else
				{
					alert("Request Processing Failed");
				}
			})
			.catch(error =>
			{
				alert("Request Processing Failed");
			})
		  }
	},
	created() {
	console.log("inside created");
	const userId = store.state.user
	api
        .request('post', './rest/pendingRequests', {userId})
		.then(response => {
			var response = response.data;
			if(response.length == 0)
			{
				return;
			}	
			for(var i=0;i<response.length;i++)
			{
				if(response[i].typeOfRequest == 5)
				{
					this.requests.push(response[i]);
				}
			}
			//this.requests = response;
			console.log("on load::"+JSON.stringify(this.requests));
		})
		.catch(error => {
           console.log("error");
		   //alert("Error in Transaction! Please contact administrator");
         })
  }
		
  
  
  
  }
</script>

<style>
tbody tr.selected {
    color: white;
    background-color: #204874 !important;
}
</style>