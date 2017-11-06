package com.IB.SL.graphics.UI.menu;

import com.IB.SL.Boot;
import com.IB.SL.graphics.Screen;
import com.IB.SL.graphics.Sprite;
import com.IB.SL.graphics.UI.part.TextBox;

public class ConsoleMenu extends UI_Menu {

	public transient TextBox cmd;

	public ConsoleMenu(int x, int y, Sprite bg) {
		this.bg = bg;
		init(x, y);
	}
	
	public void onLoad() {
		Boot.get().getPlayer().input.suspendInput();
		
		if (cmd == null) {
			cmd = new TextBox(5, 5, 266, 19, Boot.get().key, -1, false);
			cmd.desc = "Command:";
			cmd.useCmds = true;
			cmd.acceptable.add("!");
			cmd.acceptable.add(",");
			cmd.acceptable.add(".");
		}
	}
	
	public void onUnload() {
		Boot.get().getPlayer().input.resumeInput();
		cmd = null;
	}
	
	public void updateUnloaded() {
		if (enabled == false) {
			if (getKey() != null) {
				if (getKey().console) {
					load(this, false);
					getKey().console = false;
				}
			}
		}
	}
	
	public void update() {
		cmd.update();
		
		if (getKey() != null) {
			if (getKey().console) {
				unload(this);
			}
		}
	}
	
	public void render(Screen screen) {
		screen.renderAlphaSprite(bg, x, y);
		cmd.render(screen);
		font8x8.render(x, y + 20, 1, 0xFFFFFF, cmd.history, screen, false, false);
	}
}
