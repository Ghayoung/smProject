package net.skhu.service;

import javax.servlet.http.HttpServletRequest;

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

	public User changeMeminfo(HttpServletRequest request) {
		User user = UserService.getCurrentUser();
		String pw = Encryption.encrypt(request.getParameter("pw"), Encryption.SHA256);
		if(!(user.getPw().equals(pw))) {
			return null;
		}
		if(!request.getParameter("newPw").equals("")) {
			if(!(request.getParameter("newPw").equals(request.getParameter("newPw2")))) {
				return null;
			}
			user.setPw(Encryption.encrypt(request.getParameter("newPw"), Encryption.SHA256));
		}
		user.setEmail(request.getParameter("email"));
		user.setPhone(request.getParameter("phone"));
		userMapper.update(user);
		return user;
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
