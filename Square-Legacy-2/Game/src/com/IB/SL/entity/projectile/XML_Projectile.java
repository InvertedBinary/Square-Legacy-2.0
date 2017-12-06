package com.IB.SL.entity.projectile;

import java.awt.Rectangle;
import java.io.InputStream;
import java.util.concurrent.ThreadLocalRandom;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.IB.SL.Boot;
import com.IB.SL.entity.Entity;
import com.IB.SL.graphics.Screen;
import com.IB.SL.graphics.Sprite;

public class XML_Projectile extends Projectile {
	
	private static final long serialVersionUID = 1L;
	public String XML_String = "";
	public int RoF = 0;
	
	public XML_Projectile(double x, double y, double angle, String XML) {
		this.init(x, y, angle);
		readXML(XML);
	}
	
	public XML_Projectile(double x, double y, double angle, String XML, Entity origin) {
		this.init(x, y, angle);
		this.origin = origin;
		readXML(XML);
	}
	
	public void init(double x, double y, double angle) {
		this.sprite = Sprite.WizardProjectile;
		this.xOrigin = x;
		this.yOrigin = y;
		this.x = x;
		this.y = y;
		this.angle = angle;
		
	}
	
	public void readXML(String path) {
		this.XML_String = path;
		try {
		InputStream fXmlFile = getClass().getResourceAsStream(path);
		DocumentBuilderFactory dbFac = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFac.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		
		doc.getDocumentElement().normalize();
		
		System.out.println("ROOT: " + doc.getDocumentElement().getNodeName());
		
		initProjectile(doc);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void initProjectile(Document doc) {
		NodeList nList = doc.getElementsByTagName("projectile");
		System.out.println("----------------------------");
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			System.out.println("\nCurrent Element :" + nNode.getNodeName());
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				try {
					setProperties(eElement);
					} catch (Exception e) {
						e.printStackTrace();
				}
			}
		}
	}

	public void setProperties(Element eElement) {
		try {
		this.id = Integer.parseInt(eElement.getAttribute("id"));
		
		this.name = (eElement.getElementsByTagName("name").item(0).getTextContent());
		this.damage = Double.parseDouble(eElement.getElementsByTagName("damage").item(0).getTextContent());
		this.speed = Double.parseDouble(eElement.getElementsByTagName("speed").item(0).getTextContent());

		this.range = ThreadLocalRandom.current().nextInt(Integer.parseInt(((Element)eElement.getElementsByTagName("range").item(0)).getAttribute("min")), Integer.parseInt(((Element)eElement.getElementsByTagName("range").item(0)).getAttribute("max")) + 1);
		
		this.MovementStyle = ((Element)eElement.getElementsByTagName("movement").item(0)).getAttribute("shape");
		this.initial_rotation = Boolean.parseBoolean(((Element)eElement.getElementsByTagName("movement").item(0)).getAttribute("rot"));
		this.RoF = Integer.parseInt(((Element)eElement.getElementsByTagName("movement").item(0)).getAttribute("rof"));

		this.xOffset = Integer.parseInt(((Element)eElement.getElementsByTagName("sprite").item(0)).getAttribute("xOffset"));
		this.yOffset = Integer.parseInt(((Element)eElement.getElementsByTagName("sprite").item(0)).getAttribute("yOffset"));
		//Field field = Sprite.class.getField((((Element)eElement.getElementsByTagName("sprite").item(0)).getAttribute("Arrow")));
		//this.sprite = (Sprite) field.get(field.getType());
		
		//this.master_sprite = sprite;
		
		r = new java.awt.Rectangle((int)x, (int)y, sprite.getWidth(), sprite.getHeight());
		nx += speed * Math.cos(angle);
		ny += speed * Math.sin(angle);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		time++;
		
		this.moveArc();
	}
	
	public void render(Screen screen) {
		screen.renderProjectile((int)x + xOffset,(int)y + yOffset, this);
		if (Boot.get().devModeOn) screen.drawRect((int)x - 3, (int)y - 9, 5, 5, 0x0093FF, true);
	}
}
