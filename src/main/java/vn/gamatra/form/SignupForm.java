package vn.gamatra.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import vn.gamatra.constant.RegexConst;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class SignupForm implements Serializable {
    public static final long serialVersionUID = 1L;

    @NotBlank
    @Size(max = 50)
    private String userName;

    @NotBlank
    @Size(max = 256)
    private String password;

    @NotBlank
    @Size(max = 100)
    private String name;

    @NotBlank
    @Size(max = 20)
    @Pattern(regexp = RegexConst.TEL_REGEX)
    private String phone;

    @NotBlank
    @Size(max = 100)
    @Pattern(regexp = RegexConst.EMAIL_REGEX)
    private String email;

    @NotBlank
    @Size(max = 30)
    private String addressCity;

    @NotBlank
    @Size(max = 30)
    private String addressDistrict;

    @NotBlank
    @Size(max = 30)
    private String addressFull;
}
