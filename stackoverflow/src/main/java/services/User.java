package services;

import listeners.Listener;

import java.util.List;
import java.util.Optional;

public class User {
    private static final repositories.User repository = new repositories.User();

    public List<models.User> getAllUsers() {
        return repository.findAll();
    }

    public void save(models.User user) {
        repository.save(user);
    }

    public void updateUser(models.User user) {
        repository.update(user);
    }

    public static boolean isAdmin() {
        return Listener.getAuthUser().isPresent() && Listener.getAuthUser().get().getRole().equals("admin");
    }

    public static boolean isAuth() {
        return Listener.getAuthUser().isPresent();
    }

    public static models.User getAuthUser() {
        Optional<models.User> user = Listener.getAuthUser();
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new IllegalArgumentException("Auth error");
        }
    }

}
