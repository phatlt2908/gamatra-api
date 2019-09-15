package vn.gamatra.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import vn.gamatra.dto.BaseDto;
import vn.gamatra.form.BaseSearchForm;
import vn.gamatra.form.CategoryForm;
import vn.gamatra.form.CategorySearchForm;

@Service
public interface TradingService {
    ResponseEntity<BaseDto> registCategory(CategoryForm categoryForm);

    ResponseEntity<?> getCategoryList(BaseSearchForm<CategorySearchForm> form);
}
