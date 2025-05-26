package com.example.Hospital.Hospital.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Hospital.Hospital.entity.*;
import com.example.Hospital.Hospital.repository.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/register")
public class RegisterController {
	@Autowired
	private AuxiliaryRepository auxiliarRepository;
	@Autowired
	private PatientRepository patientRepository;
	@Autowired
	private DiagnosisRepository diagnosisRepository;
	@Autowired
	private VitalSignRepository vitalSignRepository;
	@Autowired
	private DrainRepository drainRepository;
	@Autowired
	private DietRepository dietRepository;
	@Autowired
	private DietTypeRepository dietTypeRepository;
	@Autowired
	private DietTextureTypeRepository dietTextureTypeRepository;
	@Autowired
	private HygieneRepository hygieneRepository;
	@Autowired
	private MobilizationRepository mobilizationRepository;
	@Autowired
	private DetailDiagnosisRepository detailDiagnosisRepository;
	@Autowired
	private RegisterRepository registerRepository;

	public RegisterController() {
		super();
	}

	@PostMapping("")
	@Transactional
	public @ResponseBody ResponseEntity<Boolean> createRegister(@RequestBody Register register) {
		try {

			// Add the current Date of the insert data
			register.setDate(new Date());

			// Mobilization
			if (register.getMobilization() != null) {
				Mobilization mobilization = mobilizationRepository.save(register.getMobilization());
				register.setMobilization(mobilization);
			}

			// Hygiene Type
			if (register.getHygieneType() != null) {
				HygieneType hygieneType = hygieneRepository.save(register.getHygieneType());
				register.setHygieneType(hygieneType);
			}

			// Vital Sign
			if (register.getVitalSign() != null) {
				VitalSign vitalSign = vitalSignRepository.save(register.getVitalSign());
				register.setVitalSign(vitalSign);
			}

			// Drain
			if (register.getDrain() != null) {
				Drain drain = drainRepository.save(register.getDrain());
				register.setDrain(drain);
			}

			// Diet
			if (register.getDiet() != null) {
				Diet diet = register.getDiet();
				Diet savedDiet = dietRepository.save(diet);

				// Diet Type
				if (diet.getDietTypes() != null && !diet.getDietTypes().isEmpty()) {
					Set<DietType> updatedDietTypes = new HashSet<>();

					for (DietType dietType : diet.getDietTypes()) {
						Optional<DietType> existingType = dietTypeRepository.findById(dietType.getId());

						if (existingType.isPresent()) {
							DietType foundType = existingType.get();
							updatedDietTypes.add(foundType);

							// Maintain bidirectional relationship
							foundType.getDiets().add(savedDiet);
							dietTypeRepository.save(foundType);
						}
					}

					// Update and save the diet with correct relationships
					savedDiet.setDietTypes(updatedDietTypes);
					savedDiet = dietRepository.save(savedDiet);
				}

				// Diet Texture Type by ID
				if (diet.getDietTypeTexture() != null && diet.getDietTypeTexture().getId() != null) {
					Optional<DietTextureType> textureType = dietTextureTypeRepository
							.findById(diet.getDietTypeTexture().getId());
					if (textureType.isPresent()) {
						savedDiet.setDietTypeTexture(textureType.get());
						savedDiet = dietRepository.save(savedDiet);
					}
				}

				register.setDiet(savedDiet);
			}

			Optional<Auxiliary> auxiliary = auxiliarRepository.findById(register.getAuxiliary().getId());
			register.setAuxiliary(auxiliary.get());
			Optional<Patient> patient = patientRepository.findById(register.getPatient().getHistorialNumber());
			register.setPatient(patient.get());

			registerRepository.save(register);
			return ResponseEntity.ok(true);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
		}
	}

	@PostMapping("/diagnosis")
	public @ResponseBody ResponseEntity<Boolean> createDiagnosis(@RequestBody Map<String, Object> requestMap) {

		try {
			ObjectMapper mapper = new ObjectMapper();

			Register register = mapper.convertValue(requestMap.get("register"), Register.class);
			Diagnosis diagnosis = mapper.convertValue(requestMap.get("diagnosis"), Diagnosis.class);

			if (register.getDate() == null) {
				register.setDate(new Date());
			}

			Diagnosis savedDiagnosis = diagnosisRepository.save(diagnosis);

			if (diagnosis.getDetailDiagnosisSet() != null) {
				for (DetailDiagnosis detail : diagnosis.getDetailDiagnosisSet()) {
					detail.setDiagnosis(savedDiagnosis);
					detailDiagnosisRepository.save(detail);
				}
			}
			register.setDiagnosis(diagnosis);

			Optional<Auxiliary> auxiliary = auxiliarRepository.findById(register.getAuxiliary().getId());
			register.setAuxiliary(auxiliary.get());
			Optional<Patient> patient = patientRepository.findById(register.getPatient().getHistorialNumber());
			register.setPatient(patient.get());

			registerRepository.save(register);

			return ResponseEntity.ok(true);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
		}
	}

	@GetMapping("/vitalSign/{id}")
	public @ResponseBody ResponseEntity<List<VitalSign>> getVitalSignData(@PathVariable int id) {
		List<VitalSign> vitalSigns = registerRepository.findVitalSignsByPatientHistorialNumber(id);
		return ResponseEntity.ok(vitalSigns);
	}

	@GetMapping("/{id}")
	public @ResponseBody ResponseEntity<Register> getCompleteRegisterDataByVitalSignId(@PathVariable int id) {
		Optional<Register> registers = registerRepository.findByVitalSignId(id);

		if (registers.isPresent()) {
			Register register = registers.get();
			return ResponseEntity.ok(register);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/diagnosis/{id}")
	public @ResponseBody ResponseEntity<DetailDiagnosis> getLastDiagnosis(@PathVariable int id) {
		Optional<Register> register = registerRepository
				.findTopByPatientHistorialNumberAndDiagnosisIsNotNullOrderByDateDesc(id);

		if (register.isPresent()) {
			for (DetailDiagnosis diagnosis : register.get().getDiagnosis().getDetailDiagnosisSet()) {
				return ResponseEntity.ok(diagnosis);
			}
		}
		return ResponseEntity.badRequest().build();
	}

	@GetMapping("/diagnosisList/{id}")
	public @ResponseBody ResponseEntity<List<DetailDiagnosis>> getListOfDiagnosis(@PathVariable int id) {
		List<Register> register = registerRepository
				.findByPatientHistorialNumberAndDiagnosisIsNotNullOrderByDateDesc(id);

		List<DetailDiagnosis> diagnosisList = new ArrayList<>();

		for (Register registerItem : register) {
			Optional<Register> optionalRegister = Optional.ofNullable(registerItem);

			if (optionalRegister.isPresent() && optionalRegister.get().getDiagnosis() != null) {
				diagnosisList.addAll(optionalRegister.get().getDiagnosis().getDetailDiagnosisSet());
			}
		}

		if (!diagnosisList.isEmpty()) {
			return ResponseEntity.ok(diagnosisList);
		}

		return ResponseEntity.badRequest().build();
	}

}
