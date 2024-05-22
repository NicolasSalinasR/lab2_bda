package Backend_Voluntarios.Backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import Backend_Voluntarios.Backend.Entity.*;
import Backend_Voluntarios.Backend.Repository.*;

@SpringBootApplication
public class BackendApplication implements CommandLineRunner {

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

	}
}