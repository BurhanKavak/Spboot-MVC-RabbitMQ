package com.example.springmvcrabbitmq.dto.response;

import com.example.springmvcrabbitmq.model.User;
import lombok.Data;

@Data
public class UserDtoForResponse {

    private String name;

    private String email;

    public static UserDtoForResponse fromUser(User user) {
        UserDtoForResponse userDtoForResponse = new UserDtoForResponse();
        userDtoForResponse.setName(user.getName());
        userDtoForResponse.setEmail(user.getEmail());
        return userDtoForResponse;
    }

    public User toUser() {
        User user = new User();
        user.setName(this.name);
        user.setEmail(this.email);
        return user;
    }

}
