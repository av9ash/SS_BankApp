<template>
  <div>
	<h1 class="text-center">Transfer By Email/Phone</h1>
	  <section class="content">
			<div class="box box-info">
				<div class="box-header with-border">
				  <h3 class="box-title">Inputs</h3>
				</div>
				<div class="box-body">
					  <div class="row">
						  <div class="col-xs-5">
						  
							<label>Email:<span style="color:red">*</span></label>
							<input type="email" class="form-control" v-model="email" />
						  </div>
						  
						  <div class="col-xs-5">
						  
							<label>Phone:<span style="color:red">*</span></label>
							<input type="text" pattern= "\d+" v-model="phone" class="form-control" maxlength="10" />
						  
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
				  <h3 class="box-title">Accounts</h3>
				</div>
				<div class="box-body">
					  <div class="row">
						  <div class="col-xs-12">
							<table class="table table-responsive table-bordered table-striped">
								<thead>
									<tr>
										<th>Account Number</th>
										<th>First Name</th>
										<th>Last Name</th>
										<th>Account Type</th>
									</tr>
								</thead>
								
								<tbody>
									<tr v-for="account in accounts"
										v-bind:class="{selected: selectedAccount === account}"
										v-on:click="accountFilter(account)">
										<td>{{account.account_id}}</td>
										<td>{{firstName}}</td>
										<td>{{lastName}}</td>
										<td>{{account.account_type}}</td>
									</tr>
								</tbody>
							</table>
						  </div>
					  </div>
					  <div class="row">
					  <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
						  <h4>Select Account<span style="color:red">*</span></h4>
						  <div class="form-group">
							<select class="form-control" v-model="account" required>
							  <option value='' disabled selected>Select an Account</option>
							  <option v-for="account in accountList" :value="account.accountId">{{account.accountId}} - {{account.accountType}}</option>
							</select>
						  </div>
						  </div>
					  </div>
				  <div class="row">
				  <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				  <label>Enter Amount<span style="color:red">*</span></label>
					  <div class="input-group">
						<span class="input-group-addon">
						  <i class="fa fa-fw fa-usd" aria-hidden="true"></i>
						</span>
						<input class="form-control" type="number" min="0.01" step="0.01" max="100000" oninput="validity.valid||(value='');" v-model="amount">
					  </div>
				  </div>
			  </div>
				</div>
			</div>
			<div class="text-right">
		  <button type="submit" class="btn btn-primary" v-on:click = "enableOtpBox()">Submit</button>
		  </div>
			</div>
		<div v-if="showOTPBox">
			<div class="box box-info">
				<div class="box-header with-border">
				  <h3 class="box-title">OTP Value</h3>
				</div>
				<div class="box-body">
					  <div class="row">
						  <div class="col-xs-12">
							<div class="input-group">
								<input class="form-control" v-model="otpValue" type="number" min="0" step="1" max="999999" oninput="validity.valid||(value='');" required />
							</div>
						  </div>
					  </div>
				</div>
			</div>
			<div class="text-right">
		  <button type="submit" class="btn btn-primary" v-on:click="submit()">Validate</button>
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
	  email: undefined,
	  phone: undefined,
	  firstName: undefined,
	  lastName: undefined,
	  accounts: [],
	  displayedEmail: undefined,
	  displayedPhone: undefined,
	  selectedAccount: undefined,
	  accounts: [],
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
	
	enableOtpBox() {
		const userId = store.state.user
		api.request('post','./rest/generateOtp',{userId})
			.then(response => {
				console.log("succes");
				this.showOTPBox = true
				console.log("true");
				
			})
			.catch(error => {
				alert("Error in generating otp:::"+error)
				this.showOTPBox = false
				return;
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

<style>
tbody tr.selected {
    color: white;
    background-color: #204874 !important;
}
</style>