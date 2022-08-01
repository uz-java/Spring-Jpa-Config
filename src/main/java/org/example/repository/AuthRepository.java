package org.example.repository;

import org.example.domains.AuthUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author "Tojaliyev Asliddin"
 * @since 01/08/22 19:57 (Monday)
 * SpringMvcJpa/IntelliJ IDEA
 */
@Repository
@Transactional
public interface AuthRepository extends JpaRepository<AuthUserEntity,Long> {
    Optional<AuthUserEntity> findByEmail(String email);
}
