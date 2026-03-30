package com.freecrm.automation.dataBuilder;
import com.freecrm.automation.apiEngine.model.User;
import java.util.Arrays;
import java.util.List;

public class UserDataBuilder {
    public static final String USERNAME = "priya.chakraborty";
    public static final String PASSWORD = "Vet@Nurse2024";

    private UserDataBuilder() {}

    public static User buildNewUser(long user_id) {
        return User.builder()
                .id(user_id)
                .username(USERNAME)
                .firstName("Priya")
                .lastName("Chakraborty")
                .email("priya.chakraborty@vetclinic.com")
                .password(PASSWORD)
                .phone("9876500001")
                .userStatus(1)
                .build();
    }

    public static User buildUpdatedUser(long user_id) {
        return User.builder()
                .id(user_id)
                .username(USERNAME)
                .firstName("Priya")
                .lastName("Chakraborty-Banerjee")    // Married — last name updated
                .email("p.chakraborty@vetclinic.com") // New work email
                .password(PASSWORD)
                .phone("9876500099")                  // New phone number
                .userStatus(1)
                .build();
    }

    public static List<User> buildUserList() {
        return Arrays.asList(
                User.builder()
                        .id(55002L)
                        .username("arjun.vet")
                        .firstName("Arjun")
                        .lastName("Mukherjee")
                        .email("arjun.mukherjee@vetclinic.com")
                        .password("Arjun@2024")
                        .phone("9876500002")
                        .userStatus(1)
                        .build(),
                User.builder()
                        .id(55003L)
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