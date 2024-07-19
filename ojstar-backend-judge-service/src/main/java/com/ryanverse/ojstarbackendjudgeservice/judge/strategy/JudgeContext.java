package com.ryanverse.ojstarbackendjudgeservice.judge.strategy;

import com.ryanverse.ojstarbackendmodel.codesandbox.JudgeInfo;
import com.ryanverse.ojstarbackendmodel.dto.question.JudgeCase;
import com.ryanverse.ojstarbackendmodel.entity.Question;
import com.ryanverse.ojstarbackendmodel.entity.QuestionSubmit;
import lombok.Data;

import java.util.List;

/**
 * ClassName: JudgeContext
 * Description: 判题上下文(用于定义在策略中传递的参数)
 *
 * @Author Haoran
 * @Create 2024/7/12 14:38
 * @Version 1.0
 */
@Data
public class JudgeContext {
	private JudgeInfo judgeInfo;

	private List<String> inputList;

	private List<String> outputList;

	private List<JudgeCase> judgeCaseList;

	private Question question;

	private QuestionSubmit questionSubmit;
}
