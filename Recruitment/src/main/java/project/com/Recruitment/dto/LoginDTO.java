package project.com.Recruitment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LoginDTO {
    @NotBlank(message = "Username field cannot be empty")
    @Size(min = 3, max = 30, message = "Username must be between 3 and 30 characters")
    private String username;

    @NotBlank(message = "Password field cannot be empty")
    @Size(min = 4, max = 50, message = "Password must be between 4 and 50 characters long")
    private String password;

    public LoginDTO() {

    }

    public LoginDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }


    /**
     * Get and Set methods for each property
     * @return  the corresponding property
     */
    public String getUsername() {
        return username; 
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
