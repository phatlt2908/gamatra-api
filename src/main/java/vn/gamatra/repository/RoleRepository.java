/**
 * Create by PhatLT
 */

package vn.gamatra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;
import vn.gamatra.model.RoleEntity;

import java.util.List;

public interface RoleRepository
        extends JpaRepository<RoleEntity, Long>, JpaSpecificationExecutor<RoleEntity> {

    /**
     * Get role by code
     * @author PhatLT
     * @param code
     * @return
     */
    RoleEntity findByCode(@Param("code") String code);

}
