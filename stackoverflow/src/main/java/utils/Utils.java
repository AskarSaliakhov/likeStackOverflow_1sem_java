package utils;

import services.User;

public class Utils {
    public static boolean isPostBelongsToUser(Long userID) {
        return User.isAuth() && User.getAuthUser().getId().equals(userID);
    }

    public static String getUsername(Long userID) {
        User service = new User();
        for (models.User user : service.getAllUsers()) {
            if (user.getId().equals(userID)) {
                return user.getLogin();
            }
        }
        return "";
    }
}
