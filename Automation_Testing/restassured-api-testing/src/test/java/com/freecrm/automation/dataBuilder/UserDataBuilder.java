package com.freecrm.automation.dataBuilder;
import com.freecrm.automation.apiEngine.model.User;
import java.util.Arrays;
import java.util.List;

public class UserDataBuilder {
//    public static final String USERNAME = "priya.chakraborty";
//    public static final String PASSWORD = "Vet@Nurse2024";

    private UserDataBuilder() {}

    public static User buildNewUser(long user_id, String username, String firstName, String lastName, String email, String password, String phone) {
        return User.builder()
                .id(user_id)
                .username(username)
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .password(password)
                .phone(phone)
                .userStatus(1)
                .build();
    }

    public static User buildUpdatedUser(long user_id, String username, String firstName, String lastName, String email, String password, String phone) {
        return User.builder()
                .id(user_id)
                .username(username)
                .firstName(firstName)
                .lastName(lastName) // Married — last name updated
                .email(email)
                .password(password)
                .phone(phone)
                .userStatus(1)
                .build();
    }

    public static List<User> buildUserList() {
        return Arrays.asList(
                User.builder()
                        .id(55003L)
                        .username("arjun.vet")
                        .firstName("Arjun")
                        .lastName("Mukherjee")
                        .email("arjun.mukherjee@vetclinic.com")
                        .password("Arjun@2024")
                        .phone("9876500002")
                        .userStatus(1)
                        .build(),
                User.builder()
                        .id(55004L)
                        .username("sutapa.adopt")
                        .firstName("Sutapa")
                        .lastName("Ghosh")
                        .email("sutapa.ghosh@adoptions.com")
                        .password("Sutapa@2024")
                        .phone("9876500003")
                        .userStatus(1)
                        .build()
        );
    }
}