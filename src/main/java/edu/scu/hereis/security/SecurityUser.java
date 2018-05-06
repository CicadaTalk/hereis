package edu.scu.hereis.security;

import edu.scu.hereis.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 将用户信息映射到Security的登录角色上
 */
public class SecurityUser extends User implements UserDetails {

    /**
     * 序列化版本号
     */
    private static final long serialVersionUID = 1L;

    public SecurityUser(User user) {

        if (null != user) {
            this.setHereisId(user.getHereisId());
            this.setImgPath(user.getImgPath());
            this.setRole(user.getRole());
        }

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();

        //授权
        SimpleGrantedAuthority authority = null;

        //设置角色
        authority = new SimpleGrantedAuthority(getRole());

        authorities.add(authority);
        return authorities;
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return getHereisId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
