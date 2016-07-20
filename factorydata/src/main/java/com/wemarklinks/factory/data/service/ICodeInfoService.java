package com.wemarklinks.factory.data.service;

import java.util.List;

import com.wemarklinks.factory.data.model.CodeInfo;

public interface ICodeInfoService {
	List<CodeInfo> getUnusedCodeInfoList(int maxNum);

	void insertCodeInfo(CodeInfo info);

	void updateCodeInfo(CodeInfo info);

	CodeInfo getCodeInfoById(String category, String batch, String code);
}
