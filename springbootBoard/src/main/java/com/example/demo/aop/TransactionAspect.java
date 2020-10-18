package com.example.demo.aop;

import java.util.Collections;
import java.util.List;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.interceptor.MatchAlwaysTransactionAttributeSource;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;

@Configuration
public class TransactionAspect {

	@Autowired
	private PlatformTransactionManager transactionManager;
	
	//포인트컷. 비즈니스 로직을 수행하는 모든 serviceImpl 클래스의 모든 메소드를 의미.
	private static final String EXPRESSION = "execution(* com.example.demo..service.*Impl.*(..))";
	
	@SuppressWarnings("deprecation")
	@Bean
	public TransactionInterceptor transactionAdvice() {
		
		//트랜잭셔에서 롤백을 수행하는 규칙. 자바에서 모든 예외는 exception 클래스를 상속받기 때문에 어떠한 예외가 발생하던 무조건 롤백이 수행됨. 
		List<RollbackRuleAttribute> rollbackRules = Collections.singletonList(new RollbackRuleAttribute(Exception.class));
		
		RuleBasedTransactionAttribute transactionAttribute = new RuleBasedTransactionAttribute();
		
		transactionAttribute.setRollbackRules(rollbackRules);
		transactionAttribute.setName("*");
		
		MatchAlwaysTransactionAttributeSource attributeSource = new MatchAlwaysTransactionAttributeSource();
		
		attributeSource.setTransactionAttribute(transactionAttribute);
		
		return new TransactionInterceptor(transactionManager, attributeSource);
	}

	@Bean
	public Advisor transactionAdvisor() {

		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		pointcut.setExpression(EXPRESSION);

		//AOP의 포인트컷을 설정.
		return new DefaultPointcutAdvisor(pointcut, transactionAdvice());
	}
}
