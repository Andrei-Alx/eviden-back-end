package nl.fontys.atosgame.Authentication.repository;

import nl.fontys.atosgame.Authentication.model.GameMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;


import java.util.Optional;

@Repository
public interface GameMasterRepository extends JpaRepository<GameMaster, Long> {

    // Define custom query methods if needed
    GameMaster findByEmail(String email); // Find game master by email
}

