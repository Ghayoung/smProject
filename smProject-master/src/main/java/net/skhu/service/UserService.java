package net.skhu.service;

import org.springframework.beans.factory.annotation.Autowired;
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

}
