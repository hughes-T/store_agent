package com.hughes.core.user;

import com.google.common.collect.Lists;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * @author hts
 * @date 2025/4/18 13:39
 */
public class DealerUser extends User implements IUserDecorator {


    public DealerUser(String username, String password) {
        super(username, password, Lists.newArrayList());
    }

    @Override
    public String getTradeType() {
        return null;
    }

    @Override
    public Long getUserId() {
        return 2333L;
    }



}
