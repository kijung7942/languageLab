package kr.or.ddit.io;

import java.io.Serializable;

public class TestVO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int number;
	private String str;
	private transient String regno1; // 직렬화의 대상에서 제외하겠다
	
	
	
	public TestVO(int number, String str) {
		super();
		this.number = number;
		this.str = str;
	}

	
	public String getRegno1() {
		return regno1;
	}





	public void setRegno1(String regno1) {
		this.regno1 = regno1;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}


	@Override
	public String toString() {
		return "TestVO [number=" + number + ", str=" + str + ", regno1=" + regno1 + "]";
	}

	
}
