<template>
  <div>
    <section class="content">
      <div class="row">
					<div class="col-md-12">
          <div class="box box-info">
            <!-- Input Addons -->
            <div class="box-header with-border">
              <h3 class="box-title">Update the following Info</h3>
            </div>
            
              <div class="box-body">
				
                <!-- with characthers -->

                <div class="input-group">
                <span class="input-group-addon">
                  <i class="fa fa-phone" aria-hidden="true"></i>
                </span>
                  <input class="form-control" v-model="phone" type="tel" pattern="[0-9]{3}[0-9]{3}[0-9]{4}" placeholder="Phone Number" required>
                </div>

                <div class="input-group">
                <span class="input-group-addon">
                  <i class="fa fa-home" aria-hidden="true"></i>
                </span>
                  <input class="form-control" placeholder="Address 1" type="text" v-model="address1" required>
                </div>
                <div class="input-group">
                <span class="input-group-addon">
                  <i class="fa fa-home" aria-hidden="true"></i>
                </span>
                  <input class="form-control" placeholder="Address 2" v-model="address2" type="text">
                </div>


                <div class="input-group">
                <span class="input-group-addon">
                  <i class="fa fa-globe" aria-hidden="true"></i>
                </span>
                  <input class="form-control" placeholder="City" v-model="city" type="text" required>
                  <span class="input-group-addon">
                  <i class="fa fa-map-marker" aria-hidden="true"></i>
                </span>
                  <input class="form-control" placeholder="Province" v-model="province" type="text">
                  <span class="input-group-addon">
                  <i class="fa fa-location-arrow" aria-hidden="true"></i>
                </span>
                  <input class="form-control" placeholder="Zip" v-model="zip" type="number" required pattern="[0-9]{5}" min="0" step="1" max="999999" oninput="validity.valid||(value='');">
                </div>
                <br />

                <button type="submit" class="btn btn-primary" v-on:click="update()">Update</button>
                <!-- /input-group -->

                <br />
                <br />
                <br />
              </div>
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

  export default {
    name: 'updateInformation',
    data(router){
	return{
		phone: undefined,
		address1: undefined,
		address2: undefined,
		city: undefined,
		province: undefined,
		zip: undefined,
		userDetails: undefined
		}
	},
    methods: {
      clearInput (vueModel) {
        vueModel = ''
      },
	  update() {
		var validateValue = this.validate();
		if(validateValue == false)
		{
			alert("Update atleast one field")
			return;
		}
		if(this.address2 != undefined)
		{
			//this.address2 = undefined
			this.userDetails.tblUserProfile.address2 = this.address2
		}
		if(this.address1 != undefined)
		{
			this.userDetails.tblUserProfile.address1 = this.address1
		}
		if(this.phone != undefined)
		{
			this.userDetails.tblUserProfile.phone = this.phone
		}
		if(this.city != undefined)
		{
			this.userDetails.tblUserProfile.city = this.city
		}
		if(this.province != undefined)
		{
			this.userDetails.tblUserProfile.province = this.province
		}
		if(this.zip != undefined)
		{
			this.userDetails.tblUserProfile.zip = this.zip
		}
		const userDet = JSON.stringify(userDet);
		console.log("userDet::"+JSON.stringify(userDet));
		
		const userId = this.userDetails.userId
		const userObj = {"userId":userId,"tblUserProfile":tblUserProfile}
		
		//const userId = this.userDetails.userId
		
		const phone = this.userDetails.tblUserProfile.phone
		const zip = this.userDetails.tblUserProfile.zip
		const city = this.userDetails.tblUserProfile.city
		const address1 = this.userDetails.tblUserProfile.address1
		const address2 = this.userDetails.tblUserProfile.address2
		const tblUserProfile = {"userId":userId,"phone":phone,"zip":zip,"city":city,"address1":address1,"address2":address2}
		
		
		api.request('post','./rest/updateUser',{userId,tblUserProfile})
		.then(response =>
		{
			this.phone = undefined,
			this.address1 = undefined,
			this.address2 = undefined,
			this.city = undefined,
			this.province = undefined,
			this.zip = undefined,
			alert("User Details updated successfully")
			
		})
		.catch(error =>
		{
			this.phone = undefined,
			this.address1 = undefined,
			this.address2 = undefined,
			this.city = undefined,
			this.province = undefined,
			this.zip = undefined,
			alert("Error in updating User Details")
			
		})
		
		
	  },
	  
	  validate() {
		console.log("this.phone::"+this.phone)
		console.log("this.address1::"+this.address1)
		console.log("this.address2::"+this.address2)
		console.log("this.city::"+this.city)
		console.log("this.province::"+this.province)
		console.log("this.zip::"+this.zip)
		if((this.phone == '' || this.phone == undefined || this.phone.length != 10) && (this.address1 == '' || this.address1 == undefined) && 
		(this.address2 == '' || this.address2 == undefined) && (this.city == '' || this.city == undefined) && (this.province == '' || this.province == undefined) && (this.zip == '' || this.zip == undefined || this.zip.length != 5))
		{
			return false;
		}
		return true;
	  }
    },
	created () {
		const userId = store.state.user
		api.request('post','./rest/getUser',{userId})
		.then(response =>
		{
			this.userDetails = response.data
		})
		.catch(error => {
			alert("Error in retrieving info");
		})
	}
  }
</script>

<style>
  .datetime-picker input {
    height: 4em !important;
  }
</style>
