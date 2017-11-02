package net.skhu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import net.skhu.dto.User;
import net.skhu.mapper.UserMapper;
import net.skhu.utils.Encryption;

@Service
public class UserService {
	@Autowired UserMapper userMapper;

	public User login(String user_id, String pw) {
        User user = userMapper.findOneByUser_id(user_id);
        if (user == null) return null;
        String password = Encryption.encrypt(pw, Encryption.SHA256);
        if (user.getPw().equals(password) == false) return null;
        return user;
    }

	public void join(User user) {
		String pw = Encryption.encrypt(user.getPw(), Encryption.SHA256);
		user.setPw(pw);
		userMapper.insert(user);
		return;
	}

	public static User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof MyAuthenticationProvider.MyAuthenticaion)
            return ((MyAuthenticationProvider.MyAuthenticaion) authentication).getUser();
        return null;
    }

    public static void setCurrentUser(User user) {
        ((MyAuthenticationProvider.MyAuthenticaion)
                SecurityContextHolder.getContext().getAuthentication()).setUser(user);
    }


}
