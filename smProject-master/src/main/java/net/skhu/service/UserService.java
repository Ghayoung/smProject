package net.skhu.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import net.skhu.dto.Article;
import net.skhu.dto.User;
import net.skhu.mapper.ArticleMapper;
import net.skhu.mapper.UserMapper;
import net.skhu.model.Pagination;
import net.skhu.utils.Encryption;

@Service
public class UserService {
	@Autowired
	UserMapper userMapper;
	@Autowired
	ArticleMapper articleMapper;
	@Autowired
	FileService fileService;

	public User login(String user_id, String pw) {
		User user = userMapper.findOneByUser_id(user_id);
		if (user == null)
			return null;
		if(user.getCondition()==1)
			return null;
		String password = Encryption.encrypt(pw, Encryption.SHA256);
		if (user.getPw().equals(password) == false)
			return null;
		return user;
	}

	public void join(User user) {
		String pw = Encryption.encrypt(user.getPw(), Encryption.SHA256);
		user.setPw(pw);

		if (user.getDouble_id() == 0 && user.getMinor_id() == 0) {
			userMapper.insertWithDep(user);
		} else {
			if (user.getDouble_id() == 0)
				userMapper.insertWithMinor(user);
			else if (user.getMinor_id() == 0)
				userMapper.insertWithDouble(user);
		}
		return;
	}

	public User changeMeminfo(HttpServletRequest request) {
		User user = UserService.getCurrentUser();
		String pw = Encryption.encrypt(request.getParameter("pw"), Encryption.SHA256);
		if (!(user.getPw().equals(pw))) {
			return null;
		}
		if (!request.getParameter("newPw").equals("")) {
			if (!(request.getParameter("newPw").equals(request.getParameter("newPw2")))) {
				return null;
			}
			user.setPw(Encryption.encrypt(request.getParameter("newPw"), Encryption.SHA256));
		}
		user.setEmail(request.getParameter("email"));
		user.setPhone(request.getParameter("phone"));
		userMapper.update(user);
		return user;
	}

	public void memDrop() {
		User user = UserService.getCurrentUser();
		userMapper.dropUser(user.getId());
		List<Article> articles = articleMapper.findByUser(user.getId());
		for(Article a: articles) {
			articleMapper.delete(a.getId());
		}
	}

	public List<Article> findAll(Pagination pagination) {
		int count = articleMapper.count(pagination);
		pagination.setRecordCount(count);
		return articleMapper.findAllByBoard(pagination);
	}

	public void createArticle(Article article, int type, @RequestBody MultipartFile file, HttpServletRequest request) {
		article.setArt_u_id(UserService.getCurrentUser().getId());
		article.setArt_b_id(type);
		article.setContent(request.getParameter("content"));
		if (!file.isEmpty()) {
			int art_f_id = fileService.fileUpload(file);
			article.setArt_f_id(art_f_id);
			articleMapper.insert(article);
		}
		else {
			articleMapper.insertNoFile(article);
		}
		return;
	}

	public void editArticle(Article article, @RequestBody MultipartFile file, HttpServletRequest request) {
		article.setContent(request.getParameter("content"));
		if (!file.isEmpty()) {
			int art_f_id = fileService.fileUpload(file);
			article.setArt_f_id(art_f_id);
		}
		articleMapper.update(article);
	}

	public static User getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication instanceof MyAuthenticationProvider.MyAuthenticaion)
			return ((MyAuthenticationProvider.MyAuthenticaion) authentication).getUser();
		return null;
	}

	public static void setCurrentUser(User user) {
		((MyAuthenticationProvider.MyAuthenticaion) SecurityContextHolder.getContext().getAuthentication())
				.setUser(user);
	}
}
