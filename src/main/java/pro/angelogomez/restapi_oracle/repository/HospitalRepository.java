package pro.angelogomez.restapi_oracle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.angelogomez.restapi_oracle.model.Hospital;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long> {
}
