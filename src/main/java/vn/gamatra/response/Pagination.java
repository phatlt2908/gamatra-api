/**
 * Create by PhatLT
 */

package vn.gamatra.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pagination {
    @NotNull
    @Min(0)
    private Integer currentPage;

    @NotNull
    @Min(0)
    private Integer itemsPerPage;

    private String sort;

    private Boolean sortOrder;

    private Long totalItemCount;
}
