package vn.gamatra.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class RoleForm implements Serializable {
    public static final long serialVersionUID = 1L;

    @NotEmpty
    @Size(max = 20)
    private String code;

    @NotEmpty
    @Size(max = 100)
    private String name;
}
