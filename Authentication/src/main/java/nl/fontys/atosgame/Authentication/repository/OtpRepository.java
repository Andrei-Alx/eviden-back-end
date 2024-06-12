package nl.fontys.atosgame.Authentication.repository;

import nl.fontys.atosgame.Authentication.model.StoredOTP;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OtpRepository extends JpaRepository<StoredOTP, Long> {
    Optional<StoredOTP> findByEmail(String email);
}
