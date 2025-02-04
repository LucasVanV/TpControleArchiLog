package fr.lucas_van_vooren.user_service.repository;

import org.springframework.data.repository.CrudRepository;

import fr.lucas_van_vooren.user_service.model.User;

public interface UserRepository extends CrudRepository<User, Long>{

}
