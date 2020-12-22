package api.projekt.pl.services;

import api.projekt.pl.models.User;

public interface UserService {
    void save(User user);

    User findByEmail(String email);
}
