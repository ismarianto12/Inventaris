package com.Inventori.Dto;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserDto {

    @Id
    public Long id;
    public String name;
    public String password;

    public UserDto() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
