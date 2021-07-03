package kr.or.ddit.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author admin
 *
 */
@XmlRootElement(name="menu")
@XmlAccessorType(XmlAccessType.FIELD)
public class MenuVO implements Serializable{

	private String code;
	private String link;
	private String text;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	@Override
	public String toString() {
		return "MenuVO [code=" + code + ", link=" + link + ", text=" + text + "]";
	}

}
