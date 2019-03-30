<template>
  <div>
    <section class="content">

      <div class="row">
        <div class="col-md-12">
          <div class="box box-info">
            <div class="box-header with-border">
              <h3 class="box-title">Enter the following: </h3>
            </div>
            <!-- Input Addons -->
            <form method="POST" action="">
            <div class="box-body">

              <!-- calendar group -->
              
              <br />
              <br />

              <!-- with icons from font awesome -->

              <div class="input-group">
                <span class="input-group-addon"><i class="fa fa-file-text"></i></span>
                <input class="form-control" placeholder="Description" required>
              </div>
              <br>
            </div>
              <button type="submit" class="'btn btn-primary btn-lg '">Submit</button>
            </form>
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
  require('moment');

  export default {
    name: 'createAccount',
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

  $(document).ready(function() {
    var pickr = $('.datepicker').pickadate({
      format: 'DD/MM/YYYY',
      selectMonths: true,
      selectYears: 5,
      editable: true
    });
    $('select').material_select();
    $('.datepicker').click(function() {
      pickr.pickadate('open');
    });
  });

</script>

<style>

</style>
