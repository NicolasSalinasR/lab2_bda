<template>
  <nav>
    <div class="logo">
      <img src="../images/RESQ.svg" alt="logo" class="logoNav">
    </div>
    <div class="menu">
      <ul>
        <li><a href="#">Inicio</a></li>
        <li><a href="#">Servicios</a></li>
        <!-- Elemento con menú desplegable -->
        <li><a href="#">Contacto</a></li>
        <li><button @click="logout" class="salir">Salir</button></li>
      </ul>
    </div>


  </nav>
</template>

<script>
export default {
  methods: {
    logout() {
      localStorage.removeItem('token');
      localStorage.removeItem('id');
      localStorage.removeItem('nombre');
      this.$router.push('/');
    }
  }
}
</script>

<style>
nav {
  display: grid;
  width: 100%;
  grid-template-columns: 1fr 1fr;

  justify-content: space-between;
  align-items: center;
  padding: 0;
  background-color: rgba(255, 255, 255, 0.7);
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
  height: 8vh;
}

.logo {
  width: 18vh;
  height: 2vh;
  display: flex;
  margin-left: 40px;
  justify-content: center;
  align-items: center;
  filter: invert(1);
}

.logoNav {
  object-fit: fill;
}



.menu li {
  list-style: none;
  display: inline;
  margin: 0 0px;
  padding: 0;
  width: 100%;
  text-align: center;
}

.menu a {
  text-decoration: none;
  color: black;
  font-size: 17px;
  font-family: 'Roboto', sans-serif;
}

.menu button {
  background-color: white;
  border: none;
  color: red;
  cursor: pointer;
  font-size: 17px;
  font-family: 'Roboto', sans-serif;
}

.menu ul {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr 1fr;
  height: 100%;
  gap: 20px;
  justify-content: center;
  align-items: center;
  margin-right: 40px;
  margin-left: 60%;
  padding: 0;
  height: 100%;
}
</style>