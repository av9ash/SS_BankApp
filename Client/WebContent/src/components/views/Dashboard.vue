<template>
  
  <section class="content">
    
<div v-if="moduleMap.AccountTransactionModule">

    <div class="form-group">
			<select class="form-control" required v-on:change="changeItem(account,$event)">
							  <option value='' disabled selected>Select an Account</option>
							  <option v-for="account in accountList" :value="account.accountId" >Account number : {{account.accountId}} - Account type : {{account.accountType | changeAcc}}</option>
							</select>
						  </div>
    <br />
<!--    <button class="'btn btn-primary btn-lg '" v-on:click="getTransactions()">Submit</button>-->

    <div class="row">


      <!-- Info boxes -->
      
      <!-- /.col -->
      
      <!-- /.col -->

    </div>
    <!-- /.row -->

    <!-- /.row -->

    <div class="row center-block">
      <h2>Transaction History: </h2>
        
      <div class="col-md-12">
        <div class="box">
          <div class="box-header">
          </div>
          <!-- /.box-header -->
          <div class="box-body">
            <div class="dataTables_wrapper form-inline dt-bootstrap" id="example1_wrapper">
              <div class="row">
                <div class="col-sm-6">
                  <div id="example1_length" class="dataTables_length">

                  </div>
                </div>
              </div>

              <div class="row">
                <div class="col-sm-12 table-responsive">
                  <table  role="grid"  class="table table-bordered table-striped">
                    <thead>
                    <tr role="row">
                      <th style="width: 167px;" colspan="1" rowspan="1"  tabindex="0" >transaction Created Date</th>
                      <th style="width: 182px;" colspan="1" rowspan="1"  tabindex="0" >transaction Status</th>
                      <th style="width: 142px;" colspan="1" rowspan="1"  tabindex="0">transaction Amount ($)</th>
                    </tr>
                    </thead>
                    <tbody>
                     <tr class="even" role="row" > 
                         <td class="sorting_1">   </td>
                         <td class="sorting_1">   </td>
                        <td class="sorting_1">   </td>
                        </tr>
                        <tr class="odd" role="row" v-for="transaction in transactionList"> 
                    <td >{{transaction.transactionCreatedDate}}</td>
                      <td >{{transaction.transactionStatus}}</td>
                      <td >{{transaction.transactionAmount}}</td>

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
  name: 'Dashboard',
  components: {
    Alert,
    InfoBox,
    ProcessInfoBox
  },
  data (router) {
    return {
      generateRandomNumbers (numbers, max, min) {
        var a = [];
        for (var i = 0; i < numbers; i++) {
          a.push(Math.floor(Math.random() * (max - min + 1)) + max)
        }
        return a
      },
	moduleMap: undefined,
    amount: undefined,
    typeOfTransaction: undefined,
    account: undefined,
    showDismissibleAlert: false,
    accountList: [],
        transactionList:[],
        selected: ""
    }
  },
  computed: {
    coPilotNumbers () {
      return this.generateRandomNumbers(12, 1000000, 10000)
    },
    personalNumbers () {
      return this.generateRandomNumbers(12, 1000000, 10000)
    },
    isMobile () {
      return (window.innerWidth <= 800 && window.innerHeight <= 600)
    }
  },
    methods: {
        changeItem: function changeItem(account, event) {
	    this.selected = event.target.value;
        const accountId = this.selected; 
	api
        .request('post', './rest/accountTransactions',{accountId})
		.then(response => {
			var response = response.data;
			this.transactionList = response;
			
		})
		.catch(error => {
           console.log("error");
		   //alert("Error in Transaction! Please contact administrator");
         })
    }
        
},

    
    
   created() {
	
	this.moduleMap = store.state.moduleMap
	const userId = store.state.user
	api
        .request('post', './rest/userAccounts',{userId})
		.then(response => {
			var response = response.data;
			this.accountList = response;
			
		})
		.catch(error => {
           console.log("error");
		   //alert("Error in Transaction! Please contact administrator");
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
