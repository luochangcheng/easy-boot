package cn.cc.easyboot.common.filter;

import cn.cc.easyboot.common.constants.RedisConstant;
import io.github.luochangcheng.ccbootcommonauth.common.filter.AuthenticationTokenFilter;
import io.github.luochangcheng.ccbootcommonauth.model.admin.AdminUserDetailModel;
import io.github.luochangcheng.ccbootcommoncore.enums.ClientType;
import io.github.luochangcheng.ccbootstarterredis.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * JWT身份验证令牌过滤器
 *
 * @author 罗长成
 * @since 2024-06-29 23:43:46
 */
@Component
public class JwtAuthenticationTokenFilter extends AuthenticationTokenFilter<AdminUserDetailModel> {


    @Autowired
    private RedisUtil<AdminUserDetailModel> redisUtil;

    @Override
    protected AdminUserDetailModel getUserDetailFromUserId(Long userId) {

        //从redis读取用户信息
        return redisUtil.get(String.format(RedisConstant.TOKEN_CACHE_FORMAT, ClientType.ADMIN.getCode(), userId));
    }
}