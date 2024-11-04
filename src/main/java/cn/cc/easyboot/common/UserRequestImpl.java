package cn.cc.easyboot.common;

import io.github.luochangcheng.ccbootcommoncore.base.Constant;
import io.github.luochangcheng.ccbootcommoncore.base.IUserRequest;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class UserRequestImpl implements IUserRequest {

    @Override
    public Long getUserId() {
        return Constant.DEFAULT_USER_ID;
    }

    @Override
    public Locale getLanguage() {
        return Constant.DEFAULT_LOCALE;
    }

}
