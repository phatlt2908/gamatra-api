/**
 * Create by: PhatLT
 */

package vn.gamatra.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@Data
@Entity
@Table(name = "product")
public class ProductEntity implements Serializable {

    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "public.product_id_seq")
    @Column(name = "id")
    private long id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "category_code")
    private String categoryCode;

    @Column(name = "descripton")
    private String descripton;

    @Column(name = "is_mall")
    private Boolean isMall = false;

    @Column(name = "is_approved")
    private Boolean isApproved = false;

    @Column(name = "is_checked")
    private Boolean isChecked = false;

    @Column(name = "reject_reason")
    private String rejectReason;

    @Column(name = "price")
    private Double price;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "seen_count")
    private Integer seenCount = 0;

    @Column(name = "bought_count")
    private Integer boughtCount = 0;

    @Column(name = "create_user_code")
    private String createUserCode;

    @Column(name = "create_date")
    private Date createDate;
}
