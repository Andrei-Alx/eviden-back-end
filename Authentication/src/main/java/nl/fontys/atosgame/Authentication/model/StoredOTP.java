package nl.fontys.atosgame.Authentication.model;

import java.time.Instant;

public class StoredOTP {
    private final String otp;
    private final Instant expiryTimestamp;

    public StoredOTP(String otp, Instant expiryTimestamp) {
        this.otp = otp;
        this.expiryTimestamp = expiryTimestamp;
    }

    public String getOTP() {
        return otp;
    }

    public Instant getExpiryTimestamp() {
        return expiryTimestamp;
    }
}
