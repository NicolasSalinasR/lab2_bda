<template>
  <footer class="footer">

    <div class="column1">
      <h3>Información de contacto</h3>
      <div class="info">
        <div class="dire"><img src="../images/chimenea-de-la-casa.svg" alt="logo" class="direccion"></div>
        <div class="tele"><img src="../images/voltear-el-telefono.svg" alt="logo" class="telefono"></div>
        <div class="cor"><img src="../images/sobre.svg" alt="logo" class="correo"></div>
      </div>

    </div>
    <div class="column2">
      <h3>Enlaces útiles</h3>
      <ul>
        <li><a @click="$router.push('/')">Inicio</a></li>
        <li><a href="#">Servicios</a></li>
        <li><a href="#">Nosotros</a></li>
        <li><a href="#">Contacto</a></li>
      </ul>
    </div>
    <div class="column3">
      <h3>Integrantes</h3>
      <div class="integrantes">
        <p>P</p>
        <p>N</p>
        <p>N</p>
        <p>N</p>
        <p>M</p>
      </div>

    </div>
  </footer>
</template>

<style scoped>
.footer {
  top: 100%;
  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
  gap: 20px;
  background-color: #012030;
  color: #fff;
  width: 100%;
  height: 120px;
  padding: 2px 0;

}

.column1 {
  display: flex;
  flex-direction: column;
  justify-content: left;
  align-items: left;
  margin-left: 30px;
}

.info {
  display: flex;
  justify-content: space-between;
  align-items: center;

}

.column1 img {
  transition: all 0.5s;
  display: flex;
  width: 30px;
  height: 30px;
  position: relative;
  align-items: center;
  justify-content: center;
  z-index: 1;
  margin-left: 40px;
  margin-right: 40px;
  filter: invert(0);


}

.dire {
  transition: all 0.5s;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: white;
  width: 50px;
  height: 50px;
  margin-left: 50px;
  border-radius: 50%;
}

.dire:hover {
  background-color: #45C4B0;
  width: 260px;
  border-radius: 10px;
  color: black;
  transition: all 0.5s;
}

.dire:hover::after {
  content: "Calle Principal 123, Ciudad";
  color: black;
  transition: all 1s;
}

.tele {
  transition: all 0.5s;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: white;
  width: 50px;
  height: 50px;
  border-radius: 50%;
}

.tele:hover {
  background-color: #45C4B0;
  width: 260px;
  border-radius: 10px;
  color: black;
  transition: all 0.5s;
}

.tele:hover::after {
  content: "123-456-789";
  color: black;
  transition: all 1s;
}

.cor {
  transition: all 0.5s;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: white;
  width: 50px;
  height: 50px;
  margin-right: 50px;
  border-radius: 50%;
}

.cor:hover {
  background-color: #45C4B0;
  width: 260px;
  border-radius: 10px;
  color: black;
  transition: all 0.5s;
}

.cor:hover::after {
  content: "info@example.com";
  color: black;
  transition: all 1s;
}


.column1 h3 {
  display: flex;
  justify-content: center;
}


.column2 {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.column2 ul {
  display: flex;
  flex-direction: row;
  padding: 0;
  margin: 0;
  gap: 20px;
  margin-top: 10px;
}

.column2 li {
  display: flex;
  justify-content: center;
  align-items: center;
  list-style: none;
  text-decoration: none;
  color: black;
  padding: 0;
  font-size: 18px;
  background-color: white;
  border-radius: 5px;
  font-family: 'Roboto', sans-serif;
  width: 90px;
  height: 22px;
}

.column2 a {
  text-decoration: none;
  color: black;
}

.column2 li:hover {
  background-color: #45C4B0;
  color: black;
  transition: all 0.5s;
}




.column3 {
  display: flex;
  flex-direction: column;
  align-items: right;
  margin-right: 30px;
}

.column3 p {
  display: flex;
  justify-content: right;
}

.column3 h3 {
  display: flex;
  justify-content: center;
  margin-bottom: 0;
}

.integrantes {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  gap: 20px;
}

.integrantes p {
  content: "";
  transition: all 0.5s;
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 18px;
  width: 30px;
  height: 30px;
  font-weight: bold;
  background-color: white;
  color: black;
  border-radius: 50%;
}

.integrantes p:hover {
  transform: all 0.5s;
  background-color: #45C4B0;
  color: black;
  width: 220px;
  border-radius: 10px;
}

/* cambiar el contenido del primero a prueba cuando se haga hover con un 0.5s de retraso*/
.integrantes p:hover:nth-child(1)::after {
  content: "ablo Macuada Rozas";
  color: black;
  transition: all 0.5s;
}

.integrantes p:hover:nth-child(2)::after {
  content: "icolás Muñoz";
  color: black;
  transition: all 0.5s;
}

.integrantes p:hover:nth-child(3)::after {
  content: "icolás Saavedra";
  color: black;
  transition: all 0.5s;
}

.integrantes p:hover:nth-child(4)::after {
  content: "icolás Salinas";
  color: black;
  transition: all 0.5s;
}

.integrantes p:hover:nth-child(5)::after {
  content: "acarena Soto Hererra";
  color: black;
  transition: all 0.5s;
}
</style>