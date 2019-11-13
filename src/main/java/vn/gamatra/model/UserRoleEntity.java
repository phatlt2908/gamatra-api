/**
 * Create by: PhatLT
 */

package vn.gamatra.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@Data
@Entity
@Table(name = "user_role")
public class UserRoleEntity implements Serializable {

    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "public.user_role_id_seq")
    @Column(name = "id")
    private long id;

    @Column(name = "user_code")
    private String userCode;

    @Column(name = "role_code")
    private String roleCode;
}
