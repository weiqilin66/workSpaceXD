package com.lwq.springsecuritymybatis.entity;

import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 * userDetails系统获取用户信息的统一接口
 * </p>
 *
 * @author LinWeiQi
 * @since 2020-01-19
 */

@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class User implements UserDetails {

    private static final long serialVersionUID = 1L;
    private int id ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String username;

    private String password;

    private Boolean enabled;

    private Boolean locked;

    private List<Role> roles;

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override // 返回用户的所有角色
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 手动放入
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_"+ role.getName()));
        }
        return authorities;
    }

    // 相当于get方法 删除默认的属性get

    @Override //是否过期
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override //是否没锁定
    public boolean isAccountNonLocked() {

        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override //是否激活
    public boolean isEnabled() {
        return enabled;
    }
}
