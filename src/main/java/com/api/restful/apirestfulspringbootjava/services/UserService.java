package com.api.restful.apirestfulspringbootjava.services;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import java.util.stream.Stream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.restful.apirestfulspringbootjava.models.User;
import com.api.restful.apirestfulspringbootjava.models.dto.UserCreateDTO;
import com.api.restful.apirestfulspringbootjava.models.dto.UserUpdateDTO;
import com.api.restful.apirestfulspringbootjava.models.enums.ProfileEnum;
import com.api.restful.apirestfulspringbootjava.respositores.UserRepository;
import com.api.restful.apirestfulspringbootjava.security.UserSpringSecurity;
import com.api.restful.apirestfulspringbootjava.services.exceptions.AuthorizationException;
import com.api.restful.apirestfulspringbootjava.services.exceptions.DataBindingViolationException;
import com.api.restful.apirestfulspringbootjava.services.exceptions.ObjectNotFoundException;


@Service
public class UserService {
    //@Autowiride funciona como o construtor,já que não pode instanciar interface
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;


    //Método para Buscar pelo identificador apenas quem vai poder buscar é quem for admin
     public User findById(Long id) {
        UserSpringSecurity userSpringSecurity = authenticated();
        if (!Objects.nonNull(userSpringSecurity)
                || !userSpringSecurity.hasRole(ProfileEnum.ADMIN) && !id.equals(userSpringSecurity.getId()))
            throw new AuthorizationException("Acesso negado!");

        Optional<User> user = this.userRepository.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException(
                "Usuário não encontrado! Id: " + id + ", Tipo: " + User.class.getName()));
    }
    @Transactional
    public User create(User obj){
        obj.setId(null);
        obj.setPassword(this.bCryptPasswordEncoder.encode(obj.getPassword()));
        obj.setProfiles(Stream.of(ProfileEnum.USER.getCode()).collect(Collectors.toSet()));
        obj=this.userRepository.save(obj);
        return obj;
    }
   
    @Transactional
    public User update(User obj){
        User newObj=findById(obj.getId());
        newObj.setPassword(obj.getPassword());
        newObj.setPassword(this.bCryptPasswordEncoder.encode(obj.getPassword()));
        return this.userRepository.save(newObj);
    }
    public void delete(long id){
        findById(id);
        try {
            this.userRepository.deleteById(id);
            
        } catch (Exception e) {
            throw new DataBindingViolationException("Não é possível deletar porque há relacinamento com outra entidade");
        }

}
   public static UserSpringSecurity authenticated() {
        try {
            return (UserSpringSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            return null;
        }
    }

    public User fromDTO(@Valid UserCreateDTO obj) {
        User user = new User();
        user.setUsername(obj.getUsername());
        user.setPassword(obj.getPassword());
        return user;
    }

    public User fromDTO(@Valid UserUpdateDTO obj) {
        User user = new User();
        user.setId(obj.getId());
        user.setPassword(obj.getPassword());
        return user;
    }
}
