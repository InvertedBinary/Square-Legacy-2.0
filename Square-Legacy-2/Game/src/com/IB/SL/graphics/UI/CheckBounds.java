package com.IB.SL.graphics.UI;

import java.io.Serializable;

import com.IB.SL.Boot;
import com.IB.SL.graphics.Screen;
import com.IB.SL.graphics.UI.part.TextBox;
import com.IB.SL.input.Mouse;

@SuppressWarnings("static-access")
public class CheckBounds extends UI implements Serializable{
	
	
	public String desc = "";

	public String save1 = "(Open)";
	public String save2 = "(Open)";
	public String save3 = "(Open)";
	public String save4 = "(Open)";
	transient public TextBox name;
	transient public TextBox cmd;

	public boolean checkBounds(int x, int y, int width, int height, boolean toScale, boolean temp) {
		if (toScale) {
		x *= Boot.get().scale;
		y *= Boot.get().scale;
		width *= Boot.get().scale;
		height *= Boot.get().scale;
		}
		
		if (Mouse.getX() < x + width && Mouse.getX() > x && Mouse.getY() < y + height && Mouse.getY() > y ) {
			return true;
		} else {
		return false;
		}
	}

	public boolean checkBounds(int x, int y, int width, int height, boolean toScale) {
		if (toScale) {
		x *= Boot.get().scale;
		y *= Boot.get().scale;
		width *= Boot.get().scale;
		height *= Boot.get().scale;
		}
		
		System.out.println("X: " + Screen.xo + "," + x);
		if (Screen.xo < x + width && Screen.xo > x && Screen.yo < y + height && Screen.yo > y ) {
			return true;
		} else {
		return false;
		}
	}	
	
}