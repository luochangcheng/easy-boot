package cn.cc.easyboot.service.impl.main;

import cn.cc.easyboot.common.constants.RedisConstant;
import cn.cc.easyboot.service.IMainService;
import com.alibaba.fastjson2.JSON;
import com.google.common.collect.Sets;
import io.github.luochangcheng.ccbootcommonauth.common.properties.AdminSecurityProperties;
import io.github.luochangcheng.ccbootcommonauth.model.admin.AdminUserDetailModel;
import io.github.luochangcheng.ccbootcommonauth.service.ILoginService;
import io.github.luochangcheng.ccbootcommonauth.utils.admin.JwtUtil;
import io.github.luochangcheng.ccbootcommonauth.vo.LoginResultVo;
import io.github.luochangcheng.ccbootcommonauth.vo.admin.AdminLoginVo;
import io.github.luochangcheng.ccbootcommonauth.vo.applet.AppletLoginVo;
import io.github.luochangcheng.ccbootcommoncore.enums.ClientType;
import io.github.luochangcheng.ccbootstarterredis.utils.RedisUtil;
import io.github.luochangcheng.ccbootstarterweb.common.enums.ApiErrorCode;
import io.github.luochangcheng.ccbootstarterweb.common.exception.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class MainServiceImpl implements IMainService, ILoginService {

    @Autowired
    private AdminSecurityProperties properties;
    @Autowired
    private RedisUtil<UserDetails> userDetailsRedisUtil;
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public LoginResultVo toLogin(AdminLoginVo vo) {
        //todo 查询用户
        AdminUserDetailModel model = new AdminUserDetailModel();
        model.setUserId(1L);
        model.setUsername("luochangcheng");
        model.setPassword("123456");
        model.setEnabled(true);
        model.setAccountNonExpired(true);
        model.setAccountNonLocked(true);
        model.setCredentialsNonExpired(true);
        model.setAuthorities(Sets.newHashSet());
        String token = jwtUtil.createToken(model);
        model.setToken(token);
        userDetailsRedisUtil.set(String.format(RedisConstant.TOKEN_CACHE_FORMAT, ClientType.ADMIN.getCode(), model.getUserId()), model, Duration.ofHours(properties.getAdminTokenExpireTime()));
        return new LoginResultVo(token, properties.getAdminTokenExpireTime());
    }

    @Override
    public LoginResultVo toLogin(AppletLoginVo vo) {
        return null;
    }

    @Override
    public boolean toLoginOut() {
        return true;
    }

    @Override
    public String accessDeniedJson() {
        return JSON.toJSONString(R.failed(ApiErrorCode.AUTHORITY_NOT_ENOUGH));
    }

    @Override
    public String authenticationEntryPointJson() {
        return JSON.toJSONString(R.failed(ApiErrorCode.UNAUTHORIZED));
    }


    @Override
    public LoginResultVo login(AdminLoginVo vo) {
        return toLogin(vo);
    }

    @Override
    public Boolean loginOut() {
        return toLoginOut();
    }

}
