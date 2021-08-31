package com.hernaval.ctpn.repository;

import com.hernaval.ctpn.domain.Role;
import java.util.Optional;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Role entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String s);
}
