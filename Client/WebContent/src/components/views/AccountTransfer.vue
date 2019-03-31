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
						  <label>Enter Account Number To Transfer from<span style="color:red">*</span></label>
							<input class="form-control" type="number" pattern=".{8,8}" v-model = "toAccountNumber" required />
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
				  <h3 class="box-title">Account Details</h3>
				</div>
				<div class="box-body">
					  <div class="row">
						  <div class="col-xs-12">
							<table class="table table-bordered table-responsive">
								<thead>
									<tr>
										<th>From Account Number</th>
										<th>Name</th>
										<th>Account Type</th>
									</tr>
								</thead>
								
								<tbody>
									<tr class="active">
										<td>{{toAccount.accountId}}</td>
										<td>{{toAccount.tblUser.tblUserProfile.firstName}} {{toAccount.tblUser.tblUserProfile.lastName}}</td>
										<td>{{toAccount.accountType | changeAcc}}</td>
									</tr>
								</tbody>
							</table>
						  </div>
					  </div>
					  <div class="row">
					  <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
						  <h4>Select To Account<span style="color:red">*</span></h4>
						  <div class="form-group">
							<select class="form-control" v-model="fromAccount" required>
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
						
						<input class="form-control" v-model="amount" type="number" min="0.01" step="0.01" max="100000" oninput="validity.valid||(value='');" required />
						
					  </div>
				  </div>
			  </div>
				</div>
			</div>
			<div class="text-right">
		  <button type="submit" class="btn btn-primary" v-on:click="enableOtpBox()">Submit</button>
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
								<input class="form-control" v-model="otpValue" type="number" min="0" step="1" max="999999" oninput="validity.valid||(value='');" required @focus="show" data-layout="numeric" readonly/>
								
							</div>
							<vue-touch-keyboard :options="options" v-if="visible" :layout="layout" :cancel="hide" :accept="accept" :input="input" />
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
	  accountList: [],
	  fromAccountNumber: undefined,
	  toAccountNumber: undefined,
	  fromAccount: undefined,
	  toAccount: undefined,
	  otpValue: undefined,
	  showOTPBox: false,
	  visible: false,
      layout: "normal",
      input: null,
      options: {
        useKbEvents: false,
        preventClickEvent: false
      }
    }
  },
  methods: {
	accept(text) {
      
      this.hide();
    },
    show(e) {
      this.input = e.target;
      this.layout = e.target.dataset.layout;
      if (!this.visible)
        this.visible = true
    },
    hide() {
      this.visible = false;
    },
	retrieveData() {
		console.log("ins")
		this.showResult = false;
		var validateValue = this.validateValue();
		if(validateValue==true)
		{
			const accountId = this.toAccountNumber;
			api
			.request('post', './rest/accountByAccountParams', {accountId})
			.then(response => {
				var response = response.data;
				if(response == undefined || response.length == 0)
				{
					alert("Account not found");
					return;
				}
				console.log("on load::"+JSON.stringify(response));
				this.toAccount = response[0];
				this.showResult = true;
			})
			.catch(error => {
			   console.log("Account not found");
			   alert("Enter Account Number");
			   //alert("Error in Transaction! Please contact administrator");
			 })
		}
		else
		{
			alert("Enter Account Number");
		}
		
	},
	
	validateValue() {
		console.log(this.toAccountNumber);
		if(this.toAccountNumber == undefined || this.toAccountNumber == '')
		{
			console.log("inside return");
			return false;
		}
		return true;
	},
	
	enableOtpBox() {
		const userId = store.state.user
		api.request('post','./rest/generateOtp',{userId})
			.then(response => {
				console.log("success");
				alert("Enter OTP");
				this.showOTPBox = true
				//console.log("true");
				
			})
			.catch(error => {
				alert("Error in generating otp:::"+error)
				this.showOTPBox = false
				return;
			})
	},
	
	submit() 
	{
		var validateValue = this.validate();
		if(validateValue == false)
		{
			console.log("enter all");
			alert("Enter all required values");
			return;
		}
		if(this.otpValue == undefined || this.otpValue == '' || this.otpValue.length != 6)
		{
					alert("Invalid Otp value!! redirecting to login page");
					
					this.showOTPBox = false
					return;
		}
		//const from_account = this.fromAccount;
		//const to_account = this.toAccount.accountId;
		const from_account = this.toAccount.accountId;
		const to_account = this.fromAccount
		const transaction_amount = this.amount;
		const type = 3;
		const user_id = store.state.user
		const userId = store.state.user
					const otpValue = this.otpValue;
					api.
					request('post','./rest/validateOtp',{otpValue})
					.then(response =>
					{
						console.log("response::"+JSON.stringify(response))
						if(response.data === "SUCCESS")
						{
						api
						.request('post', './rest/transaction', {transaction_amount, from_account, to_account, type, user_id  })
						.then(response => {
							var response = response.data;
							console.log("response::"+response);
							if(response === 'OK')
							{
								console.log("good");
								alert("Transaction Submitted!");
								//this.otpValue = undefined
								this.showOTPBox = false
							}
							else if(response === "")
							{
								alert("Transaction Declined! Insufficient Balance");
								
								this.showOTPBox = false
							}
						
						})
						.catch(error => {
						   console.log("error");
						   alert("Error in Transaction! Please contact administrator");
						 })
						}
						else
						{
							alert("invalid otp");
							this.$router.push('/login')
							this.showOTPBox = false
							return;
						}
					})
					.catch(error => {
							alert("error in validating otp");
							this.$router.push('/login')
							this.showOTPBox = false
							return;
					})
				
		this.showOTPBox = false
		this.otpValue = undefined
	
	},
	
	validate()
	{
		var validateValue = true;
		if(this.amount < 0 || this.amount > 100000)
		{
			return false;
		}
		if(this.fromAccount == undefined)
		{
			return false;
		}
		if(this.toAccount == undefined)
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