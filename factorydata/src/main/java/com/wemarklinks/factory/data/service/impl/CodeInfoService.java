package com.wemarklinks.factory.data.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.wemarklinks.factory.data.dao.CodeInfoMapper;
import com.wemarklinks.factory.data.model.CodeInfo;
import com.wemarklinks.factory.data.service.ICodeInfoService;

@Transactional
public class CodeInfoService implements ICodeInfoService {

	@Autowired
	private CodeInfoMapper codeInfoMapper;

	@Override
	public List<CodeInfo> getUnusedCodeInfoList(int maxNum) {
		// TODO Auto-generated method stub
		return null;
	}

}