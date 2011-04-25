package com.google.resting.transform.impl.atom;

import java.util.List;
import java.util.Map;

/**
 * @author lakshmipriya-p
 *
 */
@XmlInfo
public class AtomElement {

	private String xmlTag;
	private String xmlText;
	private String namespace;
	private Map<String, String> attributeValuePair;
	private List<AtomElement> children;
	
	protected AtomElement(String xmlTag) {
		this.xmlTag = xmlTag;
	}
	
	protected AtomElement(String xmlTag, String namespace) {
		this.xmlTag = xmlTag;
		this.namespace = namespace;
	}
	
	public String getTag() {
		return xmlTag;
	}
	
	public void setNamespace(String ns) {
		this.namespace = ns;
	}
	
	public String getNamespace() {
		return this.namespace;
	}
	
	public AtomElement addAttribute(String attribute, String value) {
		this.attributeValuePair.put(attribute, value);
		return this;
	}
	
	public AtomElement addChild(AtomElement e) {
		this.children.add(e);
		return this;
	}
	
	public AtomElement addText(String s) {
		this.xmlText = s;
		return this;
	}

	public String getXmlText() {
		return xmlText;
	}

	public List<AtomElement> getChildren() {
		return children;
	}
	
}
