package com.ryanverse.ojstarbackendquestionservice.controller.inner;

import com.ryanverse.ojstarbackendmodel.entity.Question;
import com.ryanverse.ojstarbackendmodel.entity.QuestionSubmit;
import com.ryanverse.ojstarbackendquestionservice.service.QuestionService;
import com.ryanverse.ojstarbackendquestionservice.service.QuestionSubmitService;
import com.ryanverse.ojstarbackendserviceclient.service.QuestionFeignClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * ClassName: InnerQuestionController
 * Description: 仅供内部调用的用户控制器
 *
 * @Author Haoran
 * @Create 2024/7/18 16:11
 * @Version 1.0
 */
@RestController
@RequestMapping("/inner")
public class InnerQuestionController implements QuestionFeignClient {

	@Resource
	private QuestionService questionService;

	@Resource
	private QuestionSubmitService questionSubmitService;

	@Override
	@GetMapping("/get/id")
	public Question getQuestionById (@RequestParam("questionId") long questionId){
		return questionService.getById(questionId);
	}

	@Override
	@GetMapping("/submit/get/id")
	public QuestionSubmit getQuestionSubmitById (@RequestParam("questionSubmitId") long questionSubmitId){
		return questionSubmitService.getById(questionSubmitId);
	}

	@Override
	@PostMapping("/submit/update")
	public boolean updateQuestionSubmit (@RequestBody QuestionSubmit questionSubmit){
		return questionSubmitService.updateById(questionSubmit);
	}

}
