<template>
  <footer class="main-footer">
    <strong><center>Copyright &copy; Sun Devil Safe Bank. All rights reserved.</center></strong>
  </footer>
</template>

<script>
export default {
  name: 'DashFooter',
  data: function () {
    return {
      year: new Date().getFullYear()
    }
  }
}
</script>
