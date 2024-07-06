package com.api.todolist.respositores;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.api.todolist.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Transactional(readOnly = true)
    User findByUsername(String username);
}
// Não precisa adicionar nenhuma método Já existe dentro
// JpaRepository<class,e o tipo do id>