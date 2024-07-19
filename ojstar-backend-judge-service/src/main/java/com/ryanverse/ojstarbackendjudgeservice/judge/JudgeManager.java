package com.ryanverse.ojstarbackendjudgeservice.judge;

import com.ryanverse.ojstarbackendjudgeservice.judge.strategy.DefaultJudgeStrategyImpl;
import com.ryanverse.ojstarbackendjudgeservice.judge.strategy.JavaJudgeStrategyImpl;
import com.ryanverse.ojstarbackendjudgeservice.judge.strategy.JudgeContext;
import com.ryanverse.ojstarbackendjudgeservice.judge.strategy.JudgeStrategy;
import com.ryanverse.ojstarbackendmodel.codesandbox.JudgeInfo;
import com.ryanverse.ojstarbackendmodel.entity.QuestionSubmit;
import org.springframework.stereotype.Service;

/**
 * ClassName: JudgeManager
 * Description:
 *
 * @Author Haoran
 * @Create 2024/7/12 15:04
 * @Version 1.0
 */
@Service
public class JudgeManager {

	/**
	 * 执行判题
	 *
	 * @param judgeContext 判题上下文
	 * @return JudgeInfo
	 */
	JudgeInfo doJudge (JudgeContext judgeContext) {
		QuestionSubmit questionSubmit = judgeContext.getQuestionSubmit();
		String language = questionSubmit.getLanguage();
		JudgeStrategy judgeStrategy = new DefaultJudgeStrategyImpl();
		if ("java".equals(language)) {
			judgeStrategy = new JavaJudgeStrategyImpl();
		}
		return judgeStrategy.doJudge(judgeContext);
	}

}
