package com.example.Hospital.Hospital.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Hospital.Hospital.entity.*;
import com.example.Hospital.Hospital.repository.*;

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
	private HygieneRepository hygieneRepository;
	@Autowired
	private MobilizationRepository mobilizationRepository;
	@Autowired
	private DetailDiagnosisRepository detailDiagnosisRepository;
	@Autowired
	private DietTypeRepository dietTypeRepository;
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
				Diet diet = dietRepository.save(register.getDiet());

				if (diet.getDietTypes() != null && !diet.getDietTypes().isEmpty()) {
					for (DietType dietType : diet.getDietTypes()) {
						if (dietType.getId() == null)
							dietTypeRepository.save(dietType);
					}
				}
				register.setDiet(diet);
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

    @GetMapping("/vitalSign/{id}")
    public @ResponseBody ResponseEntity<Iterable<VitalSign>> getVitalSignData(@PathVariable int id) {

		List<Register> registers = registerRepository.findByPatientHistorialNumberOrderByDateDesc(id);

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
	public @ResponseBody ResponseEntity<Map<String, Object>> getCompleteRegisterDataByRegisterId(@PathVariable int id) {

        Optional<Register> registers = registerRepository.findByVitalSignId(id);

		if (!registers.isEmpty()) {
			Register register = registers.get();
			Map<String, Object> response = new HashMap<>();

			response.put("id", register.getId());
			response.put("auxiliary", register.getAuxiliary());
			response.put("patient", register.getPatient());
			response.put("date", register.getDate().toString());
			response.put("hygieneType", register.getHygieneType());
			response.put("observation", register.getObservation());
			response.put("diet", register.getDiet());
			response.put("drain", register.getDrain());
			response.put("mobilization", register.getMobilization());
			response.put("vitalSign", register.getVitalSign());

			return ResponseEntity.ok(response);
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
}
