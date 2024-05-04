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

       <!--  //Lo que se muestra con Ranking voluntario x tarea, primero 1 tarjeta con eme y luego 2 con sus tareas y voluntarios -->
        <Card v-if= "mostrarCard" v-for="(item ,index) in items"  
            :key="index" 
            :title="item.title"
            :item="item"
            @ver-tareas="mostrarListCard2"
            />

         <!-- //Lo que se muestra con Ranking voluntario x emergencia, 2 tarjetas con emergencias y lo vol que + han participado -->
        <!--<ListCard2 v-if="mostrarTareas" 
            :datos ="datosListCard2"
            @volver="mostrarComponente('Card')" 
            /> -->

        <!-- //Lo que se muestra con Ranking voluntario x emergencia, 2 tarjetas con emergencias y lo vol que + han participado -->
        <ListCard2 v-if="mostrarListCard2" 
        :title1 ="EMERGENCIAS"
        :title2:="VOLUNTARIOS"
        :description1="datosListCard2.description1"
        :description2= "datosListCard2.description2"
        />

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
  import axios from 'axios';
  
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
        mostrarCard: false,
        respondeData: null,
        listadeEmergencias:[],
        items:[],
        datosListCard2:[]
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
    mostrarComponente(item) {
        // Aquí puedes cambiar el componente según el botón presionado
        if (item.buttonLabel === 'Ver Tareas') {
            // Cambiar al componente ListCard2
            this.mostrarCard = false;
            this.mostrarListCard2 = true;
        }
    },
    created() {
        // Obtener el id_emergencia del item actual
        const id_emergencia = this.item.id_emergencia; // Asegúrate de tener acceso al item actual

        // Hacer una solicitud al backend para obtener las tareas correspondientes al id_emergencia
        axios.get(`URL_DEL_BACKEND/tareas?id_emergencia=${id_emergencia}`)
        .then(response => {
            // Asignar los datos recibidos a la lista de tareas
            this.listadeTareas = response.data.map(item => ({
                text1: item.nombre_tarea,
                buttonLabel: 'Ver Voluntarios'
            }));
        })
        .catch(error => {
            console.error('Error al obtener los datos:', error);
        });
    },
    // Función para obtener lista de nombres de las emergencias, datos del backend utilizando axios
    getNombresEmergencias() {
      axios.get('URL_DEL_BACKEND')
        .then(response => {
          // Asignar los datos recibidos a la propiedad description1-------------------------
          this.description1 = response.data.map(item =>({
            text1: item.tipo_emergencia,
           // buttonLabel:this.getButtonLabel(item.estado)
          }));

        })
        .catch(error => {
          console.error('Error al obtener los datos del backend:', error);
        });
    },
    // Llamar a la función para obtener datos del backend cuando el componente se monta
    mounted() {
        this.getNombresEmergencias();
    },
    mostrarComponente(buttonLabel) {
      // Aquí puedes cambiar el componente según el botón presionado
      if (buttonLabel === 'Ver tareas') {
        // Cambiar al componente ListCard2
        this.mostrarCard = false;
        this.mostrarListCard2 = true;
      }
    },
    //Ver si le cambio el nombreeee aa mostrar voluntarios por  tareas de tal emergencias 
    mostrarListCard2(item) {
        // Lógica para obtener los datos del backend
        axios.get(`URL_DEL_BACKEND/emergencia/${item.id_emergencia}`)
            .then(response => {
            const emergencia = response.data;
            const title1 = emergencia.tipo_emergencia; // Obtener el tipo_emergencia de la emergencia
            const title2 = "Voluntarios"; // Título predeterminado para title2

            // Obtener la lista de nombre_tarea de la tabla tarea para el id_emergencia correspondiente
            axios.get(`URL_DEL_BACKEND/tarea/${item.id_emergencia}`)
                .then(response => {
                const tareas = response.data.map(tarea => ({nombre: tarea.nombre_tarea}));
                const description1 = tareas;// Cada nombre de tarea es un objeto individual en la lista

                // Asignar los valores calculados a las propiedades del componente ListCard2
                this.title1 = title1;
                this.title2 = title2;
                this.description1 = description1;
                this.description2 = ""; // Luego se puede sobrescribir

                // Mostrar el componente ListCard2
                this.mostrarListCard1 = false;
                this.mostrarCard = false;
                this.mostrarListCard2 = true;
                this.items = item;
                })
                .catch(error => {
                console.error('Error al obtener las tareas:', error);
                });
            })
            .catch(error => {
            console.error('Error al obtener la emergencia:', error);
            });
        },
        //Función que toma valores del backend y los asigna a las propiedades del componente datosListCard2
        mostrarVoluntariosXEmergencia(){
            axios.get('URL_DEL_BACKEND')
            .then(response => {
                const emergencias = response.data;
                const title1 = "Emergencias"; // Título predeterminado para title1
                const title2 = "Voluntarios"; // Título predeterminado para title2
                const description1 = emergencias.map(emergencia => ({nombre: emergencia.tipo_emergencia}));
                //CAMBIAR CUANDO SE TENGA LISTO EN EL BACK DÓNDE SE VA A GUARDAR, se debe mostar rut, nombre, y canti de tareas en la que
                const description2 = emergencias.map(emergencia => ({nombre: emergencia.voluntarios}));

                // Asignar los valores calculados a las propiedades del componente ListCard2
                this.title1 = title1;
                this.title2 = title2;
                this.description1 = description1;
                this.description2 = description2;

                // Mostrar el componente ListCard2
                this.mostrarListCard1 = false;
                this.mostrarCard = false;
                this.mostrarListCard2 = true;
            })
            .catch(error => {
                console.error('Error al obtener los datos:', error);
            });

        },
    fetchData() {
      axios.get('URL_DEL_BACKEND')
        .then(response => {
          this.responseData = response.data;
        })
        .catch(error => {
          console.error('Error al obtener los datos:', error);
        });
    }
  },

  mounted() {
    this.fetchData();
  }
    };
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