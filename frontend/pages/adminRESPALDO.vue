<template>
    <div class="main-conteiner">
        <div class="background-container"> 
          <img src="../images/mundo.svg" alt="mundo">
          <Navbar  @change-component="changeComponent" />

          <div id="app">
            <side-menu></side-menu>
            <router-view></router-view>
          </div>
          <!-- Sección "Voluntario" y botón "Crear Emergencia" -->
        <div v-if="!mostrarCard && !mostrarListCard2" style="display: flex;">
            <!-- Columna 1 -->
            <div style="flex: 1;">
                <div class="column" >
                    <div class = "boton">
                        <button class="big-button">Crear Emergencia  +  </button>
                    </div>
                </div>

            </div>

            <!-- Columna 2 -->
            <div style="flex: 1;">
                <Card title1="SIMBOLOGÍA"></Card>
            </div>
        </div>

        <Card v-if= "mostrarCard" @change-component="mostrarComponente" title1="EMERGENCIA" :description1="[
              {text1:'Incencio en Valparaíso' ,buttonLabel:'Ver tareas'},
              {text1:'Incencio en Valparaíso', buttonLabel:'Ver tareas'},
              {text1:'Incencio en Valparaíso' ,buttonLabel:'Ver tareas'},
              {text1:'Incencio en Valparaíso' ,buttonLabel:'Ver tareas'},
              {text1:'Incencio en Valparaíso' ,buttonLabel:'Ver tareas'},
              {text1:'Incencio en Valparaíso' ,buttonLabel:' Ver tareas'}]"  />
        <ListCard2 v-if="mostrarTareas" @volver="mostrarComponente('Card')" />
        <ListCard2 v-if="mostrarListCard2" 
        :description1="[
              {text1:'Incencio - Valparaíso',buttonLabel:'Ver Voluntarios'},
              {text1:'Terremoto - Santiago', buttonLabel:'Ver Voluntarios'},
              {text1:'Terremoto - Región del Maule',buttonLabel:'Ver Voluntarios'},
              {text1:'Erupción volcánica - Volcán Villarrica, Región de la Araucanía',buttonLabel:'Ver Voluntarios'},
              {text1:'Inundación - Región de Coquimbo',buttonLabel:'Ver Voluntarios'},
              {text1:'Incendio forestal - Región del Biobío ' ,buttonLabel:' Ver Voluntarios'},
              {text1:'Incendio forestal - Región del Biobío ' ,buttonLabel:' Ver Voluntarios'},
              {text1:'Incendio forestal - Región del Biobío ' ,buttonLabel:' Ver Voluntarios'}]" 
              title1 = "EMERGENCIA" title2 = "Voluntarios"/>

          <!-- <ListCard v-if="mostrarListCard"
          title1 = "Tareas de Emergencias inscritas"
          :description1="[
              {text1:'Primeros Auxilios',text2:'Incencio en Valparaíso' ,buttonLabel:'En proceso'},
              {text1:'Recoger escombros',text2:'Incendio en Valparaíso', buttonLabel:'En proceso'},
              {text1:'Primeros Auxilios',text2:'Incencio en Valparaíso' ,buttonLabel:'Terminada'},
              {text1:'Primeros Auxilios',text2:'Incencio en Valparaíso' ,buttonLabel:'Terminada'},
              {text1:'Primeros Auxilios',text2:'Incencio en Valparaíso' ,buttonLabel:'Terminada'},
              {text1:'Primeros Auxilios',text2:'Incencio en Valparaíso' ,buttonLabel:'En pausa'}]"
          title2= "TAREAS"
          :description2="[
              {text1:'Primeros Auxilios',text2:'Incencio en Valparaíso' ,buttonLabel:'Postular'},
              {text1:'Recoger escombros',text2:'Incendio en Valparaíso', buttonLabel:'Aceptar'},
              {text1:'Primeros Auxilios',text2:'Incencio en Valparaíso' ,buttonLabel:'Aceptar'},
              {text1:'Primeros Auxilios',text2:'Incencio en Valparaíso' ,buttonLabel:'Aceptar'},
              {text1:'Primeros Auxilios',text2:'Incencio en Valparaíso' ,buttonLabel:'Aceptar'},
              {text1:'Primeros Auxilios',text2:'Incencio en Valparaíso' ,buttonLabel:'Aceptar'},
              {text1:'Primeros Auxilios',text2:'Incencio en Valparaíso' ,buttonLabel:'Aceptar'},
              {text1:'Primeros Auxilios',text2:'Incencio en Valparaíso' ,buttonLabel:'Aceptar'},
              {text1:'Primeros Auxilios',text2:'Incencio en Valparaíso' ,buttonLabel:'Aceptar'}]">
          </ListCard>
          <ListCard2 v-else /> -->
          <main></main>
  
          
      </div>
    </div>
    <Footer></Footer>
  </template>
  
  <script>
  //import axios from "axios";
  
  import SideMenu from '@/components/SideMenu.vue';
  import ListCard from '@/components/ListCard.vue';
  import ListCard2 from '@/components/ListCard2.vue';
    import Card from '@/components/Card.vue';
  import NavbarVoluntario from '@/components/NavbarVoluntario.vue';
  import Footer  from '@/components/Footer.vue';
  
  export default {
  
    components: {
        Card,
      ListCard,
      ListCard2
    },
    data() {
      return {
        
        mostrarListCard: false ,
        mostrarListCard2: false,
        mostrarCard: false
      };
    },
    methods:{
      changeComponent(component) {
      // Establece el estado correspondiente a true y los demás a false
      this.mostrarListCard = component === 'ListCard1';
      this.mostrarListCard2 = component === 'ListCard2';
      this.mostrarCard = component === 'Card';
    },
    reset() {
      // Restablece las variables de estado a sus valores originales
      this.mostrarListCard = false; // o cualquier valor predeterminado que necesites
      this.mostrarListCard2 = false;
      this.mostrarCard = false;
    },
    mostrarComponente(componente) {
      // Ocultar todos los componentes
      this.mostrarCard = false;
      this.mostrarTareas = false;
      // Mostrar el componente deseado
      if (componente === 'ListCard2') {
        this.mostrarTareas = true;
      } else if (componente === 'Card') {
        this.mostrarCard = true;
      }
      // Mostrar otros componentes según sea necesario
    },
    mostrarComponente(buttonLabel) {
      // Aquí puedes cambiar el componente según el botón presionado
      if (buttonLabel === 'Ver tareas') {
        // Cambiar al componente ListCard2
        this.mostrarCard = false;
        this.mostrarListCard2 = true;
      }
    }
    }
    /* mounted() {
      // Realiza la solicitud HTTP al backend para obtener los datos
      axios.get("URL_DEL_BACKEND")
        .then(response => {
          // Asigna los datos recibidos a la variable backendData
          this.backendData = response.data;
        })
        .catch(error => {
          console.error("Error al obtener los datos del backend:", error);
        });
    } */
  };
  
  
  </script>
  
  <style>
  
  .main-container {
    position: relative;
    overflow: hidden;
  }
  
  .background-container {
    position: relative;
    /* overflow: hidden; */
    
  }
  body {
    margin: 0;
    color: black;
    background-color: rgb(255, 255, 255);
    font-family: 'Roboto', sans-serif;
  }
  
  
  
  img {
    width: 100%;
    height: 100vh;
    position: absolute;
    z-index: -5;
    object-fit: cover;
    filter: brightness(0.25) invert(1);
    background-repeat: repeat-y;
  }
  
  .footer {
    position: absolute;
    bottom: 0;
    width: 100%;
  }
  .big-button {
  font-size: 24px; /* Tamaño del texto */
  padding: 20px 40px; /* Espacio interno del botón */
  border: none; /* Sin borde */
  background-color: #347355; /* Color de fondo del botón */
  color: white; /* Color del texto */
  border-radius: 10px; /* Borde redondeado */
  cursor: pointer; /* Cursor al pasar el ratón */
  transition: background-color 0.3s; /* Transición suave del color de fondo */
}

/* Estilos al pasar el ratón sobre el botón */
.big-button:hover {
  background-color: #000000; /* Cambio de color de fondo */
}

.column {
    flex: 1;
    display: flex;
    justify-content: center;
    padding: 0 10px; /* Añade un poco de espacio entre las columnas */
    }

    .boton{
        display: flex;
        justify-content: center;
        padding: 0 10px; /* Añade un poco de espacio entre las columnas */
    margin-top: 75px;}

  </style>