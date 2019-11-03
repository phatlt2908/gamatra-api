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
import vn.gamatra.dto.BaseSearchResponse;
import vn.gamatra.dto.CategoryListDto;
import vn.gamatra.form.BaseSearchForm;
import vn.gamatra.form.CategoryForm;
import vn.gamatra.form.CategorySearchForm;
import vn.gamatra.form.ProductForm;
import vn.gamatra.model.CategoryEntity;
import vn.gamatra.model.ProductEntity;
import vn.gamatra.repository.CategoryRepository;
import vn.gamatra.repository.ProductRepository;
import vn.gamatra.response.Pagination;
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
    ProductRepository productRepository;

    @Autowired
    EntityManager entityManager;

    private static final ModelMapper modelMapper = new ModelMapper();

    @Override
    public ResponseEntity<BaseDto> saveCategory(CategoryForm categoryForm) {
        try {
            if (!Objects.nonNull(categoryForm)) {
                throw new NullPointerException("Category form data can not null!");
            }

            CategoryEntity categoryEntity;

            modelMapper.getConfiguration().setAmbiguityIgnored(true);
            categoryEntity = modelMapper.map(categoryForm, CategoryEntity.class);
            categoryEntity.setIsParent(false);
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

            StringBuilder sqlCount = new StringBuilder();
            StringBuilder sqlSelect = new StringBuilder();
            StringBuilder sqlCondition = new StringBuilder();

            sqlCount.append("SELECT COUNT(cate.code) ");

            sqlSelect.append("SELECT new vn.gamatra.dto.CategoryListDto(cate.code, cate.name, cate.urlLogo, cate.urlBanner, cate.path, cate.description) ");

            sqlCondition.append("FROM CategoryEntity cate ")
                    .append("WHERE cate.isParent = TRUE AND cate.isActive = TRUE");

            // generate sql count and sql select list
            sqlCount.append(sqlCondition);
            sqlSelect.append(sqlCondition);

            TypedQuery<Long> queryCount = entityManager.createQuery(sqlCount.toString(), Long.class);
            TypedQuery<CategoryListDto> querySelect = entityManager.createQuery(sqlSelect.toString(), CategoryListDto.class);

            long totalRow = queryCount.getSingleResult();

            // Generate pagination
            Integer currentPage;
            Integer itemsPerPage;
            if ((Objects.nonNull(form.getPagination().getCurrentPage()) && form.getPagination().getCurrentPage() > 0)
                    && Objects.nonNull(form.getPagination().getItemsPerPage()) && form.getPagination().getItemsPerPage() > 0) {
                currentPage = form.getPagination().getCurrentPage();
                itemsPerPage = form.getPagination().getItemsPerPage();
            } else {
                currentPage = 0;
                itemsPerPage = 0;
            }
            if (currentPage > 0 && itemsPerPage > 0) {
                Integer startIndex = (currentPage * itemsPerPage) - itemsPerPage;
                querySelect.setFirstResult(startIndex);
                querySelect.setMaxResults(itemsPerPage);
            }

            categoryList = querySelect.getResultList();

            List<CategoryListDto> categoryChildList;
            for (CategoryListDto category : categoryList) {
                String parentCode = category.getCode();

                StringBuilder childSql = new StringBuilder();

                childSql.append("SELECT new vn.gamatra.dto.CategoryListDto(cate.code, cate.name, cate.urlLogo, cate.urlBanner, cate.path, cate.description) ")
                        .append("FROM CategoryEntity cate ")
                        .append("WHERE cate.isParent = FALSE AND cate.isActive = TRUE ")
                        .append("AND cate.path LIKE '/" + parentCode + "/%'")
                        .append("ORDER BY cate.sortNum ASC, cate.id ASC");

                TypedQuery<CategoryListDto> queryChildSelect = entityManager.createQuery(childSql.toString(), CategoryListDto.class);

                categoryChildList = queryChildSelect.getResultList();

                category.setSubCategory(categoryChildList);
            }

            // Set pagination response
            Pagination pagination = new Pagination();
            pagination.setCurrentPage(form.getPagination().getCurrentPage());
            pagination.setItemsPerPage(form.getPagination().getItemsPerPage());
            pagination.setTotalItemCount(totalRow);
            pagination.setSort((form.getPagination().getSort()));
            pagination.setSortOrder(form.getPagination().getSortOrder());

            //Set queries response
            CategorySearchForm queries = new CategorySearchForm();
            queries.setKeywords(form.getQueries().getKeywords());

            // Return data
            BaseSearchResponse response = new BaseSearchResponse();
            response.setItems(categoryList);
            response.setPagination(pagination);
            response.setQueries(queries);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            BaseDto base = new BaseDto(AppCodeConst.COMMOM_10, e.getMessage());
            base.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(base, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> saveProduct(ProductForm productForm) {
        ProductEntity product;

        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        product = modelMapper.map(productForm, ProductEntity.class);

        productRepository.save(product);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
