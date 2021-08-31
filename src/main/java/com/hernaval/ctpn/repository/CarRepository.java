package com.hernaval.ctpn.repository;

import com.hernaval.ctpn.domain.Car;
import java.util.Optional;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Car entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    @Query("select car from Car car left join fetch car.commenters where car.id =:id")
    Optional<Car> findOneWithEagerRelationships(@Param("id") Long id);
}
