package com.api.restful.apirestfulspringbootjava.services;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.restful.apirestfulspringbootjava.models.User;
import com.api.restful.apirestfulspringbootjava.respositores.UserRepository;


@Service
public class UserService {
    //@Autowiride funciona como o construtor,já que não pode instanciar interface

    @Autowired
    private UserRepository userRepository;


    //Método para Buscar pelo identificador 
    public User findById(long id){
        Optional<User> user=this.userRepository.findById(id);
        return user.orElseThrow(()-> new RuntimeException(
            "Usuário não encontrado! Id:"+ id + ",Tipo:"+ User.class.getName()
        ));
    }
   
    @Transactional
    public User update(User obj){
        User newObj=findById(obj.getId());
        newObj.setPassword(obj.getPassword());
        return this.userRepository.save(newObj);
    }
    public void delete(long id){
        findById(id);
        try {
            this.userRepository.deleteById(id);
            
        } catch (Exception e) {
            throw new RuntimeException("Não é possível deletar porque há relacinamento com outra entidadew");
        }

}
}
