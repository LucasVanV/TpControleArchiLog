package fr.lucas_van_vooren.user_service.service;

import fr.lucas_van_vooren.user_service.model.MembershipType;
import fr.lucas_van_vooren.user_service.model.User;

public interface UserService {
    public Iterable<User> getAllUser();
    public User getUserById(Long id);
    public User createUser(User user);
    public void deleteUser(Long id);
    public void updateUser(User user);
    public int getMaxNumberBorrow(MembershipType membershipType);
    public void lockUser(Long id);
    public void unLockUser(Long id);
}
