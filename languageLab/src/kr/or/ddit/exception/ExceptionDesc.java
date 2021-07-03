package kr.or.ddit.exception;

import java.io.IOException;

/**
 *	에러?  예외? : 예상하지 않았던 비정상 상황.(둘 다 Throwable의 자식인 객체이다.)
 * 		Error : 개발자가 처리하지 않는 비정상 상황 (ex: compile error)
 *		
 *
 *		Exception : 개발자가 처리할 수 있는 비정상 상황(RuntimeException도 Exception의 자손이다)
 *			1. checked exception(Exception)	: 예외가 던져지면(throw) 반드시 처리(try-catch)해야 하는 예외
 *				- IOException, SQLException 등
 *			2. unchecked exception(RuntimeException) : 예외가 던져지고, 명시적인 예외 처리 코드가 없으면 자동으로 VM에게 제어가 전달되는 예외.
 *				- NullPointerException 등
 *			3. 처리 방법
 *				1. 적극적인 처리 (try-catch-finally 블럭 = try-resource 블럭)
 *					try{
 *						// 예외 발생 가능 코드
 *					}catch(try블럭내에서 발생할 수 있는 예외 타입 e){
 *						// 예외 처리 코드(발생 예외를 변경 가능, 발생한 예외를 없애는 것도 가능.)
 *					} finally{
 *						// 예외 발생 여부와 무관하게 처리하는 구문(자원의 해제)
 *					}
 *				2. 호출자에게 전달 : throws
 *			4. Custom exception 정의
 *			: 예외의 특성을 결정하고, 처리 정책에 따라 상위가 결정됨.(checked : Exception 상속, unchecked : RuntimeException 상속)
 *				throw new CustomException();
 */

public class ExceptionDesc {

	public static void main(String[] args) throws RuntimeException {
		try {
				test1();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	private static void test1() throws RuntimeException {
			try {
					if(1==1) {
					throw new IOException("강제 발생 예외");
				}
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
	}
}
