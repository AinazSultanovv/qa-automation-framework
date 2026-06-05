package org.qa.models;

import java.time.LocalDateTime;

public class AuthToken {
    private String token;
    private String userId;
    private LocalDateTime expiresAt;

    public AuthToken(String token, String userId, LocalDateTime expiresAt){
        this.token = token;
        this.userId = userId;
        this.expiresAt = expiresAt;
    }

    public String getToken(){return token;}
    public String getUserId(){return userId;}
    public LocalDateTime getExpiresAt(){return expiresAt;}

    public boolean isExpired(){return expiresAt.isBefore(LocalDateTime.now());}

}
