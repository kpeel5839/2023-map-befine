package com.mapbefine.mapbefine.repository;

import com.mapbefine.mapbefine.entity.Pin;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PinRepository extends JpaRepository<Pin, Long> {

    @Query(
            "select p "
                    + "from Pin p "
                    + "where p.location.coordinate.latitude BETWEEN :currentLatitude - :distance AND :currentLatitude + :distance "
                    + "AND p.location.coordinate.longitude BETWEEN :currentLongitude - :distance AND :currentLongitude + :distance"
    )
    List<Pin> findAllByRectangle(
            @Param("currentLatitude") BigDecimal currentLatitude,
            @Param("currentLongitude") BigDecimal currentLongitude,
            @Param("distance") BigDecimal distance
    );

}
