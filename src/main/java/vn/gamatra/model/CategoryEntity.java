/**
 * Create by: PhatLT
 */

package vn.gamatra.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "category")
public class CategoryEntity implements Serializable {

    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "public.category_id_seq")
    @Column(name = "id")
    private long id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "url_logo")
    private String urlLogo;

    @Column(name = "path")
    private String path;

    @Column(name = "description")
    private String description;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "is_parent")
    private Boolean isParent;

    @Column(name = "create_user_code")
    private String createUserCode;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "update_user_code")
    private String updateUserCode;

    @Column(name = "update_date")
    private Date updateDate;
}
