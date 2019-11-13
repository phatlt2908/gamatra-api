/**
 * Create by PhatLT
 */

package vn.gamatra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;
import vn.gamatra.model.UserEntity;

public interface UserRepository
        extends JpaRepository<UserEntity, Long>, JpaSpecificationExecutor<UserEntity> {

    /**
     * Get user by code
     * @author PhatLT
     * @param code
     * @return
     */
    UserEntity findByCode(@Param("code") String code);

}
