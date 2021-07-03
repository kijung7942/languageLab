package kr.or.ddit.reflection;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import kr.or.ddit.reflect.ReflectionTest;

public class ReflectionDesc {
	public static void main(String[] args) {
		// reflection : 인스턴스로부터 클래스를 찾아가는 작업
		// reflection은 불확실성을 담보로 한 작업이다.
		Object obj = ReflectionTest.getObject();
		System.out.println(obj);
		
		
		// PropertyDescriptor API를 사용하는 방법
		Class type = obj.getClass();
		Field[] fields = type.getDeclaredFields(); 
		for(Field fld : fields) {
			try {
				PropertyDescriptor pd = new PropertyDescriptor(fld.getName(), type);
				Method getter = pd.getReadMethod();
				Method setter = pd.getWriteMethod();
				
				System.out.printf("%s = %s \n",pd.getName(),getter.invoke(obj));
			} catch (IntrospectionException e) {
				// 자바빈 규약에 의해 만들어지지 않은 객체일때 발생하는 예외
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	
	public static void reflect1(Object obj) {
		// 원시적인 방법
		// 1. 타입을 알아낸다
		Class type = obj.getClass();
		System.out.println(type);
		// 2. 상태를 알아낸다(field : 일반적인 전역변수, property : 자바빈규약에 따른 전역변수)
//		Field[] fields = type.getFields(); // public인 전역변수만 가져온다.
		Field[] fields = type.getDeclaredFields(); 
		
		for(Field fld:fields) {
//			System.out.println(fld);

//			fld.setAccessible(true);
//			try {
//				System.out.printf("%s = %s \n", fld.getName(), fld.get(obj));
//			} catch (IllegalArgumentException | IllegalAccessException e) {
//				e.printStackTrace();
//			}
			
			// 위의 방법은 강제로 private을 풀어버리므로 위험하다 메서드를 찾는 방법
			String fldName = fld.getName();
			String getterName = "get" + fldName.substring(0,1).toUpperCase() + fldName.substring(1);
			try {
				Method getter =  type.getMethod(getterName);
				// 찾은 메서드(getter)를 통해 멤버변수들을 찾아내자(자바빈 규약에 의해 만들어졌다고 가정)
				System.out.printf("%s = %s \n", fld.getName(), getter.invoke(obj));
			} catch (NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
	}
	
}
