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
import vn.gamatra.service.TradingService;

@RestController
@RequestMapping(value = CommonConst.BASE_API_URL)
public class TradingController {

    @Autowired
    TradingService tradingService;

	@GetMapping(value = "/test")
    public String getProduct(){
        return "test";
    }

    @PostMapping(value = APIConst.REGIST_UPDATE_CATEGORY, produces = CommonConst.JSON_UTF8)
    public ResponseEntity<BaseDto> registUpdateCategory(@RequestBody CategoryForm form) {
	    return tradingService.registCategory(form);
    }

    @PostMapping(value = APIConst.GET_CATEGORY_LIST, produces = CommonConst.JSON_UTF8)
    public ResponseEntity<?> getCategoryList(@RequestBody BaseSearchForm<CategorySearchForm> form) {
	    return tradingService.getCategoryList(form);
    }
}
