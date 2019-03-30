<template>
  <div>
    <section class="content">

      <div class="row">
        <div class="col-md-12">
          <div class="box box-info">
            <div class="box-header with-border">
              <h3 class="box-title">Create Account</h3>
            </div>
            <!-- Input Addons -->
            
            <div class="box-body">

              <!-- calendar group -->
              
              <br />
              <br />

              <!-- with icons from font awesome -->

              <div class="row">
					  <div class="col-xs-12">
						  <h4>Select Type of Account<span style="color:red">*</span></h4>
						  <div class="form-group">
							<select class="form-control" v-model="account" required>
							  <option value='' disabled selected>Select a Type of Account</option>
							  <option v-for="account in accountList" :value="account.accountValue">{{account.accountType}}</option>
							</select>
						  </div>
						  </div>
			  </div>
					  <div class="text-right">
              <button type="submit" class="'btn btn-primary '" v-on:click="submit()">Submit Request</button>
				</div>
            
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
            <br />
            <br />
            <!-- /.box-body -->
          </div>

        </div>
      </div>


    </section>
  </div>
</template>
<script>
import api from '../../api'
import store from '../../store'
  require('moment');

  export default {
    name: 'createAccount',
    components: { },
	data(router) {
	return {
		accountList: [],
		account: undefined
		}
	},
    created() {
      this.accountList = [{"accountType":"Checking","accountValue":"1"},{"accountType":"Savings","accountValue":"2"}]
    },
    methods: {
      clearInput (vueModel) {
        vueModel = ''
      },
    
      submit()
	{
        if(this.account == undefined || this.account == '')
		{
			alert("Select an Account type");
			return;
		}
        const user = {"userId":store.state.user}
		var typeOfAccount = this.account
		const request = {"typeOfAccount":typeOfAccount}
        api
        .request('post','./rest/account',{user, request})
        .then(response => {
            var response = response.data;
            console.log("response");
            if(response === 'OK')
			{
				console.log("good");
				alert("Request for Account Creation Submitted");
			}
			else if(response === "FAIL")
			{
				alert("Error in Request for Account Creation");
			}
            
        })
        .catch(error => {
           console.log("error");
		   alert("Error in Request for Account Creation! Please contact administrator");
         })
        
	}
    }
  }


</script>
