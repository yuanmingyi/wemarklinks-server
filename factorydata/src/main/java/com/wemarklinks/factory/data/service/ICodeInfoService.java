package com.wemarklinks.factory.data.service;

import java.util.List;

import com.wemarklinks.factory.data.model.CodeInfo;

public interface ICodeInfoService {
	List<CodeInfo> getUnusedCodeInfoList(int maxNum);
}
