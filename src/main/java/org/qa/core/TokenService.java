package org.qa.core;

import org.qa.models.AuthToken;

public interface TokenService {
    AuthToken generateToken(String userId);
    boolean validateToken(String token);
    void revokeToken(String token);
}
