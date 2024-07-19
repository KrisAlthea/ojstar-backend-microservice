package com.ryanverse.ojstarbackendjudgeservice.controller.inner;

import com.ryanverse.ojstarbackendjudgeservice.judge.JudgeService;
import com.ryanverse.ojstarbackendmodel.entity.QuestionSubmit;
import com.ryanverse.ojstarbackendserviceclient.service.JudgeFeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * ClassName: InnerJudgeController
 * Description:
 *
 * @Author Haoran
 * @Create 2024/7/18 16:24
 * @Version 1.0
 */
@RestController
@RequestMapping("/inner")
public class InnerJudgeController implements JudgeFeignClient {

	@Resource
	private JudgeService judgeService;

	/**
	 * 判题
	 *
	 * @param questionSubmitId 题目ID
	 * @return QuestionSubmit
	 */
	@Override
	@PostMapping("/do")
	public QuestionSubmit doJudge (@RequestParam("questionSubmitId") long questionSubmitId) {
		return judgeService.doJudge(questionSubmitId);
	}
}
