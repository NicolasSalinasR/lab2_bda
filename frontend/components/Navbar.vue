<template>
  <nav>
    <div class="logo">
      <img src="../images/RESQ.svg" alt="loga">
    </div>
    <div class="menu">
      <!-- Menú principal -->
      <ul>
        <li @click="changeComponent('')">Inicio</li>
        <li><a href="#">Servicios</a></li>
        <!-- Elemento con menú desplegable -->
        <li class="dropdown" @mouseover="showDropdown" @mouseleave="hideDropdown">
          <a href="#">Estadísticas <span>&#9662;</span></a>
          <!-- Submenú desplegable -->
          <ul class="dropdown-content" v-show="isDropdownVisible">
            <li @click="changeComponent('Card')">Ranking Voluntario por Tarea</li>
            <li @click="changeComponent('ListCard2')">Ranking Voluntario por Emergencia</li>

          </ul>
        </li>
        <li><a href="#">Contacto</a></li>
      </ul>
    </div>
  </nav>
</template>

<script>
export default {
  data() {
    return {
      isDropdownVisible: false
    };
  },
  methods: {
    showDropdown() {
      this.isDropdownVisible = true;
    },
    hideDropdown() {
      this.isDropdownVisible = false;
    },
    toggleComponent() {
      // Cambia el estado para mostrar el componente contrario
      this.mostrarComponenteA = !this.mostrarComponenteA;
    },
    changeComponent(value) {
      this.$emit('change-component', value);
    },
    reset() {
      // Restablece las variables de estado a sus valores originales
      this.mostrarListCard = false; // o cualquier valor predeterminado que necesites
      this.mostrarListCard2 = false;
      this.mostrarCard = false;
    }
  }
};
</script>

<style>
nav {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0 5%;
  background-color: rgba(255, 255, 255, 0.7);
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
}

.logo h1 {
  margin: 0;
  font-size: 20px;
  font-weight: bold;
  color: #333;
}

.menu ul {
  display: flex;
  list-style: none;
}

.menu ul li {
  margin-right: 20px;
}

.menu ul li a {
  text-decoration: none;
  color: #333;
}

/* Estilos para el menú desplegable */
.dropdown-content {
  display: none;
  position: absolute;
  background-color: #f9f9f9;
  min-width: 160px;
  box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
  z-index: 1;
}

.dropdown-content li {
  padding: 10px;
}

.dropdown:hover .dropdown-content {
  display: block;
}
</style>