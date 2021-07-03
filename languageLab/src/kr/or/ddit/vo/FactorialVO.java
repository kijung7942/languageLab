package kr.or.ddit.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@XmlRootElement(name="result")
@XmlAccessorType(XmlAccessType.NONE) // FIELD : 전역변수가 있으면 포함 // PROPERTY: 자바빈 규약에 따라 getter나 setter가 있을때 포함 //Public: public인 경우만 포함 // none: 어노테이션이 있는 경우만 포함
public class FactorialVO implements Serializable{
	@XmlElement
	private int left;
	@JsonIgnoreProperties
	private final char SIGN = '!';
	private int result;
	private String expression;
	
	
	public FactorialVO() {
		super();
	}

	public FactorialVO(int left) {
		super();
		this.left = left;
	}
	
	public int factorial(int left) {
		if(left <= 0) throw new IllegalArgumentException("음수는 연산 불가");
		if(left == 1) {
			return 1;
		}else {
			return left * factorial(left-1);
		}
	}

	public int getLeft() {
		return left;
	}

	public void setLeft(int left) {
		this.left = left;
	}

	public int getResult() {
		return factorial(left);
	}

	public void setResult(int result) {
		this.result = result;
	}
	
	@XmlElement
	public String getExpression() {
		return String.format("%d%c = %d",left,SIGN,getResult());
	}
	
	public void setExpression(String expression) {
		this.expression = expression;
	}
	
//	public char getSIGN() {
//		return SIGN;
//	}

	@Override
	public String toString() {
		return "FactorialVO [left=" + left + ", SIGN=" + SIGN + ",result=" + getResult() + ", expression=" + getExpression() + "]";
	}

	
	
	
}
