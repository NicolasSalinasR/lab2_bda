package Backend_Voluntarios.Backend.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.javafaker.Faker;

import Backend_Voluntarios.Backend.Entity.*;

@Service
public class FakerService {
        private Faker faker = new Faker();

        @Autowired
        private PasswordEncoder passwordEncoder;

        public FakerService() {
                this.faker = new Faker();
        }

        public VoluntarioEntity generateVoluntario() {
                String[] equipamientos = { "Casco", "Chaleco", "Botas", "Guantes", "Pantalones", "Camiseta", "Gorro",
                                "Gafas",
                                "Mascarilla", "Cinturón", "Linterna", "Walkie-talkie", "Botiquín", "Extintor",
                                "Martillo", "Sierra",
                                "Hacha", "Pala", "Cuerda", "Cinta" };
                return new VoluntarioEntity(faker.name().fullName(), faker.internet().emailAddress(),
                                faker.number().digits(10),
                                equipamientos[faker.random().nextInt(0, equipamientos.length - 1)],
                                faker.lorem().word(), passwordEncoder.encode("1234"));
        }

        public InstitucionEntity generateInstitucion() {
                String[] instituciones = { "Bomberos de Chile", "Carabineros de Chile", "PDI", "SAMU",
                                "Emergencias Marítimas",
                                "Onemi", "CSN", "Defensa Civil", "Coorporación Nacional Forestal", "Sernageomin",
                                "Cruz roja" };
                return new InstitucionEntity(instituciones[faker.random().nextInt(0, instituciones.length - 1)]);
        }

        public CoordinadorEntity generateCoordinador() {
                return new CoordinadorEntity(faker.name().fullName(), passwordEncoder.encode("1234"),
                                faker.internet().emailAddress(),
                                faker.number().digits(10).toString());
        }

        public HabilidadEntity generateHabilidad() {
                String[] habilidades = { "Primeros Auxilios", "Manejo de extintores", "Rescate en altura",
                                "Rescate en agua",
                                "Manejo de embarcaciones", "Manejo de vehículos de emergencia", "Manejo de drones",
                                "Manejo de armas",
                                "Manejo de explosivos", "Manejo de sustancias peligrosas",
                                "Manejo de animales peligrosos",
                                "Manejo de maquinaria pesada", "Manejo de maquinaria agrícola",
                                "Manejo de maquinaria industrial" };
                return new HabilidadEntity(habilidades[faker.random().nextInt(0, habilidades.length - 1)]);
        }

        public EmergenciaEntity generateEmergencia(InstitucionEntity institucion) {
                String[] emergencias = { "Incendio", "Terremoto", "Inundación", "Accidente de tránsito",
                                "Deslizamiento de tierra",
                                "Avalancha", "Erupción volcánica", "Tsunami", "Ataque terrorista", "Ataque armado",
                                "Ataque químico",
                                "Ataque biológico", "Ataque nuclear", "Ataque cibernético", "Ataque electromagnético" };

                String[] zonas = { "Santiago", "Valparaíso", "Concepción", "Arica", "Iquique", "Antofagasta", "Calama",
                                "Copiapó",
                                "La Serena", "Coquimbo", "Ovalle", "Rancagua", "Talca", "Curicó", "Linares", "Chillán",
                                "Los Ángeles",
                                "Temuco", "Valdivia", "Osorno", "Puerto Montt", "Coyhaique", "Punta Arenas",
                                "Isla de Pascua",
                                "Juan Fernández" };

                return new EmergenciaEntity(emergencias[faker.random().nextInt(0, emergencias.length - 1)],
                                zonas[faker.random().nextInt(0, zonas.length - 1)], "", faker.number().digits(2),
                                faker.number().digits(2), institucion);
        }

        public TareaEntity generateTarea(EmergenciaEntity emergencia) {
                String[] tareas = { "Rescate", "Evacuación", "Atención de heridos", "Atención de enfermos",
                                "Atención de niños",
                                "Atención de ancianos", "Atención de discapacitados", "Atención de animales",
                                "Atención de plantas",
                                "Atención de edificaciones", "Atención de vehículos", "Atención de embarcaciones",
                                "Atención de aeronaves",
                                "Atención de trenes", "Atención de buses", "Atención de camiones",
                                "Atención de motocicletas",
                                "Atención de bicicletas", "Atención de patinetas", "Atención de scooters",
                                "Atención de monopatines",
                                "Atención de patines", "Atención de patines en línea", "Atención de patines de hielo",
                                "Atención de patines de ruedas", "Atención de patines de cuatro ruedas",
                                "Atención de patines de tres ruedas",
                                "Atención de patines de dos ruedas", "Atención de patines de una rueda",
                                "Atención de patines de cero ruedas",
                                "Atención de patines de infinitas ruedas", "Atención de patines de ruedas cuadradas",
                                "Atención de patines de ruedas redondas", "Atención de patines de ruedas triangulares",
                                "Atención de patines de ruedas rectangulares",
                                "Atención de patines de ruedas pentagonales",
                                "Atención de patines de ruedas hexagonales",
                                "Atención de patines de ruedas heptagonales",
                                "Atención de patines de ruedas octogonales",
                                "Atención de patines de ruedas nonagonales",
                                "Atención de patines de ruedas decagonales",
                                "Atención de patines de ruedas undecagonales",
                                "Atención de patines de ruedas dodecagonales",
                                "Atención de patines de ruedas tridecagonales",
                                "Atención de patines de ruedas tetradecagonales",
                                "Atención de patines de ruedas pentadecagonales" };
                return new TareaEntity(tareas[faker.random().nextInt(0, tareas.length - 1)], faker.lorem().sentence(),
                                faker.lorem().word(), emergencia);
        }

        public VoluntarioHabilidadEntity generateVoluntarioHabilidad(VoluntarioEntity voluntario,
                        HabilidadEntity habilidad) {
                return new VoluntarioHabilidadEntity(voluntario, habilidad);
        }

        public RankingEntity generateRanking(TareaEntity tarea, VoluntarioEntity voluntario) {
                return new RankingEntity(tarea, voluntario, voluntario.getNombreVoluntario(),
                                voluntario.getNumeroDocumentoVoluntario(), faker.number().numberBetween(1, 10),
                                faker.lorem().word());
        }

        public TareaHabilidadEntity generateTareaHabilidad(TareaEntity tarea, EmeHabilidadEntity emeHabilidad) {
                String[] equipamientos = { "Casco", "Chaleco", "Botas", "Guantes", "Pantalones", "Camiseta", "Gorro",
                                "Gafas",
                                "Mascarilla", "Cinturón", "Linterna", "Walkie-talkie", "Botiquín", "Extintor",
                                "Martillo", "Sierra",
                                "Hacha", "Pala", "Cuerda", "Cinta" };
                return new TareaHabilidadEntity(tarea, emeHabilidad,
                                equipamientos[faker.random().nextInt(0, equipamientos.length - 1)]);
        }

        public EstadoTareaEntity generateEstadoTarea(TareaEntity tarea) {
                return new EstadoTareaEntity(tarea, faker.random().nextBoolean());
        }

        public EmeHabilidadEntity generateEmeHabilidad(EmergenciaEntity emergencia, HabilidadEntity habilidad) {
                return new EmeHabilidadEntity(emergencia, habilidad);
        }

}
