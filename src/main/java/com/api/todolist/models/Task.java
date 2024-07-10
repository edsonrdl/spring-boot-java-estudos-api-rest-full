package com.api.todolist.models;


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
@Table(name ="task")
@AllArgsConstructor 
@NoArgsConstructor
@Data
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @ManyToOne 
    @JoinColumn(name = "user_id", nullable = false, updatable = false) 
    private User user;

    @Column(name = "description", length = 255, nullable = false)
    @NotBlank 
    @Size(min = 1, max = 255, message = "Mínimo de 1 caracteres e máximo de 255")
    private String description;



}
