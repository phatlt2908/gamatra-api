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
public class ProductForm implements Serializable {
    public static final long serialVersionUID = 1L;

    @NotEmpty
    @Size(max = 30)
    private String code;

    @NotEmpty
    @Size(max = 100)
    private String name;

    @Size(max = 10)
    private String categoryCode;

    @Size(max = 100)
    private String categoryOtherName;

    @Size(max = 200)
    private String descripton;

    private Double price;

    private Integer quantity;
}
