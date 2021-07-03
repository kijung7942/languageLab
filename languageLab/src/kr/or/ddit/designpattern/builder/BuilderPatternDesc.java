package kr.or.ddit.designpattern.builder;

/**
 * Builder Pattern 적용 단계 => lombok의 @Build
 * 1. 모든 프로퍼티를 받는 생성자 정의(private으로 묶기)
 * 2. inner class의 형태로 Builder class 정의
 * 		1) build의 대상이 되는 outer class의 멤버변수와 동일한 프로퍼티 정의
 * 		2) 프로퍼티명과 똑같은 메서드를 만들어준다(setter의 개념) : return this 가 들어가 있다.
 * 		3) build 메소드내에서 빌드 대상인 인스턴스 생성
 * 3. Builder 인스턴스를 반환하는 getBuilder 정의.
 * 
 *  
 * @author admin
 *
 */
public class BuilderPatternDesc {

	
	public static void main(String[] args) {
		// 자바빈 규약에 따르는 생성 방법
//		TestVO vo = new TestVO();
//		vo.setProp1("프로퍼티1");
//		vo.setProp2("프로퍼티2");
		
		//생성자를 이용하여 코드를 줄이는 방법 = > 점층적인 생성 방식
		// 단점 -> 모든 멤버변수에 대한 생성자를 오버로딩 해야한다.
		//        타입이 같은 멤버변수의 경우 중복된 생성자가 불가하다
//		TestVO vo2 = new TestVO("프로퍼티1","프로퍼티2");
		
		// Lombok이 사용하는 디자인 패턴
		// 위의 단점을 극복하기 위해 Builder라는 생성 디자인 패턴을 사용
		TestVO vo = TestVO.getBuilder()
//				.prop1("프로퍼티1") // 프로퍼티2만 넣고 싶다면 아래의 메서드 하나만 호출하면 됨.
				.prop2("프로퍼티2")
				.build();
	}
	
	// 테스트를 위한 이너클래스
	public static class TestVO{
		private String prop1;
		private String prop2;

		public static TestVOBuilder getBuilder() {
			return new TestVOBuilder();
		}
		
//		private TestVO() {
//			super();
//		}
//		
//		private TestVO(String prop1) {
//			super();
//			this.prop1 = prop1;
//		}

		// TestVO를 만들기 위한 빌더 이너 클래스
		public static class TestVOBuilder{
			
			// biuld의 대상이 되는 클래스의 변수와 똑같은 변수를 선언해주기
			private String prop1;
			private String prop2;

			// 가지고 있는 변수와 똑같은 이름의 메서드 만들기 =>setter 처럼 사용 
			// 단, return Type이 자기 자신이다
			public TestVOBuilder prop1(String prop1) {
				this.prop1 = prop1;
				return this; // this를 넘겨주기 때문에 chain 구조로 사용 가능하다.
			}

			public TestVOBuilder prop2(String prop2) {
				this.prop2 = prop2;
				return this; 
			}
			
			// build 메서드 만들기
			public TestVO build() {
				return new TestVO(prop1,prop2);
			}
		}
		
		private TestVO(String prop1, String prop2) {
			super();
			this.prop1 = prop1;
			this.prop2 = prop2;
		}
		public String getProp1() {
			return prop1;
		}
		public void setProp1(String prop1) {
			this.prop1 = prop1;
		}
		public String getProp2() {
			return prop2;
		}
		public void setProp2(String prop2) {
			this.prop2 = prop2;
		}
	
	}
}
