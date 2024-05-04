<!-- <template>
  <div>
    <div>
      <div class="more" @click="$router.push(/login);" >Ver más</div>
    </div>
    <ul>
      <li>
        <NuxtLink to="/componentep">Holaaa</NuxtLink>
      </li>
    </ul>
  </div>
</template> -->

<template>

  <div>
    <div class="background-container" >
      <div class="content"> 
        <div class="rectangle">
        <p class="app-name">{{ appName }}</p>
        </div>
        <!-- <img src="../images/mundo.svg" alt="mundo"> -->
        <div class="more"  @click="$router.push(`/login`);" 
        >Iniciar Sesión
        </div>

        <div class="more1"  @click="$router.push(`/register`);" 
        >Registrarse
        </div>

      </div>
      
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      appName: "Nombre de la app"
    };
  }
};
</script>

<style>

.content{
  width: 100%;

}
.rectangle {
  background-color: #347355;
  padding: 20px;
  border-radius: 5px;
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: 30px;
  width: 100%; /* Ancho del 100% de la página */
  /* box-sizing: border-box; */ /* Asegura que el padding se incluya en el ancho */
}


.app-name {
  font-size: 24px;
  font-weight: bold;
  color: #FFFFFF; /* Color de texto */
}

.container{
  position: relative;
  width: 100%;
}

.containerIndex {
    /* background-color: white; */
    width: 100%;
    height: 90vh;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    font-family: 'Roboto', sans-serif;
}

.background-container {
  width: 100%;
  background-image: url("../images/mundo.svg"); /* Ruta de tu imagen de fondo */
  background-size: cover; /* Para cubrir todo el contenedor */
  background-position: center; /* Centrar la imagen */
  height: 100vh; /* Altura del contenedor, en este caso, 100% del viewport */
  display: flex;
  justify-content: center; /* Centrar contenido horizontalmente */
  align-items: center; /* Centrar contenido verticalmente */
}

.more {
  position: absolute;
  bottom: 200px; /* Puedes ajustar esta posición según sea necesario */
  background-color: #fff; /* Color de fondo de los botones */
  padding: 10px 20px;
  border-radius: 5px;
  cursor: pointer;
  left: 300px; /* Alinea el botón a la izquierda */
}

.more1 {
  position: absolute;
  bottom: 200px; /* Puedes ajustar esta posición según sea necesario */
  background-color: #fff; /* Color de fondo de los botones */
  padding: 10px 20px;
  border-radius: 5px;
  cursor: pointer;
  right: 300px; /* Alinea el botón a la derecha */
}

</style>