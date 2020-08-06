/**
 * create by PhatLT
 */

package vn.gamatra.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryListDto implements Serializable {
    public static final long serialVersionUID = 1L;

    private String code;
    private String name;
    private String urlLogo;
    private String path;
    private String description;
    private List<CategoryListDto> subCategory;

    public CategoryListDto(String code, String name, String urlLogo, String path, String description) {
        this.code = code;
        this.name = name;
        this.urlLogo = urlLogo;
        this.path = path;
        this.description = description;
    }
}
