<template>
  <aside class="side-menu">
    <div class="menu-toggle" @click="toggleMenu">
        <div class="icon"></div>
      </div>
    <ul v-if="showMenu">
      <li><router-link to="/">Inicio</router-link></li>
      <li><router-link to="/perfil">Perfil</router-link></li>
      <li><router-link to="/ajustes">Ajustes</router-link></li>
      <!-- Agrega más enlaces según sea necesario -->
    </ul>
  </aside>
</template>

<script>
export default {
  data() {
    return {
      showMenu: false
    };
  },
  methods: {
    toggleMenu() {
      this.showMenu = !this.showMenu;
    }
  }
}
</script>

<style scoped>
/* Estilos para el menú lateral */
.side-menu {
  width: 200px;
  background-color: #f0f0f0;
}

.side-menu ul {
  list-style: none;
  padding: 0;
}

.side-menu ul li {
  padding: 10px;
}

.side-menu ul li a {
  text-decoration: none;
  color: #333;
}

.side-menu ul li a:hover {
  background-color: #ccc;
}



.side-menu, .navbar {
  margin: 0;
  padding: 0;
}

/* Estilos para el menú lateral */
.side-menu, .navbar {
  display: flex;
  align-items: center;
}
</style>

