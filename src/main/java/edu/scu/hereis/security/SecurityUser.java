package edu.scu.hereis.security;

import edu.scu.hereis.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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


    //登录时使用的code，相当于userName
    private String LoginCode;

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

    public String getLoginCode() {
        return LoginCode;
    }

    public void setLoginCode(String loginCode) {
        LoginCode = loginCode;
    }

    @Override
    public String getPassword() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        return passwordEncoder.encode("hereis");
    }

    @Override
    public String getUsername() {
        return getLoginCode();
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
