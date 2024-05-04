<template>
    <div class="container">
      <div class="column">
        <div class="card">
          <div class="card-header">
            {{ title1 }}
          </div>
          <div class="card-body" style="max-height: 400px; overflow: auto;">
            <div v-for="(item, index) in description1" :key="index" class="item-container">
                <p>{{ item.text1 }}</p>
                <p>{{ item.text2 }}</p>
                <button @click="verTareas" :style="{ backgroundColor: getButtonColor(item.buttonLabel) }" class="color-rectangle">
                    <span>Ver tareas</span>
                </button>
            </div>
          </div>
        </div>
      </div>

    </div>
  </template>
  
  <script>
  import axios from 'axios';

  export default {
    props: {
      title1: {
        type: String,
        required: true
      },
      description1:{
        type: Array,
        required:true
      },
      title2:{
        type: String,
        required:true
      },
      description2:{
        type: String,
        required:true
      },
      title: String,
      item:Object
    },
    methods:{
        getButtonColor(buttonLabel) {
      // Lógica para determinar el color basado en el texto del botón
      if (buttonLabel === 'En proceso') {
        return '#4b9bf7'; // Color verde si el botón dice "Aceptar"
      } else if (buttonLabel === 'Terminada') {
        return '#4ed12e'; // Color rojo si el botón dice "Rechazar"
      } else {
        return '#d5e388'; // Otro color por defecto
      }
    },
    emitChangeComponent(item) {
      // Emitir un evento personalizado con la información adicional del item
      this.$emit('mostrar-tareas', item);
    },
    // Función para obtener datos del backend utilizando axios
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
    verTareas() {
      // Emitir un evento personalizado para mostrar las tareas
      this.$emit('ver-tareas', this.item);
    }
    /* // Función para obtener el texto del botón basado en el estado
    getButtonLabel(estado) {
      if (estado === 'En proceso') {
        return 'Aceptar';
      } else if (estado === 'Terminada') {
        return 'Rechazar';
      } else {
        return 'Otro';
      }
    } */
    },
    // Llamar a la función para obtener datos del backend cuando el componente se monta
    mounted() {
        this.getNombresEmergencias();
    }
};
  </script>
  
  <style>
  .container {
    display: flex;
    align-items: stretch; /* Asegura que todas las tarjetas tengan la misma altura y se extiendan según sea necesario */
  }
  
  .column {
    flex: 1;
    display: flex;
    justify-content: center;
    padding: 0 10px; /* Añade un poco de espacio entre las columnas */
  }
  
  .card {
    width: 100%;
    max-width: 500px;
    border: 1px solid #ccc;
    border-radius: 8px;
    overflow: hidden;
    margin-bottom: 20px;
    background-color: #fcfcfc;
  }
  
  .card-header {
    background-color: #347355;
    padding: 10px;
    font-weight: bold;
    color:#ffffff
  }
  
  .card-body {
    padding: 10px;
    background-color: #fcfcfc;
  }
  .item-container {
  display: flex;
  align-items: center; /* Centra verticalmente el contenido */
  margin-bottom: 30px; /* Espacio separador entre cada iteración */
}

.item-container p {
  flex: 1; /* El texto ocupará todo el espacio restante */
  margin-right: 10px; /* Añade un poco de espacio entre el texto y el botón */
}

/* .item-container button {
   Estilos para el botón 
} */

.color-rectangle {
  width: 100px; /* Ancho del rectángulo */
  height: 30px; /* Altura del rectángulo */
  border-radius: 5px; /* Bordes redondeados */
  margin-top: 10px; /* Margen superior */
  display: flex; /* Hace que el contenido se centre verticalmente */
  align-items: center; /* Centra verticalmente el contenido */
  justify-content: center; /* Centra horizontalmente el contenido */
}

.color-rectangle span {
  color: white; /* Color del texto */
}
  </style>
  