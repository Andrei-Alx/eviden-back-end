package nl.fontys.atosgame.Authentication.repository;

import nl.fontys.atosgame.Authentication.model.GameMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameMasterRepository extends JpaRepository<GameMaster, Long> {

    // Define custom query methods if needed
    GameMaster findByEmail(String email); // Find game master by email
}

