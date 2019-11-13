package vn.gamatra.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class LoginForm implements Serializable {
    public static final long serialVersionUID = 1L;

    @NotBlank
    private String userCodeOrPhoneOrEmail;

    @NotBlank
    private String password;
}
