package com.project.split.entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;




    @NoArgsConstructor
    @AllArgsConstructor
    public class UserDTO {

        private String name;
        private String password;




        public String getName(){return name;}

        public String getPassword() {
            return password;
        }
    }

