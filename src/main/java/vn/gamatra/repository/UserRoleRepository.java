/**
 * Create by PhatLT
 */

package vn.gamatra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;
import vn.gamatra.model.UserRoleEntity;

import java.util.List;

public interface UserRoleRepository
        extends JpaRepository<UserRoleEntity, Long>, JpaSpecificationExecutor<UserRoleEntity> {

    /**
     * Get user role by user code
     * @author PhatLT
     * @param userCode
     * @return
     */
    List<UserRoleEntity> findByUserCode(@Param("userCode") String userCode);

}
