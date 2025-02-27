package fr.lucas_van_vooren.user_service.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.lucas_van_vooren.user_service.kafka.UserKafkaProducer;
import fr.lucas_van_vooren.user_service.model.MembershipType;
import fr.lucas_van_vooren.user_service.model.User;
import fr.lucas_van_vooren.user_service.repository.UserRepository;
import fr.lucas_van_vooren.user_service.service.UserService;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserKafkaProducer userKafkaProducer;

    public Iterable<User> getAllUser() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User createUser(User user) {
        user.setNombreMaxEmprunt(getMaxNumberBorrow(user.getMembershipType()));
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userKafkaProducer.sendUserDeleteEvent(id);
        userRepository.deleteById(id);
    }

    public void updateUser(User user){
        if(!userRepository.existsById(user.getId())){
            throw new RuntimeException("User not found");
        }
        user.setNombreMaxEmprunt(getMaxNumberBorrow(user.getMembershipType()));
        userRepository.save(user);
    }

    public int getMaxNumberBorrow(MembershipType membershipType){
        if(membershipType == MembershipType.REGULAR){
            return 5;
        }else{
            return 7;
        }
    }

    public void addBorrow(Long id, int nbOfBook){
        User user = getUserById(id);
        if(nbOfBook == getMaxNumberBorrow(user.getMembershipType())){
            user.setLocked(true);
            userRepository.save(user);
        }
        
    }
}
