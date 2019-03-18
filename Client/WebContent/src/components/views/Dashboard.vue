<template>
  <!-- Main content -->
  <section class="content">
    <!-- GitHub hint -->
    <div class="row">


      <!-- Info boxes -->
      <div class="col-md-4 col-sm-12 col-xs-12">
        <info-box color-class="bg-yellow"
                  :icon-classes="['ion', 'ion-cash']"
                  text="Balance"
                  number="$236.61"></info-box>
      </div>
      <!-- /.col -->
      <div class="col-md-4 col-sm-6 col-xs-12">
        <info-box color-class="bg-green"
                  :icon-classes="['ion', 'ion-card']"
                  text="Deposits"
                  number="+$64.00"></info-box>
      </div>
      <!-- /.col -->

      <div class="col-md-4 col-sm-6 col-xs-12">
        <info-box color-class="bg-red"
                  :icon-classes="['ion', 'ion-card']"
                  text="Withdrawals"
                  number="-$588.75"></info-box>
      </div>
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
                  <table aria-describedby="example1_info" role="grid" id="example1" class="table table-bordered table-striped dataTable">
                    <thead>
                    <tr role="row">
                      <th aria-label="Rendering engine: activate to sort column descending" aria-sort="ascending" style="width: 167px;" colspan="1" rowspan="1" aria-controls="example1" tabindex="0" class="sorting_asc">Date</th>
                      <th aria-label="Platform(s): activate to sort column ascending" style="width: 182px;" colspan="1" rowspan="1" aria-controls="example1" tabindex="0" class="sorting">Description</th>
                      <th aria-label="Engine version: activate to sort column ascending" style="width: 142px;" colspan="1" rowspan="1" aria-controls="example1" tabindex="0" class="sorting">Amount ($)</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr class="even" role="row">
                      <td class="sorting_1">03/17/2019</td>
                      <td>ABC</td>
                      <td>54</td>
                    </tr>
                    <tr class="odd" role="row">
                      <td class="sorting_1">03/18/2019</td>
                      <td>ABC</td>
                      <td>100</td>
                    </tr>
                    <tr class="even" role="row">
                      <td class="sorting_1">03/19/2019</td>
                      <td>ABC</td>
                      <td>18</td>
                    </tr>
                    <tr class="odd" role="row">
                      <td class="sorting_1">03/20/2019</td>
                      <td>ABC</td>
                      <td>180</td>
                    </tr>
                    <tr class="even" role="row">
                      <td class="sorting_1">03/21/2019</td>
                      <td>ABC</td>
                      <td>19</td>
                    </tr>
                    <tr class="odd" role="row">
                      <td class="sorting_1">03/22/2019</td>
                      <td>ABC</td>
                      <td>1</td>
                    </tr>
                    <tr class="even" role="row">
                      <td class="sorting_1">03/23/2019</td>
                      <td>ABC</td>
                      <td>1.8</td>
                    </tr>
                    <tr class="odd" role="row">
                      <td class="sorting_1">03/24/2019</td>
                      <td>ABC</td>
                      <td>17</td>
                    </tr>
                    <tr class="even" role="row">
                      <td class="sorting_1">03/25/2019</td>
                      <td>ABC</td>
                      <td>197</td>
                    </tr>
                    <tr class="odd" role="row">
                      <td class="sorting_1">03/26/2019</td>
                      <td>ABC</td>
                      <td>8</td>
                    </tr>
                    <tr class="even" role="row">
                      <td class="sorting_1">03/27/2019</td>
                      <td>ABC</td>
                      <td>10</td>
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
  name: 'Dashboard',
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
      }
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
