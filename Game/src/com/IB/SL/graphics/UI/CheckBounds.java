package com.IB.SL.graphics.UI;

import java.io.Serializable;

import com.IB.SL.Boot;
import com.IB.SL.graphics.Screen;
import com.IB.SL.graphics.UI.components.TextBox;
import com.IB.SL.input.Mouse;

public class CheckBounds extends UI implements Serializable{
	private static final long serialVersionUID = 1L;

	public String desc = "";

	public String save1 = "(Open)";
	public String save2 = "(Open)";
	public String save3 = "(Open)";
	public String save4 = "(Open)";
	transient public TextBox name;

	public boolean checkBounds(int x, int y, int width, int height, boolean toScale, boolean temp) {
		if (toScale) {
		x *= Boot.scale;
		y *= Boot.scale;
		width *= Boot.scale;
		height *= Boot.scale;
		}
		
		if (Mouse.getX() < x + width && Mouse.getX() > x && Mouse.getY() < y + height && Mouse.getY() > y ) {
			return true;
		} else {
		return false;
		}
	}

	public boolean checkBounds(int x, int y, int width, int height, boolean toScale) {
		if (toScale) {
		x *= Boot.scale;
		y *= Boot.scale;
		width *= Boot.scale;
		height *= Boot.scale;
		}
		
		System.out.println("X: " + Screen.xo + "," + x);
		if (Screen.xo < x + width && Screen.xo > x && Screen.yo < y + height && Screen.yo > y ) {
			return true;
		} else {
		return false;
		}
	}	
	
}