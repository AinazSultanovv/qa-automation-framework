package org.qa.core;

import org.qa.models.User;

public interface UserRepository {
    User findById(String id);
    User findByEmail(String email);
    User save(User user);
    void delete(String id);
}
