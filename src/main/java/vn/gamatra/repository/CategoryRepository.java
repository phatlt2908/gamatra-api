/**
 * Create by PhatLT
 */

package vn.gamatra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;
import vn.gamatra.model.CategoryEntity;

public interface CategoryRepository
        extends JpaRepository<CategoryEntity, Long>, JpaSpecificationExecutor<CategoryEntity> {

    /**
     * Get category by code
     * @author PhatLT
     * @param code
     * @return
     */
    CategoryEntity findByCode(@Param("code") String code);

}
