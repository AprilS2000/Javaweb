package session04.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test2 {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-apo.xml");
		Calc calc = ctx.getBean("calcImpl", CalcImpl.class);
		System.out.println(calc.add(20, 10));
		System.out.println(calc.div(20, 10));

	}

}
