package net.skhu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import net.skhu.dto.Comment;
import net.skhu.mapper.CommentMapper;

@Controller
@RequestMapping("/embeded")
public class EmbededController {

	@Autowired CommentMapper commentMapper;

	@RequestMapping("comment")
	public String comment(Model model) {
		Comment newComment = new Comment();
		model.addAttribute("comments", commentMapper.findAllByArticle(1));
		model.addAttribute("newComment", newComment);
		return "embeded/comment";
	}

}
