package pro.angelogomez.restapi_oracle.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.angelogomez.restapi_oracle.dto.ApiResponse;
import pro.angelogomez.restapi_oracle.exception.ResourceNotFoundException;
import pro.angelogomez.restapi_oracle.model.Hospital;
import pro.angelogomez.restapi_oracle.service.HospitalService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/hospitals")
@CrossOrigin(origins = "http://localhost:4200")
public class HospitalAPI {

    @Autowired
    private HospitalService hospitalService;

    @GetMapping
    public ResponseEntity<ApiResponse<Hospital>> findAll(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "3") Integer size
    ) {
        ApiResponse<Hospital> response = hospitalService.findAll(page, size);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<Hospital> getHospitalById(@PathVariable Long id) throws ResourceNotFoundException {
        Hospital hospital = hospitalService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hospital not found for this id :: " + id));
        return ResponseEntity.ok().body(hospital);
    }

    @PostMapping("/register")
    public Hospital save(@Valid @RequestBody Hospital hospital) {
        return hospitalService.save(hospital);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Hospital> update(@PathVariable Long id, @Valid @RequestBody Hospital hospitalBody) throws ResourceNotFoundException {
        Hospital hospitalSelect = hospitalService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hospital not found for this id :: " + id));
        hospitalSelect.setIdDistrito(hospitalBody.getIdDistrito());
        hospitalSelect.setNombreHospital(hospitalBody.getNombreHospital());
        hospitalSelect.setAntiguedad(hospitalBody.getAntiguedad());
        hospitalSelect.setArea(hospitalBody.getArea());
        hospitalSelect.setIdGerente(hospitalBody.getIdGerente());
        hospitalSelect.setIdCondicion(hospitalBody.getIdCondicion());
        final Hospital hospitalUpdate = hospitalService.save(hospitalSelect);
        return ResponseEntity.ok(hospitalUpdate);
    }

    @DeleteMapping("/delete/{id}")
    public Map<String, Boolean> deleteHospital(@PathVariable Long id) throws ResourceNotFoundException {
        Hospital hospital = hospitalService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hospital not found for this id :: " + id));
        hospitalService.deleteById(hospital.getIdHospital());
        Map <String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
