<template>
    <div class="container-admin">
        <nav class="nav-back">
            <img src="../images/menu-hamburguesa.svg" alt="" @click="toggleMenu">
            <h1>RES-Q</h1>
            <h3>Vista Coordinador</h3>
        </nav>

        <aside>
            <img src="../images/x.svg" alt="" @click="toggleMenuBack">
        </aside>

        <section class="aside-section">

        </section>

        <main>
            <section class="section1">
                <div id="map" style="height: 100%; width: 100%;"></div>
            </section>
            <section class="section2">
                <button>Crear Emergencia</button>
                <!-- TODO: en la segunda fila debe ir la leyenda del mapa con los puntos y su significado, debe cambiar a la lista de las emergencias cuando se seleccione en el mapa -->
                <!-- lista de las tareas -->
                <div class="containerListTareasRegiones">
                    <ul>
                        <li v-for="tarea in tareas" :key="tarea.id">
                            <span class="icon-container">
                                <img src="../images/marcador.svg" alt="Marcador" />
                            </span>
                            {{ tarea[3] }}
                        </li>
                    </ul>
                </div>
            </section>
        </main>
    </div>


</template>

<script>
import axios from 'axios';

export default {
    data() {
        return {
            map: null,
            tareas: [],
            markers: [],
        };
    },
    mounted() {
        this.initMap();
    },
    methods: {
        initMap() {
            const script = document.createElement('script');
            script.src = `https://maps.googleapis.com/maps/api/js?key=AIzaSyDd1yMKvX4PyaxaVtyauISsGrMvxYi6CgQ&libraries=places`;
            script.async = true;
            script.onload = () => {
                const map = new google.maps.Map(document.getElementById('map'), {
                    center: { lat: -33.447308, lng: -70.664213 },
                    zoom: 10,
                });

                this.map = map;

                axios.get('http://localhost:8080/regiones/all',
                    { headers: { 'Authorization': `Bearer eyJhbGciOiJIUzI1NiJ9.eyJudW1lcm9Eb2N1bWVudG9Wb2x1bnRhcmlvIjoiMjA4NDczODctNSIsIm5vbWJyZVZvbHVudGFyaW8iOiJQYWJsbyIsInN1YiI6InBhYmxvQGdtYWlsLmNvbSIsImlhdCI6MTcxNjgzMjY1MCwiZXhwIjoxNzE3MjY0NjUwfQ.eDYFXSKcqxxFFD481vS3yGB0rWl3aqbLXOsiWM4wWHY` } })
                    .then(response => {
                        response.data.forEach(region => {
                            const polygons = this.wktToLatLng(region.geometria);
                            polygons.forEach(polygon => {
                                var poligono = new google.maps.Polygon({
                                    paths: polygon,
                                    strokeColor: '#FF0000',
                                    strokeOpacity: 0.8,
                                    strokeWeight: 2,
                                    fillColor: '#FF0000',
                                    fillOpacity: 0.35
                                });
                                poligono.setMap(this.map);
                                poligono.addListener('click', () => {
                                    this.showPolygonInfo(region.id, region.nombre);
                                });
                            });
                        });
                    })
            };
            document.head.appendChild(script);


        },
        wktToLatLng(wkt) {
            if (!wkt || typeof wkt !== 'string') {
                console.error('Invalid WKT:', wkt);
                return [];
            }

            const multipolygonMatch = wkt.match(/MULTIPOLYGON\s*\(\(\((.+)\)\)\)/);
            // console.log(multipolygonMatch[1]);
            if (!multipolygonMatch) {
                console.error('WKT is not a valid MULTIPOLYGON:', wkt);
                return [];
            }

            const coordinatesString = multipolygonMatch[1];
            const polygons = coordinatesString.split('),(');
            // console.log("polygons", polygons);
            const paths = polygons.map(polygon => {
                return polygon.split(',').map(coord => {
                    const [lng, lat] = coord.trim().split(' ').map(Number);
                    // console.log("lat", lat, "lng", lng);
                    return { lat, lng };
                });
            });
            return paths;
        },
        showPolygonInfo(id, name) {
            axios.get(`http://localhost:8080/tarea/tareaRegion/${name}`,
                { headers: { 'Authorization': `Bearer eyJhbGciOiJIUzI1NiJ9.eyJudW1lcm9Eb2N1bWVudG9Wb2x1bnRhcmlvIjoiMjA4NDczODctNSIsIm5vbWJyZVZvbHVudGFyaW8iOiJQYWJsbyIsInN1YiI6InBhYmxvQGdtYWlsLmNvbSIsImlhdCI6MTcxNjgzMjY1MCwiZXhwIjoxNzE3MjY0NjUwfQ.eDYFXSKcqxxFFD481vS3yGB0rWl3aqbLXOsiWM4wWHY` } })
                .then(response => {
                    this.clearMarkers();
                    this.tareas = response.data;
                    response.data.forEach(tarea => {
                        this.addMarker(tarea[4], tarea[5], tarea[3]);
                    });
                }).catch(error => {
                    console.error(error);
                })
        },
        toggleMenu() {
            const aside = document.querySelector('aside');
            aside.style.left = aside.style.left === '0%' ? '-100%' : '0%';
            const section = document.querySelector('.aside-section');
            section.style.left = section.style.left === '27%' ? '0%' : '27%';
            section.style.transition = '0.5s ease-out';
            // despues de 3 segundos se cambia el color de fondo
            setTimeout(() => {
                section.style.backgroundColor = 'rgba(0, 0, 0, 0.3)';
            }, 1000);
        },
        toggleMenuBack() {
            const aside = document.querySelector('aside');
            aside.style.left = '-100%';
            const section = document.querySelector('.aside-section');
            section.style.transition = '0s';
            section.style.backgroundColor = 'rgba(0, 0, 0, 0)';
            section.style.left = '-100%';
        },
        addMarker(lat, lng, title) {
            const marker = new google.maps.Marker({
                position: { lat, lng },
                map: this.map,
                title: title,
            });
            this.markers.push(marker);
        },
        clearMarkers() {
            this.markers.forEach(marker => {
                google.maps.event.clearInstanceListeners(marker);
                marker.setMap(null);
            });
            this.markers = [];
        },
    },
};

