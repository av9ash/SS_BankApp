<template>
  <!-- Main content -->
  <section class="content">
    <!-- GitHub hint -->

    <!-- /.row -->

    <div class="row center-block">
      <h2>Appointments: </h2>
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
                  <table  role="grid"  class="table table-bordered table-striped dataTable">
                    <thead>
                    <tr role="row">
                      <th style="width: 167px;" colspan="1" rowspan="1" tabindex="0" >Date</th>
                      <th style="width: 182px;" colspan="1" rowspan="1" tabindex="0" >Description</th>
                    </tr>
                    </thead>
                    <tbody>
                     <tr class="even" role="row" > 
                         <td class="sorting_1">   </td>
                         <td class="sorting_1">   </td>

                    </tr>
                    <tr class="odd" role="row" v-for="appointment in appointment_list"> 
                      <td class="sorting_1">{{appointment.dateCreated}}</td>
                      <td >{{appointment.descript}}</td>

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

  export default {
    name: 'showAppointment',
    components: {
      Alert,
      InfoBox,
      ProcessInfoBox
    },
    data () {
      return {
        generateRandomNumbers (numbers, max, min) {
          var a = [];
          for (var i = 0; i < numbers; i++) {
            a.push(Math.floor(Math.random() * (max - min + 1)) + max)
          }
          return a
        },
          appointment_list:[]
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
    created() {
    console.log("enter created()");
	const userId = store.state.user;
	api
        .request('post', './rest/searchAppointments',{userId})
		.then(response => {
			var response = response.data;
			this.appointment_list = response;
			console.log("on load::"+JSON.stringify(response));
		})
		.catch(error => {
           console.log("error");
		   //alert("Error in Transaction! Please contact administrator");
         })
  },
    mounted () {
      this.$nextTick(() => {
        var ctx = document.getElementById('trafficBar').getContext('2d')
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

        new Chart(ctx, config) // eslint-disable-line no-new

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

        new Chart(pieChartCanvas, pieConfig) // eslint-disable-line no-new
      });

      $('#example1').DataTable()
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
