package vn.gamatra.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.gamatra.constant.AppCodeConst;
import vn.gamatra.dto.BaseDto;
import vn.gamatra.dto.CategoryListDto;
import vn.gamatra.form.BaseSearchForm;
import vn.gamatra.form.CategoryForm;
import vn.gamatra.form.CategorySearchForm;
import vn.gamatra.model.CategoryEntity;
import vn.gamatra.repository.CategoryRepository;
import vn.gamatra.service.TradingService;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class TradingServiceImpl implements TradingService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    EntityManager entityManager;

    private static final ModelMapper modelMapper = new ModelMapper();

    @Override
    public ResponseEntity<BaseDto> registCategory(CategoryForm categoryForm) {
        try {
            if (!Objects.nonNull(categoryForm)) {
                throw new NullPointerException("Category form data can not null!");
            }

            CategoryEntity categoryEntity;

            modelMapper.getConfiguration().setAmbiguityIgnored(true);
            categoryEntity = modelMapper.map(categoryForm, CategoryEntity.class);
            categoryEntity.setIsActive(true);
            categoryEntity.setCreateUserCode("Admin");
            categoryEntity.setCreateDate(new Date());
            categoryEntity.setUpdateUserCode("Admin");
            categoryEntity.setUpdateDate(new Date());

            CategoryEntity categoryCheck = categoryRepository.findByCode(categoryForm.getCode());

            if (Objects.nonNull(categoryCheck)) {
                categoryEntity.setId(categoryCheck.getId());
            }

            categoryRepository.save(categoryEntity);

            return ResponseEntity.ok(null);

        } catch (NullPointerException e) {
            log.error(e.getMessage(), e);
            BaseDto base = new BaseDto(AppCodeConst.COMMOM_10, e.getMessage());
            base.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(base, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getCategoryList(BaseSearchForm<CategorySearchForm> form) {
        try {
            List<CategoryListDto> categoryList;

            StringBuilder sql = new StringBuilder();

            sql.append("SELECT new vn.gamatra.dto.CategoryListDto(cate.code, cate.name, cate.urlLogo, cate.path, cate.description) ")
                    .append("FROM CategoryEntity cate ")
                    .append("WHERE cate.isParent = TRUE AND cate.isActive = TRUE");

            TypedQuery<CategoryListDto> querySelect = entityManager.createQuery(sql.toString(), CategoryListDto.class);

            categoryList = querySelect.getResultList();

            List<CategoryListDto> categoryChildList;
            for (CategoryListDto category : categoryList) {
                String parentCode = category.getCode();

                StringBuilder childSql = new StringBuilder();

                childSql.append("SELECT new vn.gamatra.dto.CategoryListDto(cate.code, cate.name, cate.urlLogo, cate.path, cate.description) ")
                        .append("FROM CategoryEntity cate ")
                        .append("WHERE cate.isParent = FALSE AND cate.isActive = TRUE ")
                        .append("AND cate.path LIKE '/" + parentCode + "/%'");

                TypedQuery<CategoryListDto> queryChildSelect = entityManager.createQuery(childSql.toString(), CategoryListDto.class);

                categoryChildList = queryChildSelect.getResultList();

                category.setSubCategory(categoryChildList);
            }

            return ResponseEntity.ok(categoryList);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            BaseDto base = new BaseDto(AppCodeConst.COMMOM_10, e.getMessage());
            base.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(base, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
