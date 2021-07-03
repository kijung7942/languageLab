package kr.or.ddit.designpattern.adapter;

// wrapper클래스라고 생각하면 됨.
public class Adapter implements Target {

	private Adaptee adaptee;

	//기본 생성자는 넣지 않음(adaptee를 무조건 받아야하기 때문에)
	public Adapter(Adaptee adaptee) {
		super();
		this.adaptee = adaptee;
	}

	@Override
	public void request() {
		adaptee.specificRequest();
	}
	
}
