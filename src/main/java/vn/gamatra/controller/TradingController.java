package vn.gamatra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.gamatra.constant.APIConst;
import vn.gamatra.constant.CommonConst;
import vn.gamatra.form.BaseSearchForm;
import vn.gamatra.form.CategorySearchForm;
import vn.gamatra.service.TradingService;

@RestController
@CrossOrigin(value = "*")
@RequestMapping(value = CommonConst.BASE_API_URL)
public class TradingController {

    @Autowired
    TradingService tradingService;

    @PostMapping(value = APIConst.GET_CATEGORY_LIST, produces = CommonConst.JSON_UTF8)
    public ResponseEntity<?> getCategoryList(@RequestBody BaseSearchForm<CategorySearchForm> form) {
	    return tradingService.getCategoryList(form);
    }

    @GetMapping(value = "/test", produces = CommonConst.JSON_UTF8)
    public String test() {
        return "teng teng teng";
    }
}
