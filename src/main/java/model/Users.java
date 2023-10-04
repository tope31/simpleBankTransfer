package model;

import lombok.Data;

@Data
public class Users {
    private Integer userId;
    private String username;
    private String password;
    private Integer balance;
    private Integer transactionCount;
}