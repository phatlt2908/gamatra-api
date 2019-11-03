/**
 * Create by PhatLT
 */

package vn.gamatra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;
import vn.gamatra.model.ProductEntity;

public interface ProductRepository
        extends JpaRepository<ProductEntity, Long>, JpaSpecificationExecutor<ProductEntity> {

    /**
     * Get product by code
     * @author PhatLT
     * @param code
     * @return
     */
    ProductEntity findByCode(@Param("code") String code);

}
