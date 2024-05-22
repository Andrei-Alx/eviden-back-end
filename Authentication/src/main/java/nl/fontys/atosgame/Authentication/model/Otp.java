package nl.fontys.atosgame.Authentication.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
public class Otp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String otp;
    private Instant expiryTimestamp;

    public Otp() {
    }

    public Otp(String email, String otp, Instant expiryTimestamp) {
        this.email = email;
        this.otp = otp;
        this.expiryTimestamp = expiryTimestamp;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public Instant getExpiryTimestamp() {
        return expiryTimestamp;
    }

    public void setExpiryTimestamp(Instant expiryTimestamp) {
        this.expiryTimestamp = expiryTimestamp;
    }
}
