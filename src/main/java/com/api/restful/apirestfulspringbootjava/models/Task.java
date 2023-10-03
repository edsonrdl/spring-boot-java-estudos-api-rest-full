package com.api.restful.apirestfulspringbootjava.models;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity //Define a entidade no Banco de Dados
@Table(name = Task.TABLE_NAME)
@AllArgsConstructor //Vai criar o construtor
@NoArgsConstructor //Vai criar o construtor vazio 
@Data
public class Task {
    public static final String TABLE_NAME = "task";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @ManyToOne // Vai definir que várias tarefas são de um usuário (Cardinalidade)
    @JoinColumn(name = "user_id", nullable = false, updatable = false) // vai fazer relção com a chave primária do user
    private User user;

    @Column(name = "description", length = 255, nullable = false)
    // @NotNull
    // @NotEmpty(message = "A descrição  não pode estar vazio")
    @NotBlank //Funciona como o NotNull e o NotEmpty ,mas apenas com String
    @Size(min = 1, max = 255, message = "Mínimo de 1 caracteres e máximo de 255")
    private String description;


    //Código não necessário ,utilizar o lombok já cria os métodos de acesso e construtores
    // public Task() {

    // }

    // public Task(Long id, User user, String description) {
    //     this.id = id;
    //     this.user = user;
    //     this.description = description;
    // }

    // public Long getId() {
    //     return this.id;
    // }

    // public void setId(Long id) {
    //     this.id = id;
    // }

    // public User getUser() {
    //     return this.user;
    // }

    // public void setUser(User user) {
    //     this.user = user;
    // }

    // public String getDescription() {
    //     return this.description;
    // }

    // public void setDescription(String description) {
    //     this.description = description;
    // }

    // @Override
    // public boolean equals(Object obj) {
    //     if (obj == this)
    //         return true;
    //     if (obj == null)
    //         return false;
    //     if (!(obj instanceof Task))
    //         return false;
    //     Task other = (Task) obj;
    //     if (this.id == null)
    //         if (other.id != null)
    //             return false;
    //         else if (!this.id.equals(other.id))
    //             return false;
    //     return Objects.equals(this.id, other.id) && Objects.equals(this.user, other.user)
    //             && Objects.equals(this.description, other.description);
    // }

    // @Override
    // public int hashCode() {
    //     final int prime = 31;
    //     int result = 1;
    //     result = prime * result + (this.id == null ? 0 : this.hashCode());
    //     return result;
    // }

}
