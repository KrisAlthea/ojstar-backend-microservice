package com.ryanverse.ojstarbackendjudgeservice.judge.codesandbox.impl;

import com.ryanverse.ojstarbackendjudgeservice.judge.codesandbox.CodeSandbox;
import com.ryanverse.ojstarbackendmodel.codesandbox.ExecuteCodeRequest;
import com.ryanverse.ojstarbackendmodel.codesandbox.ExecuteCodeResponse;
import com.ryanverse.ojstarbackendmodel.codesandbox.JudgeInfo;
import com.ryanverse.ojstarbackendmodel.enums.JudgeInfoMessageEnum;
import com.ryanverse.ojstarbackendmodel.enums.QuestionSubmitStatusEnum;

import java.util.List;

/**
 * ClassName: ExampleCodeSandboxImpl
 * Description:
 *
 * @Author Haoran
 * @Create 2024/7/11 19:58
 * @Version 1.0
 */
public class ExampleCodeSandboxImpl implements CodeSandbox {
	@Override
	public ExecuteCodeResponse executeCode (ExecuteCodeRequest executeCodeRequest) {
		List<String> inputList = executeCodeRequest.getInputList();
		ExecuteCodeResponse executeCodeResponse = new ExecuteCodeResponse();
		executeCodeResponse.setOutputList(inputList);
		executeCodeResponse.setMessage("测试执行成功");
		executeCodeResponse.setStatus(QuestionSubmitStatusEnum.SUCCESS.getValue());
		JudgeInfo judgeInfo = new JudgeInfo();
		judgeInfo.setMessage(JudgeInfoMessageEnum.ACCEPTED.getText());
		judgeInfo.setMemory(100L);
		judgeInfo.setTime(100L);
		executeCodeResponse.setJudgeInfo(judgeInfo);
		return executeCodeResponse;
	}
}
