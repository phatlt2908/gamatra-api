package vn.gamatra.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto implements Serializable {
    public static final long serialVersionUID = 1L;

    private String accessToken;
    private String tokenType = "Bearer";

    public LoginDto(String accessToken) {
        this.accessToken = accessToken;
    }
}
