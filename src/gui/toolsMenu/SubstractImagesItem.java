package gui.toolsMenu;

import gui.ImageOperations;
import model.Image;

public class SubstractImagesItem extends ImageOperations {

	private static final long serialVersionUID = 1L;

	public SubstractImagesItem(ToolsMenu t) {
		super("Substract Images", t);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doOperation(Image img1, Image img2) {
		img1.substract(img2);

	}

}
