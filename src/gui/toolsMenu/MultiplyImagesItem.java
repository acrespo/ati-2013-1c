package gui.toolsMenu;

import gui.ImageOperations;
import model.Image;

public class MultiplyImagesItem extends ImageOperations {

	private static final long serialVersionUID = 1L;

	public MultiplyImagesItem(ToolsMenu t) {
		super("Multiplicar Imagenes", t);
	}

	@Override
	protected void doOperation(Image img1, Image img2) {
		img1.multiply(img2);

	}

}
