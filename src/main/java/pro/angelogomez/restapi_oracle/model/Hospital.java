package pro.angelogomez.restapi_oracle.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "Hospital")
@Data
public class Hospital {
    @Id
    @Column(name = "idHospital", nullable = false, updatable = false)
    private Long idHospital;

    @Column(name = "idDistrito", nullable = false)
    private Long idDistrito;

    @Column(name = "nombreHospital", nullable = false, length = 200)
    private String nombreHospital;

    private Integer antiguedad;

    private Float area;

    @Column(name = "idSede", nullable = false)
    private Long idSede;

    @Column(name = "idGerente", nullable = false)
    private Long idGerente;

    @Column(name = "idCondicion",nullable = false)
    private Long idCondicion;

    @Column(name = "fechaRegistro", nullable = false, updatable = false)
    private Date fechaRegistro;
}
