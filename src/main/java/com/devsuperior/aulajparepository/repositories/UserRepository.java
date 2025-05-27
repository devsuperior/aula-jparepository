package com.devsuperior.aulajparepository.repositories;

import com.devsuperior.aulajparepository.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT obj FROM User obj WHERE obj.salary >= :minSalary AND <= :maxSalary")
    // Page<User> searchSalary(Double minSalary, Double maxSalary, Pageable pageable); // esta parte foi um deste em aula ao vivo
    Page<User> findBySalaryBetween(Double minSalary, Double maxSalary, Pageable pageable);

    @Query("SELECT obj FROM User obj WHERE LOWER(obj.name) LIKE LOWER(CONCAT('%',:name,'%'))")
    //Page<User> seachName(String name, Pageable pageable);  // esta parte foi um deste em aula ao vivo - OBS.: lower=minúscula like=contenha o nome informado
    Page<User> findByNameContainingIgnoreCase(String name, Pageable pageable); // neste exemplo não precisará usar -> LIKE LOWER(CONCAT('%',:name,'%'))")
}

// Exemplo: WHERE LOWER(nome) LIKE LOWER('%maria%') - contenha Maria