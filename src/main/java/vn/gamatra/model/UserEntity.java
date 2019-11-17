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
@Table(name = "user", schema = "public")
public class UserEntity implements Serializable {

    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "public.user_id_seq")
    @Column(name = "id")
    private long id;

    @Column(name = "code", unique = true)
    private String code;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "address_city")
    private String addressCity;

    @Column(name = "address_district")
    private String addressDistrict;

    @Column(name = "address_full")
    private String addressFull;

    @Column(name = "url_avatar")
    private String urlAvatar;

    @Column(name = "is_active")
    private Boolean isActive = false;
}
