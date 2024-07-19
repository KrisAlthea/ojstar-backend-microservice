package com.ryanverse.ojstarbackendquestionservice.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ryanverse.ojstarbackendcommon.common.ErrorCode;
import com.ryanverse.ojstarbackendcommon.constant.CommonConstant;
import com.ryanverse.ojstarbackendcommon.exception.BusinessException;
import com.ryanverse.ojstarbackendcommon.utils.SqlUtils;
import com.ryanverse.ojstarbackendmodel.dto.questionsubmit.QuestionSubmitAddRequest;
import com.ryanverse.ojstarbackendmodel.dto.questionsubmit.QuestionSubmitQueryRequest;
import com.ryanverse.ojstarbackendmodel.entity.Question;
import com.ryanverse.ojstarbackendmodel.entity.QuestionSubmit;
import com.ryanverse.ojstarbackendmodel.entity.User;
import com.ryanverse.ojstarbackendmodel.enums.QuestionSubmitLanguageEnum;
import com.ryanverse.ojstarbackendmodel.enums.QuestionSubmitStatusEnum;
import com.ryanverse.ojstarbackendmodel.vo.QuestionSubmitVO;
import com.ryanverse.ojstarbackendquestionservice.mapper.QuestionSubmitMapper;
import com.ryanverse.ojstarbackendquestionservice.rabbitmq.MyMessageProducer;
import com.ryanverse.ojstarbackendquestionservice.service.QuestionService;
import com.ryanverse.ojstarbackendquestionservice.service.QuestionSubmitService;
import com.ryanverse.ojstarbackendserviceclient.service.JudgeFeignClient;
import com.ryanverse.ojstarbackendserviceclient.service.UserFeignClient;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * @author Haoran
 * @description 针对表【question_submit(题目提交)】的数据库操作Service实现
 * @createDate 2024-07-07 00:57:44
 */
@Service
public class QuestionSubmitServiceImpl extends ServiceImpl<QuestionSubmitMapper, QuestionSubmit>
		implements QuestionSubmitService {

	@Resource
	private QuestionService questionService;

	@Resource
	private UserFeignClient userFeignClient;

	@Resource
	@Lazy
	private JudgeFeignClient judgeFeignClient;

	@Resource
	private MyMessageProducer myMessageProducer;

	/**
	 * 提交题目
	 *
	 * @param questionSubmitAddRequest 提交题目请求
	 * @param loginUser                登录用户
	 * @return 提交题目ID
	 */
	@Override
	public long doQuestionSubmit (QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser) {
		// 语言是否合法
		String language = questionSubmitAddRequest.getLanguage();
		if (QuestionSubmitLanguageEnum.getEnumByValue(language) == null) {
			throw new BusinessException(ErrorCode.PARAMS_ERROR, "语言不合法");
		}
		Long questionId = questionSubmitAddRequest.getQuestionId();
		// 判断实体是否存在，根据类别获取实体
		Question question = questionService.getById(questionId);
		if (question == null) {
			throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "题目不存在");
		}
		// 是否已提交题目
		long userId = loginUser.getId();
		// 每个用户串行提交题目
		QuestionSubmit questionSubmit = new QuestionSubmit();
		questionSubmit.setUserId(userId);
		questionSubmit.setQuestionId(questionId);
		questionSubmit.setLanguage(language);
		questionSubmit.setCode(questionSubmitAddRequest.getCode());
		// 设置初始状态
		questionSubmit.setStatus(QuestionSubmitStatusEnum.PENDING.getValue());
		questionSubmit.setJudgeInfo("{}");
		boolean save = this.save(questionSubmit);
		if (save) {
			// 判题
			Long questionSubmitId = questionSubmit.getId();
			// 发送消息
			myMessageProducer.sendMessage("code_exchange", "my_routingKey", String.valueOf(questionSubmitId));
			//CompletableFuture.runAsync(() -> judgeFeignClient.doJudge(questionSubmitId));
			return questionSubmitId;
		} else {
			throw new BusinessException(ErrorCode.SYSTEM_ERROR);
		}
	}

	/**
	 * 获取查询包装类
	 *
	 * @param questionSubmitQueryRequest
	 * @return
	 */
	@Override
	public QueryWrapper<QuestionSubmit> getQueryWrapper (QuestionSubmitQueryRequest questionSubmitQueryRequest) {
		QueryWrapper<QuestionSubmit> queryWrapper = new QueryWrapper<>();
		if (questionSubmitQueryRequest == null) {
			return queryWrapper;
		}
		String language = questionSubmitQueryRequest.getLanguage();
		Integer status = questionSubmitQueryRequest.getStatus();
		Long questionId = questionSubmitQueryRequest.getQuestionId();
		Long userId = questionSubmitQueryRequest.getUserId();
		String sortField = questionSubmitQueryRequest.getSortField();
		String sortOrder = questionSubmitQueryRequest.getSortOrder();

		// 拼接查询条件
		queryWrapper.eq(StringUtils.isNotBlank(language), "language", language);
		queryWrapper.eq(ObjectUtils.isNotEmpty(userId), "userId", userId);
		queryWrapper.eq(ObjectUtils.isNotEmpty(questionId), "questionId", questionId);
		queryWrapper.eq(QuestionSubmitStatusEnum.getEnumByValue(status) != null, "status", status);
		queryWrapper.eq("isDelete", false);
		// 排序
		queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC),
				sortField);

		return queryWrapper;
	}

	@Override
	public QuestionSubmitVO getQuestionSubmitVO (QuestionSubmit questionSubmit, User loginUser) {
		QuestionSubmitVO questionSubmitVO = QuestionSubmitVO.objToVo(questionSubmit);
		// 脱敏, 仅本人和管理员能看见提交的代码
		long userId = loginUser.getId();
		// 处理脱敏
		if (userId != questionSubmit.getUserId() && !userFeignClient.isAdmin(loginUser)) {
			questionSubmitVO.setCode("******");
		}
		return questionSubmitVO;
	}

	@Override
	public Page<QuestionSubmitVO> getQuestionSubmitVOPage (Page<QuestionSubmit> questionSubmitPage, User loginUser) {
		List<QuestionSubmit> questionSubmitList = questionSubmitPage.getRecords();
		Page<QuestionSubmitVO> questionSubmitVOPage = new Page<>(questionSubmitPage.getCurrent(), questionSubmitPage.getSize(), questionSubmitPage.getTotal());
		if (CollUtil.isEmpty(questionSubmitList)) {
			return questionSubmitVOPage;
		}
		List<QuestionSubmitVO> questionSubmitVOList = questionSubmitList.stream()
				.map(questionSubmit -> getQuestionSubmitVO(questionSubmit, loginUser))
				.collect(Collectors.toList());
		questionSubmitVOPage.setRecords(questionSubmitVOList);
		return questionSubmitVOPage;
	}


}




