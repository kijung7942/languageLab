package kr.or.ddit.designpattern;

import java.util.Arrays;
import java.util.List;

import kr.or.ddit.designpattern.templetmethod.ConcreteClass1;
import kr.or.ddit.designpattern.templetmethod.ConcreteClass2;
import kr.or.ddit.designpattern.templetmethod.TempleteClass;

public class TemplateMethodPatternTest {
	public static void main(String[] args) {
		List<TempleteClass> list =
				Arrays.asList(new ConcreteClass1(), new ConcreteClass2());
		for(TempleteClass tmp : list) {
			tmp.template();
		}
	}
	
}
