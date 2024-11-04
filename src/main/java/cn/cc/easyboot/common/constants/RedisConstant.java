package cn.cc.easyboot.common.constants;

/**
 * 常数
 *
 * @author 罗长成
 * @since 2023-07-11 17:49:45
 */
public interface RedisConstant {

    //-------缓存
    String TOKEN_CACHE_FORMAT = "userInfoByUserId[clientType=%d,userId=%d]";
    String ACCOUNT_LOCK_CACHE_FORMAT = "accountLock[clientType=%d,userId=%d]";

    //-------缓存

}
