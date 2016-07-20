package com.wemarklinks.factory.data.service.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wemarklinks.factory.data.dao.CodeInfoMapper;
import com.wemarklinks.factory.data.model.CodeInfo;
import com.wemarklinks.factory.data.model.CodeInfoExample;
import com.wemarklinks.factory.data.service.ICodeInfoService;

@Service
public class CodeInfoService implements ICodeInfoService {

	@Autowired
	private CodeInfoMapper codeInfoMapper;

	@Override
	public List<CodeInfo> getUnusedCodeInfoList(int maxNum) {
		CodeInfoExample example = new CodeInfoExample();
		example.createCriteria().andStatusEqualTo(0);
		RowBounds rowBounds = new RowBounds(0, maxNum);
		return codeInfoMapper.selectByExampleWithRowbounds(example, rowBounds);
	}

	@Override
	public CodeInfo getCodeInfoById(String category, String batch, String code) {
		CodeInfoExample example = new CodeInfoExample();
		example.createCriteria().andCategoryEqualTo(category).andBatchEqualTo(batch).andCodeEqualTo(code);
		List<CodeInfo> codeInfoList = codeInfoMapper.selectByExample(example);
		return codeInfoList.get(0);
	}

	@Override
	public void insertCodeInfo(CodeInfo info) {
		codeInfoMapper.insertSelective(info);
	}

	@Override
	public void updateCodeInfo(CodeInfo info) {
		codeInfoMapper.updateByPrimaryKeySelective(info);
	}
}
