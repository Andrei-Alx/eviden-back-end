package nl.fontys.atosgame.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import nl.fontys.atosgame.model.GameMaster; // Import your GameMaster entity class

@Repository
public interface GameMasterRepository extends JpaRepository<GameMaster, Long> {

    // Define custom query methods if needed
    GameMaster findByEmail(String email);
}

