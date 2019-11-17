package vn.gamatra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.gamatra.constant.APIConst;
import vn.gamatra.constant.CommonConst;
import vn.gamatra.form.ProductForm;
import vn.gamatra.service.TradingService;

@RestController
@CrossOrigin(value = "*")
@RequestMapping(value = CommonConst.BASE_API_URL + APIConst.BASE_CUSTOMER)
public class CustomerController {

    @Autowired
    TradingService tradingService;

    @PostMapping(value = APIConst.SAVE_PRODUCT, produces = CommonConst.JSON_UTF8)
    public ResponseEntity<?> saveProduct(@RequestBody ProductForm productForm) {
        return tradingService.saveProduct(productForm);
    }
}