</script>

<style scoped>
body {
    margin: 0;
    padding: 0;
}

.container-admin {
    position: absolute;
    margin: 0;
    padding: 0;
    width: 100%;
    height: 100vh;
    background-color: white !important;
    color: white;

}

nav img {
    width: 30px;
    height: 30px;
    margin: 20px;
    filter: invert(1);
    cursor: pointer;
}

nav {
    background-color: #000000;
    width: 100%;
    height: 70px;
    display: flex;
    justify-content: left;
}

nav h1 {
    color: #ffffff;
    font-size: 30px;
    margin: 20px;
    font-family: 'Roboto', sans-serif;
}

nav h3 {
    color: #ffffff;
    font-size: 20px;
    margin: 26px;
    margin-left: 3px;
    margin-bottom: 1px;
    font-family: 'Roboto', sans-serif;
}

main {
    display: grid;
    grid-template-columns: 1fr 1fr;
    grid-gap: 20px;
    margin: 20px;
    width: 97%;
    height: 84vh;
}

.section2 {
    display: grid;
    grid-template-columns: 1fr;
    grid-template-rows: 1fr 1fr;
}

.section2 button {
    transition: 0.3s all;
    background-color: #000000;
    color: #ffffff;
    font-size: 20px;
    font-family: 'Roboto', sans-serif;
    border: none;
    border-radius: 10px;
    cursor: pointer;
    margin: 20px;
    padding: 10px;
    width: 250px;
    height: 50px;
    justify-self: center;
    align-self: center;
}

.section2 button:hover {
    background-color: #ffffff;
    color: #000000;
    border: 1px solid #000000;
}

aside {
    transition: 1s ease-out;
    background-color: #000000;
    width: 27%;
    height: 100vh;
    position: absolute;
    z-index: 1;
    top: 0;
    left: -100%;
}

.aside-section {
    transition: 0.5s ease-out;
    /* background-color: rgba(0, 0, 0, 0.3); */
    background-color: none;
    width: 73%;
    height: 100vh;
    position: absolute;
    z-index: 1;
    top: 0;
    left: -100%;
}

aside h1 {
    color: #ffffff;
    font-size: 30px;
    margin: 20px;
    font-family: 'Roboto', sans-serif;
}

aside img {
    width: 30px;
    height: 30px;
    margin: 20px;
    filter: invert(1);
    cursor: pointer;
}

.containerListTareasRegiones {
    width: 100%;
    height: 300px;
    border: 1px solid black;
    border-radius: 10px;
    overflow: auto;
    font-family: 'Roboto', sans-serif;
}

.containerListTareasRegiones ul {
    padding: 0;
}

.containerListTareasRegiones li {
    padding: 5px;
    padding-left: 10px;
    color: black;
    cursor: pointer;
}

.containerListTareasRegiones span img {
    width: 20px;
    height: 20px;
    margin-right: 10px;
    position: relative;
}

.icon-container {
    display: inline-block;
    width: 20px;
    height: 20px;
    margin-right: 10px;
    position: relative;
    top: 5px;
}

.containerListTareasRegiones li:hover {
    background-color: #f2f2f2;
}
</style>
