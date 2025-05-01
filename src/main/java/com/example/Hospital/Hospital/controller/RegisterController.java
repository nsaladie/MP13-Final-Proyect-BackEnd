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
				// Diet Type
				if (diet.getDietTypes() != null && !diet.getDietTypes().isEmpty()) {
					Set<DietType> updatedDietTypes = new HashSet<>();

					for (DietType dietType : diet.getDietTypes()) {
						// Search by description the types of Diet
						DietType existingTypeByDesc = dietTypeRepository.findByDescription(dietType.getDescription());

						if (existingTypeByDesc != null) {
							// Add to the list the existing types
							updatedDietTypes.add(existingTypeByDesc);
						}
					}

					// Update the list with only the existing types
					diet.setDietTypes(updatedDietTypes);
				}
				// Diet Texture Type
				if (diet.getDietTypeTexture() != null && diet.getDietTypeTexture().getDescription() != null) {
					String description = diet.getDietTypeTexture().getDescription();
					diet.setDietTypeTexture(null);
					diet.setDietTypeTexture(dietTextureTypeRepository.findByDescription(description));
				}

				Diet savedDiet = dietRepository.save(diet);
				register.setDiet(savedDiet);
			}

			// Diagnosis
			if (register.getDiagnosis() != null) {
				Diagnosis diagnosis = diagnosisRepository.save(register.getDiagnosis());
				// Detail Diagnosis
				if (register.getDiagnosis().getDetailDiagnosisSet() != null) {
					for (DetailDiagnosis detail : register.getDiagnosis().getDetailDiagnosisSet()) {
						detail.setDiagnosis(diagnosis);
						detailDiagnosisRepository.save(detail);
					}
				}
				register.setDiagnosis(diagnosis);
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
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
		}
	}

	@GetMapping("/vitalSign/{id}")
	public @ResponseBody ResponseEntity<Iterable<VitalSign>> getVitalSignData(@PathVariable int id) {

		List<Register> registers = registerRepository
				.findByPatientHistorialNumberAndVitalSignIsNotNullOrderByDateDesc(id);

		List<VitalSign> vitalSigns = new ArrayList<>();
		for (Register register : registers) {
			vitalSigns.add(register.getVitalSign());
		}

		if (!vitalSigns.isEmpty()) {
			return ResponseEntity.ok(vitalSigns);
		} else {
			return ResponseEntity.notFound().build();
		}

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

	@PostMapping("/dietType")
	public @ResponseBody ResponseEntity<Boolean> createDietType(@RequestBody List<DietType> dietType) {
		try {
			dietTypeRepository.saveAll(dietType);
			return ResponseEntity.status(HttpStatus.CREATED).body(true);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(false);
		}
	}

}
