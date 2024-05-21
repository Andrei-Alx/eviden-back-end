package nl.fontys.atosgame.Authentication.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class GameMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true) // Ensure email is unique
    private String email;
    
    private String name;
    private boolean isGameMaster;

    // Constructors
    public GameMaster() {
    }

    public GameMaster(Long id, String name, String email, boolean isGameMaster) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.isGameMaster = isGameMaster;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isGameMaster() {
        return isGameMaster;
    }

    public void setGameMaster(boolean gameMaster) {
        isGameMaster = gameMaster;
    }
}
