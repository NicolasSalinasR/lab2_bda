package Backend_Voluntarios.Backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import Backend_Voluntarios.Backend.Service.FakerService;
import Backend_Voluntarios.Backend.Entity.*;
import Backend_Voluntarios.Backend.Repository.*;

@SpringBootApplication
public class BackendApplication implements CommandLineRunner {
	@Autowired
	private FakerService fakerService;

	@Autowired
	private CoordinadorRepository coordinadorRepository;

	@Autowired
	private EmeHabilidadRepository emeHabilidadRepository;

	@Autowired
	private EmergenciaRepository emergenciaRepository;

	@Autowired
	private EstadoTareaRepository estadoTareaRepository;

	@Autowired
	private HabilidadRepository habilidadRepository;

	@Autowired
	private InstitucionRepository institucionRepository;

	@Autowired
	private RankingRepository rankingRepository;

	@Autowired
	private TareaHabilidadRepository tareaHabilidadRepository;

	@Autowired
	private TareaRepository tareaRepository;

	@Autowired
	private VoluntarioHabilidadRepository volluntarioHabilidadRepository;

	@Autowired
	private VoluntarioRepository voluntarioRepository;

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		for (int i = 0; i < 10; i++) {
			CoordinadorEntity coordinador = fakerService.generateCoordinador();
			InstitucionEntity institucion = fakerService.generateInstitucion();
			EmergenciaEntity emergencia = fakerService.generateEmergencia(institucion);
			HabilidadEntity habilidad = fakerService.generateHabilidad();
			EmeHabilidadEntity emeHabilidad = fakerService.generateEmeHabilidad(emergencia, habilidad);
			TareaEntity tarea = fakerService.generateTarea(emergencia);
			EstadoTareaEntity estadoTarea = fakerService.generateEstadoTarea(tarea);
			VoluntarioEntity voluntario = fakerService.generateVoluntario();
			RankingEntity ranking = fakerService.generateRanking(tarea, voluntario);
			TareaHabilidadEntity tareaHabilidad = fakerService.generateTareaHabilidad(tarea, emeHabilidad);
			VoluntarioHabilidadEntity voluntarioHabilidad = fakerService.generateVoluntarioHabilidad(voluntario,
					habilidad);

			coordinadorRepository.save(coordinador);
			institucionRepository.save(institucion);
			emergenciaRepository.save(emergencia);
			habilidadRepository.save(habilidad);
			emeHabilidadRepository.save(emeHabilidad);
			tareaRepository.save(tarea);
			estadoTareaRepository.save(estadoTarea);
			voluntarioRepository.save(voluntario);
			rankingRepository.save(ranking);
			tareaHabilidadRepository.save(tareaHabilidad);
			volluntarioHabilidadRepository.save(voluntarioHabilidad);
		}
	}

}