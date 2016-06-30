package com.wemarklinks.factory.data.service;

import java.util.List;

import com.wemarklinks.factory.data.model.CodeInfo;

public interface ICodeInfoService {
	List<CodeInfo> getUnusedCodeInfoList(int maxNum);

	CodeInfo getCodeInfoById(long code);

	void insertCodeInfo(CodeInfo info);

	void updateCodeInfo(CodeInfo info);
}
