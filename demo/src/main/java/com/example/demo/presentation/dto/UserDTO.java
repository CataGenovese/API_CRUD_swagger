package com.example.demo.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//creamos dto para poder mapear la info que nos envia el front-end
public class UserDTO {

    private Long id;
    private String name;
    private String lastName;
    private String email;
    private byte age;


    // Agrega getters y setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = (byte) age;
    }
}

