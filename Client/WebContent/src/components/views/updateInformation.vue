<template>
  <div>
    <section class="content">
      <div class="row">
        <div class="col-md-12">
          <div class="box box-info">
            <!-- Input Addons -->
            <div class="box-header with-border">
              <h3 class="box-title">Enter the following: </h3>
            </div>
            <form method="POST" action="">
              <form class="box-body">

                <!-- with characthers -->

                <div class="input-group">
                <span class="input-group-addon">
                  <i class="fa fa-phone" aria-hidden="true"></i>
                </span>
                  <input class="form-control" type="tel" pattern="[0-9]{3}[0-9]{3}[0-9]{4}" placeholder="Phone Number" required>
                </div>

                <div class="input-group">
                <span class="input-group-addon">
                  <i class="fa fa-home" aria-hidden="true"></i>
                </span>
                  <input class="form-control" placeholder="Address 1" type="text" required>
                </div>
                <div class="input-group">
                <span class="input-group-addon">
                  <i class="fa fa-home" aria-hidden="true"></i>
                </span>
                  <input class="form-control" placeholder="Address 2" type="text">
                </div>


                <div class="input-group">
                <span class="input-group-addon">
                  <i class="fa fa-globe" aria-hidden="true"></i>
                </span>
                  <input class="form-control" placeholder="City" type="text" required>
                  <span class="input-group-addon">
                  <i class="fa fa-map-marker" aria-hidden="true"></i>
                </span>
                  <input class="form-control" placeholder="Province" type="text">
                  <span class="input-group-addon">
                  <i class="fa fa-location-arrow" aria-hidden="true"></i>
                </span>
                  <input class="form-control" placeholder="Zip" type="text" required pattern="[0-9]{5}">
                </div>
                <br />

                <button type="submit" class="'btn btn-primary btn-lg '">Submit</button>
                <!-- /input-group -->

                <br />
                <br />
                <br />
              </form>
              <!-- /.box-body -->
            </form>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>
<script>
  require('moment')

  export default {
    name: 'updateInformation',
    components: { },
    computed: {
      datetime () {
        return new Date()
      }
    },
    methods: {
      clearInput (vueModel) {
        vueModel = ''
      }
    }
  }
</script>

<style>
  .datetime-picker input {
    height: 4em !important;
  }
</style>
