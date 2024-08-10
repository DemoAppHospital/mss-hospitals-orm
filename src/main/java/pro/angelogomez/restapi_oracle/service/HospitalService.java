package pro.angelogomez.restapi_oracle.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pro.angelogomez.restapi_oracle.dto.ApiResponse;
import pro.angelogomez.restapi_oracle.model.Hospital;
import pro.angelogomez.restapi_oracle.repository.HospitalRepository;

import java.util.List;
import java.util.Optional;

@Service
public class HospitalService {

    @Autowired
    private HospitalRepository hospitalRepository;

    public ApiResponse<Hospital> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Hospital> hospitalPage = hospitalRepository.findAll(pageable);
        List<Hospital> hospitals = hospitalPage.getContent();
        int totalItems = (int) hospitalPage.getTotalElements();
        int totalPages = hospitalPage.getTotalPages();
        int currentPage = hospitalPage.getNumber();

        ApiResponse.Metadata metadata = new ApiResponse.Metadata(totalItems,totalPages, currentPage, size);
        return new ApiResponse<>("success", hospitals, metadata);
    }

    public Optional<Hospital> findById(Long id) {
        return hospitalRepository.findById(id);
    }

    public Hospital save(Hospital hospital) {
        return hospitalRepository.save(hospital);
    }

    public void deleteById(Long id) {
        hospitalRepository.deleteById(id);
    }
}
