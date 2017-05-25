package net.slipp.web;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import net.slipp.domain.Question;
import net.slipp.domain.QuestionRepository;
import net.slipp.domain.User;
import net.slipp.domain.UserRepository;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoRestTemplateCustomizer;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

import support.test.BasicAuthAcceptanceTest;
import support.test.HtmlFormDataBuilder;

public class QuestionControllerTest extends BasicAuthAcceptanceTest {
    private static final Logger log = LoggerFactory.getLogger(QuestionController.class);

	@Autowired
	private QuestionRepository questionRepository;

    @Autowired
	private UserRepository userRepository;

	@Test
	public void createForm_logout() throws Exception {
		ResponseEntity<String> response = template.getForEntity("/questions/form", String.class);
		assertThat(response.getStatusCode(), is(HttpStatus.UNAUTHORIZED));	
	}
	
	@Test
	public void createForm_login() throws Exception {
		ResponseEntity<String> response = basicAuthTemplate.getForEntity("/questions/form", String.class);
		assertThat(response.getStatusCode(), is(HttpStatus.OK));
	}

	@Test
	public void updateForm_내가_쓴_글() throws Exception {
		Question q = new Question()
				.setTitle("자꾸 헷갈리게 하지마")
				.setContents("왜 그러는 거냐고?")
				.writeBy(loginUser);

		Question savedQ = questionRepository.save(q);
		ResponseEntity<String> res =
				basicAuthTemplate.getForEntity("/questions/"+savedQ.getId()+"/form", String.class);

		assertThat(res.getStatusCode(), is(HttpStatus.OK));

		log.debug(res.getBody());
        assertTrue(res.getBody().contains(savedQ.getTitle()));
        assertTrue(res.getBody().contains(savedQ.getContents()));

    }

	@Test
    public void updateForm_남이_쓴_글() throws Exception {
        User writer = userRepository.findByUserId("chris").get();

        Question q = new Question()
                .setTitle("자꾸 헷갈리게 하지마")
                .setContents("왜 그러는 거냐고?")
                .writeBy(writer);

        Question savedQ = questionRepository.save(q);
        ResponseEntity<String> res =
                basicAuthTemplate.getForEntity("/questions/"+savedQ.getId()+"/form", String.class);

        assertThat(res.getStatusCode(), is(HttpStatus.UNAUTHORIZED));
    }
	
	@Test
	public void create() throws Exception {
		HttpEntity<MultiValueMap<String, Object>> request = HtmlFormDataBuilder
				.urlEncodedForm()
				.addParameter("title", "TDD는 의미있는 활동인가?")
				.addParameter("contents", "당근 엄청 의미있는 활동이고 말고..")
				.build();

		ResponseEntity<String> response = basicAuthTemplate.postForEntity("/questions", request, String.class);
        
		assertThat(response.getStatusCode(), is(HttpStatus.FOUND));
	}
}
