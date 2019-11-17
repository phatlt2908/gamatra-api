package vn.gamatra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.gamatra.constant.APIConst;
import vn.gamatra.constant.CommonConst;
import vn.gamatra.dto.BaseDto;
import vn.gamatra.form.CategoryForm;
import vn.gamatra.form.RoleForm;
import vn.gamatra.service.AuthService;
import vn.gamatra.service.TradingService;

@RestController
@CrossOrigin(value = "*")
@RequestMapping(value = CommonConst.BASE_API_URL + APIConst.BASE_ADMIN)
public class AdminController {

    @Autowired
    private TradingService tradingService;

    @Autowired
    private AuthService authService;

    @PostMapping(value = APIConst.SAVE_CATEGORY, produces = CommonConst.JSON_UTF8)
    public ResponseEntity<BaseDto> saveCategory(@RequestBody CategoryForm form) {
        return tradingService.saveCategory(form);
    }

    @PostMapping(value = APIConst.SAVE_ROLE, produces = CommonConst.JSON_UTF8)
    public ResponseEntity<?> saveRole(@RequestBody RoleForm form) {
        return authService.saveRole(form);
    }
}
