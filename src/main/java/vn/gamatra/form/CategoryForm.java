package vn.gamatra.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class CategoryForm implements Serializable {
    public static final long serialVersionUID = 1L;

    @NotEmpty
    @Size(max = 10)
    private String code;

    @NotEmpty
    @Size(max = 100)
    private String name;

    private String urlLogo;

    @NotEmpty
    @Size(max = 50)
    private String path;

    @Size(max = 200)
    private String descripton;
}
