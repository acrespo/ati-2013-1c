package gui;

import gui.createMenu.CreateMenu;
import gui.fileMenu.FileMenu;
import gui.filterMenu.FilterMenu;
import gui.noiseMenu.NoiseMenu;
import gui.toolsMenu.ToolsMenu;

import javax.swing.JMenuBar;

public class MenuTopBar extends JMenuBar {

	private static final long serialVersionUID = 1L;

	public MenuTopBar(){
		this.add(new FileMenu());
		this.add(new ToolsMenu());
		this.add(new FilterMenu());
		this.add(new NoiseMenu());
		this.add(new CreateMenu());
	}

}
