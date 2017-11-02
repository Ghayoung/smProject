package net.skhu.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import net.skhu.dto.User;

@Component
public class MyAuthenticationProvider implements AuthenticationProvider {

	@Autowired UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String user_id = authentication.getName();
        String pw = authentication.getCredentials().toString();
        return authenticate(user_id, pw);
    }

    public Authentication authenticate(String user_id, String pw) throws AuthenticationException {
        User user = userService.login(user_id, pw);
        if (user == null) return null;

        List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        String role = "";
        switch (user.getType()) {
        case 1: role = "ROLE_USER"; break;
        case 2: role = "ROLE_MANAGER"; break;
        case 3: role = "ROLE_MENTOR"; break;
        case 4: role = "ROLE_MENTEE"; break;
        }
        grantedAuthorities.add(new SimpleGrantedAuthority(role));
        return new MyAuthenticaion(user_id, pw, grantedAuthorities, user);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    public class MyAuthenticaion extends UsernamePasswordAuthenticationToken {
        private static final long serialVersionUID = 1L;
        User user;

        public MyAuthenticaion (String user_id, String pw,
                                List<GrantedAuthority> grantedAuthorities, User user) {
            super(user_id, pw, grantedAuthorities);
            this.user = user;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }
    }

}
