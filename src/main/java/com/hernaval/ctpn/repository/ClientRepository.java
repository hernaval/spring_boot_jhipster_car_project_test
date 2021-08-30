package com.hernaval.ctpn.repository;

import com.hernaval.ctpn.domain.Client;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Client entity.
 */
@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    @Query(
        value = "select distinct client from Client client left join fetch client.roles",
        countQuery = "select count(distinct client) from Client client"
    )
    Page<Client> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct client from Client client left join fetch client.roles")
    List<Client> findAllWithEagerRelationships();

    @Query("select client from Client client left join fetch client.roles where client.id =:id")
    Optional<Client> findOneWithEagerRelationships(@Param("id") Long id);

    Optional<Client> findByUsername(String username);
}
