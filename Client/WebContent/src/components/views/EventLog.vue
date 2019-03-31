<template>
  <!-- Main content -->
  <section class="content">
    <!-- GitHub hint -->

    <!-- /.row -->

    <div class="row center-block">
      <h2>Events: </h2>
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
                  <table class="table table-bordered table-striped">
                    <thead>
                    <tr role="row">
                      <th style="width: 167px;" colspan="1" rowspan="1" tabindex="0" >Event Description</th>
                      <th style="width: 182px;" colspan="1" rowspan="1" tabindex="0" >Event Date</th>
                    </tr>
                    </thead>
                    <tbody>
                     <tr class="even" role="row" > 
                         <td class="sorting_1"></td>
                         <td class="sorting_1"></td>

                    </tr>
                    <tr class="odd" role="row" v-for="event in eventList"> 
                      <td>{{event.eventName}}</td>
                      <td>{{event.createdDate}}</td>

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
  import $ from 'jquery'
  // Require needed datatables modules
  require('datatables.net')
  require('datatables.net-bs')

  export default {
    name: 'eventLog',
    data(router) {
      return {
        eventList: undefined
      }
    },
 
    created() {
    console.log("enter created()");
	const userId = store.state.user;
	api
        .request('post', './rest/searchEvent',{userId})
		.then(response => {
			var response = response.data;
			this.eventList = response;
			//$('#example1').DataTable()
			//console.log("on load::"+JSON.stringify(response));
		})
		.catch(error => {
           console.log("error");
		   alert("Error in retrieving Event Log");
         })
	
  },
  mounted () {
    this.$nextTick(() => {
      //var ctx = document.getElementById('trafficBar').getContext('2d')
      var config = {
        type: 'line',
        data: {
          labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'],
          datasets: [{
            label: 'CoPilot',
            fill: false,
            borderColor: '#284184',
            pointBackgroundColor: '#284184',
            backgroundColor: 'rgba(0, 0, 0, 0)',
            data: this.coPilotNumbers
          }, {
            label: 'Personal Site',
            borderColor: '#4BC0C0',
            pointBackgroundColor: '#4BC0C0',
            backgroundColor: 'rgba(0, 0, 0, 0)',
            data: this.personalNumbers
          }]
        },
        options: {
          responsive: true,
          maintainAspectRatio: !this.isMobile,
          legend: {
            position: 'bottom',
            display: true
          },
          tooltips: {
            mode: 'label',
            xPadding: 10,
            yPadding: 10,
            bodySpacing: 10
          }
        }
      };

      

      var pieChartCanvas = document.getElementById('languagePie').getContext('2d')
      var pieConfig = {
        type: 'pie',
        data: {
          labels: ['HTML', 'JavaScript', 'CSS'],
          datasets: [{
            data: [56.6, 37.7, 4.1],
            backgroundColor: ['#00a65a', '#f39c12', '#00c0ef'],
            hoverBackgroundColor: ['#00a65a', '#f39c12', '#00c0ef']
          }]
        },
        options: {
          responsive: true,
          maintainAspectRatio: !this.isMobile,
          legend: {
            position: 'bottom',
            display: true
          }
        }
      };

      
    });
    
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
