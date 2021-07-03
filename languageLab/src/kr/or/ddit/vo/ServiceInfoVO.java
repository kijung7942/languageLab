package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="serviceInfo")
public class ServiceInfoVO implements Serializable{
	
	private List<MenuVO> menuList;

	public List<MenuVO> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<MenuVO> menuList) {
		this.menuList = menuList;
	}

	@Override
	public String toString() {
		return "ServiceInfoVO [menuList=" + menuList + "]";
	}

}
