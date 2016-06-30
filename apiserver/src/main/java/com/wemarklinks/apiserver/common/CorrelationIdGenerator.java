package com.wemarklinks.apiserver.common;

import org.springframework.stereotype.Component;
import org.springframework.util.SimpleIdGenerator;

@Component("IdGenerator")
public class CorrelationIdGenerator extends SimpleIdGenerator {

}
