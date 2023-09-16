package br.com.devleo.securitypassword.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.devleo.securitypassword.domain.Password;

@Repository
public interface PasswordRepository extends JpaRepository<Password, Long>{

    Optional<Password> findPasswordByPassword(String password);
    
}
