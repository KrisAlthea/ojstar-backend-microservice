package com.ryanverse.ojstarbackendjudgeservice.judge;

import com.ryanverse.ojstarbackendmodel.entity.QuestionSubmit;

/**
 * ClassName: JudgeFeignClient
 * Description: 判题服务
 *
 * @Author Haoran
 * @Create 2024/7/11 21:15
 * @Version 1.0
 */
public interface JudgeService {

	/**
	 * 判题
	 *
	 * @param questionSubmitId 题目ID
	 * @return QuestionSubmit
	 */
	QuestionSubmit doJudge (long questionSubmitId);
}
