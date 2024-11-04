package cn.cc.easyboot.controller.common;

import cn.cc.easyboot.service.IMainService;
import io.github.luochangcheng.ccbootcommonauth.vo.LoginResultVo;
import io.github.luochangcheng.ccbootcommonauth.vo.admin.AdminLoginVo;
import io.github.luochangcheng.ccbootstarterweb.common.exception.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 首页 控制器
 *
 * @author 罗长成
 * @since 2024-06-30 00:10:45
 */
@RestController
@RequestMapping("/common/main")
@Api(tags = "公共-首页")
public class MainController {

    @Autowired
    private IMainService mainService;

    @ApiOperation("登录")
    @PostMapping("/login")
    public R<LoginResultVo> login(@RequestBody AdminLoginVo vo) {
        return R.ok(mainService.login(vo));
    }

    @ApiOperation("注销")
    @DeleteMapping("/loginOut")
    public R<Boolean> loginOut() {
        return R.ok(mainService.loginOut());
    }

}
