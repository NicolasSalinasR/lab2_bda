<template>

  <main>
    <div id="circle">
      <div class="circle"></div>
    </div>

    <img src="../images/mundo.svg" alt="mundo" class="imgFondo">
    <div class="containerIndex">
      <img src="../images/RESQ.svg" alt="logo" class="imgLogo">
      <div class="containerText" @mousemove="updateBlurCiclePosition">
        <div class="containerTextP">
          <p>{{ texto }}</p>
        </div>
      </div>

      <div class="containerButton">
        <div class="login" @click="$router.push(`/login`);">Iniciar Sesión
        </div>

        <div class="logup" @click="$router.push(`/register`);">Registrarse
        </div>
      </div>

    </div>
  </main>
</template>

<script>
export default {
  data() {
    return {
      appName: "Nombre Fabuloso",
      texto: "¡Únete a la fuerza del voluntariado en emergencias! Nuestra plataforma te conecta con oportunidades para marcar la diferencia cuando más se necesita. Coordinar esfuerzos, asignar tareas y mantener el progreso bajo control: todo en un solo lugar. ¡Haz tu parte y ayuda a construir un mundo más resiliente hoy mismo!"
    };
  },
  mounted() {
    setInterval(() => {
      // posicion random en x y y dentro de los limites de la pantalla
      const x = Math.random() * window.innerWidth - 10;
      const y = Math.random() * window.innerHeight - 10;
      // color random entre las opciones de rojo y naranja
      const color = Math.random() > 0.5 ? 'red' : 'orange';
      // crear el circle
      this.createCircles(x, y, color);
    }, 500);
  },
  methods: {
    createCircles(x, y, color) {
      const circle = document.createElement('div');
      circle.classList.add('circle');
      circle.style.left = x + 'px';
      circle.style.top = y + 'px';
      circle.style.backgroundColor = color;
      document.getElementById('circle').appendChild(circle);
      setTimeout(() => {
        circle.remove();
      }, 4000);

    },
    async login() {
      try {
        const res = await this.$axios.post('http://localhost:3000/api/auth/login', {
          email: this.email,
          password: this.password
        });
        console.log(res.data);
        this.$router.push('/dashboard');
      } catch (error) {
        console.log(error);
      }
    }
  }
};
</script>

<style>
body {
  background-color: black;
  color: white;
  margin: 0;

}

.imgFondo {
  width: 100%;
  height: 100vh;
  position: absolute;
  z-index: -10;
  filter: brightness(0.4);
  object-fit: cover;

}

.imgLogo {
  width: 40%;
  height: 15%;
  z-index: 1;
  filter: invert(1);

}

.containerIndex {
  display: flex;
  position: relative;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 100vh;
  font-family: 'Roboto', sans-serif;
}

.containerText {
  width: 70%;
  display: flex;
  text-align: center;
  margin-top: 20px;
  border-bottom: 1px solid white;
  border-radius: 10px;
}

.containerText::after {
  content: "";
  position: absolute;
  width: 70%;
  height: 12vh;
  background-color: rgba(0, 0, 0, 0.5);
  z-index: -1;
  filter: blur(6px);
}

.circle {
  background-color: red;
  width: 10px;
  height: 10px;
  border-radius: 50%;
  position: absolute;
  top: -10%;
  left: -10%;
  transform: translate(-50%, -50%);
  z-index: -1;
  filter: blur(6px);
  transition: all 3s;
}

.containerButton {
  /* grid de una fila con dos columnas */
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 10px;
  margin-top: 20px;
  width: 70%;
  justify-content: center;
  align-items: center;
  text-align: center;
}

/* login en la columna 1 */
.login {
  transition: all 0.5s;
  border: 1px solid white;
  padding: 10px;
  border-radius: 10px;
  text-align: center;
  cursor: pointer;
  order: 1;
}

.login:hover {
  background-color: #13678a;
  border: 1px solid #13678a;
}

/* logup en la columna 2 */
.logup {
  transition: all 0.5s;
  border: 1px solid white;
  padding: 10px;
  border-radius: 10px;
  text-align: center;
  cursor: pointer;
  order: 2;
}

.logup:hover {
  background-color: #13678a;
  border: 1px solid #13678a;
}
</style>