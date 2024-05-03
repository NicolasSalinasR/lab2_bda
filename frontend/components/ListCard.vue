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
                <div :style="{ backgroundColor: getButtonColor(item.buttonLabel) }" class="color-rectangle">
                    <span>{{ item.buttonLabel }}</span>
                </div>
            </div>
          </div>
        </div>
      </div>
      <div class="column">
        <div class="card">
          <div class="card-header">
            {{ title2 }}
          </div>
          <div class="card-body" style="max-height: 400px; overflow: auto;">
            <div v-for="(item, index) in description2" :key="index" class="item-container">
                <p>{{ item.text1 }}</p>
                <p>{{ item.text2 }}</p>
                <button>{{ item.buttonLabel }}</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <script>
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
      }
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
    }}
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
  