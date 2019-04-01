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
            
            <div class="box-body">

              <!-- calendar group -->
              
              <br />
              <br />

              <!-- with icons from font awesome -->

              <div class="input-group">
                <span class="input-group-addon"><i class="fa fa-file-text"></i></span>
                <input class="form-control" placeholder="Description" required id="description_value" v-model="descript"/>
              </div>
              <br>
            </div>
              <button type="submit" class="btn btn-primary" v-on:click="submit()">Submit</button>

            
            
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
	data(router) {
	descript: undefined
	},
    components: { },
    computed: {
      datetime () {
        return new Date()
      }
    },
    methods: {
      clearInput (vueModel) {
        vueModel = ''
      },
    
      submit()
	{
		if(this.descript == undefined || this.descript == '')
		{
			alert("Enter a description");
			return;
		}
        console.log("enter submit()");
        const user_id = store.state.user;
        console.log("user_Id", user_id);
        const obj = {"userId":user_id};
        const tblUserByAppointmentUserId = obj;
        //const descript = document.getElementById("description_value").value;        
        const descript = this.descript;
        
        api
        .request('post','./rest/createAppointment',{tblUserByAppointmentUserId, descript})
        .then(response => {
            var response = response.data;
            console.log("response");
            if(response === 'success')
			{
				console.log("good");
                this.descript = undefined
				alert("Appointment Created!");
			}
			else if(response === "FAIL")
			{
				alert("Unable to create appointment");
			}
            
        })
        .catch(error => {
           console.log("error");
		   alert("Error in Appointment creation! Please contact administrator");
         })
        
	}
    }
  }

</script>

<style>

</style>
