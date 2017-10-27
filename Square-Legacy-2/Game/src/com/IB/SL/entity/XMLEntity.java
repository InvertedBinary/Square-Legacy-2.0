package com.IB.SL.entity;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.IB.SL.graphics.AnimatedSprite;
import com.IB.SL.graphics.Screen;
import com.IB.SL.graphics.SpriteSheet;

public class XMLEntity extends Entity {
	
	transient private AnimatedSprite down = new AnimatedSprite(SpriteSheet.zombie_down, 16, 16, 3);
	transient private AnimatedSprite up = new AnimatedSprite(SpriteSheet.zombie_up, 16, 16, 3);
	transient private AnimatedSprite left = new AnimatedSprite(SpriteSheet.zombie_left, 16, 16, 2);
	transient private AnimatedSprite right = new AnimatedSprite(SpriteSheet.zombie_right, 16, 16, 2);

	transient private AnimatedSprite animSprite = down;

	public XMLEntity(String XML) {
		XML = "/XML/Entities/Test_Zombie.xml";
		readXML(XML);
	}
	
	public XMLEntity(int x, int y, String XML) {
		XML = "/XML/Entities/Test_Zombie.xml";
		readXML(XML);
		this.x = x * 16;
		this.y = y * 16;
	}
	
	public void readXML(String path) {
		try {
		InputStream fXmlFile = getClass().getResourceAsStream(path);
		DocumentBuilderFactory dbFac = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFac.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		
		doc.getDocumentElement().normalize();
		
		System.out.println("ROOT: " + doc.getDocumentElement().getNodeName());
		
		initMob(doc);
		initSprite(doc);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void initMob(Document doc) {
		NodeList nList = doc.getElementsByTagName("mob");
		System.out.println("----------------------------");
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			System.out.println("\nCurrent Element :" + nNode.getNodeName());
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;

			this.id = Integer.parseInt(eElement.getAttribute("id"));
			this.name = (eElement.getElementsByTagName("name").item(0).getTextContent());
			this.speed = Double.parseDouble(eElement.getElementsByTagName("speed").item(0).getTextContent());
			this.maxhealth = Double.parseDouble(eElement.getElementsByTagName("maxhealth").item(0).getTextContent());
			this.mobhealth = maxhealth;
			this.rarity = Integer.parseInt(eElement.getElementsByTagName("rarity").item(0).getTextContent());
			this.Exp = Integer.parseInt(eElement.getElementsByTagName("exp").item(0).getTextContent());
			}
		}
	}
	
	public void initSprite(Document doc) {
		NodeList nList = doc.getElementsByTagName("sprite");
		System.out.println("----------------------------");
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			System.out.println("\nCurrent Element :" + nNode.getNodeName());
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				/*Element xml_down = (Element) eElement.getElementsByTagName("down");
				Element xml_up = (Element) eElement.getElementsByTagName("up");
				Element xml_left = (Element) eElement.getElementsByTagName("left");
				Element xml_right = (Element) eElement.getElementsByTagName("right");

				down = new AnimatedSprite(SpriteSheet.zombie_down, Integer.parseInt(xml_down.getAttribute("width")), Integer.parseInt(xml_down.getAttribute("height")), Integer.parseInt(xml_down.getAttribute("length")));
				up = new AnimatedSprite(SpriteSheet.zombie_down, Integer.parseInt(xml_up.getAttribute("width")), Integer.parseInt(xml_up.getAttribute("height")), Integer.parseInt(xml_up.getAttribute("length")));
				left = new AnimatedSprite(SpriteSheet.zombie_down, Integer.parseInt(xml_left.getAttribute("width")), Integer.parseInt(xml_left.getAttribute("height")), Integer.parseInt(xml_left.getAttribute("length")));
				right = new AnimatedSprite(SpriteSheet.zombie_down, Integer.parseInt(xml_right.getAttribute("width")), Integer.parseInt(xml_right.getAttribute("height")), Integer.parseInt(xml_right.getAttribute("length")));
*/
				
				
			}
		}
	}
	
	public void update() {
		
	}
	
	public void render(Screen screen) {
		sprite = animSprite.getSprite();
		xOffset = -8;
		yOffset = -15;
		//if (this.mobhealth < this.maxhealth) screen.renderSprite((int) x - 16, (int)y - 24, gui.renderMobHealthExperiment(this, 20), true);
		//gui.renderName(screen, "Zombie", (int)x - 14, (int)y- 25, -4, true, true);
		sprite = animSprite.getSprite();
		screen.renderMobSpriteUniversal((int) (x + xOffset), (int) (y + yOffset), sprite);
	
	}
}
