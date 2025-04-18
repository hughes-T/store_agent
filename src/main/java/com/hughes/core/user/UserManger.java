package com.hughes.core.user;

import java.util.Objects;

/**
 * @author hts
 * @date 2025/4/18 13:19
 */
public class UserManger {

    /**
     * 当前用户信息
     */
    private static final ThreadLocal<IUserDecorator> USER_LOCAL = new ThreadLocal<>();


    public static void setUserLocal(IUserDecorator user) {
        USER_LOCAL.set(user);
    }

    public static IUserDecorator getUserLocal() {
        return USER_LOCAL.get();
    }

    public static Long getLocalUserId() {
        IUserDecorator user = getUserLocal();
        return Objects.nonNull(user) ? user.getUserId() : null;
    }

}
