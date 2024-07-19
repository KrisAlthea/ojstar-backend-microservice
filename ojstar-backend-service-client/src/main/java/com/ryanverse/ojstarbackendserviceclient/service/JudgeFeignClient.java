package com.ryanverse.ojstarbackendserviceclient.service;


import com.ryanverse.ojstarbackendmodel.entity.QuestionSubmit;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * ClassName: JudgeFeignClient
 * Description: 判题服务
 *
 * @Author Haoran
 * @Create 2024/7/11 21:15
 * @Version 1.0
 */
@FeignClient(name = "ojstar-backend-judge-service", path = "/api/judge/inner")
public interface JudgeFeignClient {

	/**
	 * 判题
	 *
	 * @param questionSubmitId 题目ID
	 * @return QuestionSubmit
	 */
	@PostMapping("/do")
	QuestionSubmit doJudge (@RequestParam("questionSubmitId") long questionSubmitId);
}
