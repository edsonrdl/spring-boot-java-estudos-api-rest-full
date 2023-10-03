package com.api.restful.apirestfulspringbootjava.services;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.restful.apirestfulspringbootjava.models.Task;
import com.api.restful.apirestfulspringbootjava.models.User;
import com.api.restful.apirestfulspringbootjava.models.enums.ProfileEnum;
import com.api.restful.apirestfulspringbootjava.models.projection.TaskProjection;
import com.api.restful.apirestfulspringbootjava.respositores.TaskRepository;
import com.api.restful.apirestfulspringbootjava.security.UserSpringSecurity;
import com.api.restful.apirestfulspringbootjava.services.exceptions.AuthorizationException;
import com.api.restful.apirestfulspringbootjava.services.exceptions.ObjectNotFoundException;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserService userService;

    public Task findById(Long id) {
        Task task = this.taskRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
                "Tarefa não encontrada! id:" + id + ",Tipos :" + Task.class.getName()));
        UserSpringSecurity userSpringSecurity = UserService.authenticated();
        if (!Objects.nonNull(userSpringSecurity)
                || !userSpringSecurity.hasRole(ProfileEnum.ADMIN) && !id.equals(userSpringSecurity.getId()))
            throw new AuthorizationException("Acesso negado!");
        return task;
    }

    public List<TaskProjection> findAllByUser() {
        UserSpringSecurity userSpringSecurity = UserService.authenticated();
        if (!Objects.nonNull(userSpringSecurity))
            throw new AuthorizationException("Acesso negado!");
        List<TaskProjection> tasks = this.taskRepository.findByUser_Id(userSpringSecurity.getId());
        return tasks;
    }

    @Transactional
    public Task create(Task obj) {
        UserSpringSecurity userSpringSecurity = UserService.authenticated();
        if (!Objects.nonNull(userSpringSecurity))
            throw new AuthorizationException("Acesso negado!");
        User user = this.userService.findById(userSpringSecurity.getId());
        obj.setId(null);
        obj.setUser(user);
        obj = this.taskRepository.save(obj);
        return obj;

    }

    @Transactional
    public Task update(Task obj) {
        Task newObj = findById(obj.getId());
        newObj.setDescription(obj.getDescription());
        return this.taskRepository.save(newObj);
    }

    public void delete(long id) {
        findById(id);
        try {
            this.taskRepository.deleteById(id);
        } catch (Exception e) {

            throw new RuntimeException("Não é possivel excluir,pois existe relacionamento entre tabelas");
        }
    }
}
