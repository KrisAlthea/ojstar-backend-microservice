package com.ryanverse.ojstarbackendserviceclient.service;

import com.ryanverse.ojstarbackendmodel.entity.Question;
import com.ryanverse.ojstarbackendmodel.entity.QuestionSubmit;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Haoran
 * @description 针对表【question(题目)】的数据库操作Service
 * @createDate 2024-07-07 00:56:57
 */
@FeignClient(name = "ojstar-backend-question-service", path = "/api/question/inner")
public interface QuestionFeignClient {

	@GetMapping("/get/id")
	Question getQuestionById (@RequestParam("questionId") long questionId);

	@GetMapping("/submit/get/id")
	QuestionSubmit getQuestionSubmitById (@RequestParam("questionSubmitId") long questionSubmitId);

	@PostMapping("/submit/update")
	boolean updateQuestionSubmit (@RequestBody QuestionSubmit questionSubmit);

}
