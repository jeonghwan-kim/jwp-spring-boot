package net.slipp.web;

import net.slipp.UnAuthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.slipp.domain.Question;
import net.slipp.domain.QuestionRepository;
import net.slipp.domain.User;
import net.slipp.security.LoginUser;

import javax.annotation.Resource;

@Controller
@RequestMapping("/questions")
public class QuestionController {
	private static final Logger log = LoggerFactory.getLogger(QuestionController.class);

	@Autowired
	private QuestionRepository questionRepository;

	@GetMapping("/{id}/form")
	public String updateForm(@LoginUser User loginUser, @PathVariable long id, Model model) {
		Question q = questionRepository.findOne(id);
		if (!q.isOwner(loginUser)) {
			throw new UnAuthorizedException("You're required Login!");
		}

		model.addAttribute("question", q);
		return "/qna/update_form";
	};

	@GetMapping("/form")
	public String form(@LoginUser User loginUser) {
		return "/qna/form";
	}
	
	@PostMapping("")
	public String create(@LoginUser User loginUser, Question question) {
		question.writeBy(loginUser);
		log.debug("question : {}", question);
		questionRepository.save(question);
		return "redirect:/";
	}
}
