package gui.toolsMenu;

import gui.ImageOperations;
import model.Image;

public class AddImagesItem extends ImageOperations {

	private static final long serialVersionUID = 1L;

	public AddImagesItem(ToolsMenu t) {
		super("Sumar Imagen", t);
	}

	@Override
	protected void doOperation(Image img1, Image img2) {
		img1.add(img2);

	}

}
