package vn.gamatra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.gamatra.constant.APIConst;
import vn.gamatra.constant.CommonConst;
import vn.gamatra.dto.BaseDto;
import vn.gamatra.form.BaseSearchForm;
import vn.gamatra.form.CategoryForm;
import vn.gamatra.form.CategorySearchForm;
import vn.gamatra.form.ProductForm;
import vn.gamatra.service.TradingService;

@RestController
@CrossOrigin(value = "*")
@RequestMapping(value = CommonConst.BASE_API_URL)
public class TradingController {

    @Autowired
    TradingService tradingService;

    @PostMapping(value = APIConst.SAVE_CATEGORY, produces = CommonConst.JSON_UTF8)
    public ResponseEntity<BaseDto> saveCategory(@RequestBody CategoryForm form) {
	    return tradingService.saveCategory(form);
    }

    @PostMapping(value = APIConst.GET_CATEGORY_LIST, produces = CommonConst.JSON_UTF8)
    public ResponseEntity<?> getCategoryList(@RequestBody BaseSearchForm<CategorySearchForm> form) {
	    return tradingService.getCategoryList(form);
    }

    @PostMapping(value = APIConst.SAVE_PRODUCT, produces = CommonConst.JSON_UTF8)
    public ResponseEntity<?> saveProduct(@RequestBody ProductForm productForm) {
        return tradingService.saveProduct(productForm);
    }
}
