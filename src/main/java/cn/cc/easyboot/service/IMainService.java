package cn.cc.easyboot.service;

import io.github.luochangcheng.ccbootcommonauth.vo.LoginResultVo;
import io.github.luochangcheng.ccbootcommonauth.vo.admin.AdminLoginVo;

public interface IMainService {

    LoginResultVo login(AdminLoginVo vo);

    Boolean loginOut();

}
