<template>
  <div>
	<h1 class="text-center">Debit/Credit</h1>
	  <section class="content">
			<div class="box box-info">
				<div class="box-header with-border">
				  <h3 class="box-title">Inputs</h3>
				</div>
				<form method="POST" action="">
				<div class="box-body">
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
						<label>Type of Transaction<span style="color:red">*</span></label>
						<label for="Credit" class="radio-inline"><input type="radio" id="Credit" value="1" v-model="typeOfTransaction" required />Credit</label>
						<label for="Debit" class="radio-inline"><input type="radio" id="Debit" value="2" v-model="typeOfTransaction" required />Debit</label>
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
					  <div>

				</div>
            </div>
        </div>
				</form>
			</div>
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="text-right">
						<button type="submit" class="btn btn-primary" v-on:click="submit()">Submit</button>
					</div>
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
      amount: undefined,
	  typeOfTransaction: undefined,
	  account: undefined,
	  showDismissibleAlert: false,
	  accountList: []
    }
  },
  methods: {
	submit()
	{
		var validateValue = this.validate();
		if(validateValue == false)
		{
			console.log("enter all");
			alert("Enter all required values");
			return;
		}
		const from_account = this.account;
		const to_account = this.account;
		const transaction_amount = this.amount;
		const type = this.typeOfTransaction;
		const user_id = store.state.user;
		
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

	validate()
	{
		var validateValue = true;
		if(this.amount < 0 || this.amount > 100000)
		{
			return false;
		}
		if(this.typeOfTransaction != '1' && this.typeOfTransaction != '2')
		{
			return false;
		}
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
	const token = store.state.token;
	console.log("token:::"+token);
	let tok = token.split(' ')[1]
	console.log("tok:::"+tok)
	//const head = Authorization: `Bearer ${tok}`;
	//console.log("head::"+head);
		
		const header = {
			headers: {
				'Authorization' : token,
				'Content-Type' : 'application/json'
			}
		}
	api
        .request('post', './rest/userAccounts',{userId})
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
