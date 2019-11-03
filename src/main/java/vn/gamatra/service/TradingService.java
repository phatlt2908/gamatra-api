package vn.gamatra.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import vn.gamatra.dto.BaseDto;
import vn.gamatra.form.BaseSearchForm;
import vn.gamatra.form.CategoryForm;
import vn.gamatra.form.CategorySearchForm;
import vn.gamatra.form.ProductForm;

@Service
public interface TradingService {
    ResponseEntity<BaseDto> saveCategory(CategoryForm categoryForm);

    ResponseEntity<?> getCategoryList(BaseSearchForm<CategorySearchForm> form);

    ResponseEntity<?> saveProduct(ProductForm productForm);
}
