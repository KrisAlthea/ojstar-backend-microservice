package com.ryanverse.ojstarbackendjudgeservice.judge.strategy;

import com.ryanverse.ojstarbackendmodel.codesandbox.JudgeInfo;

/**
 * ClassName: JudgeStrategy
 * Description: 判题策略
 *
 * @Author Haoran
 * @Create 2024/7/12 14:37
 * @Version 1.0
 */
public interface JudgeStrategy {
	/**
	 * 判题
	 *
	 * @param judgeContext 判题上下文
	 * @return JudgeInfo
	 */
	JudgeInfo doJudge (JudgeContext judgeContext);
}
