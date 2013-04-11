package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import model.Coordinate;
import model.Image;
import model.Image.Channel;



public class Panel extends JPanel {

	private static final long serialVersionUID = 1L;
	private Image original = null;
	private Image image = null;
	private Image previous = null;
	private List<Coordinate> mask = null;
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (image != null) {
			for (int i = 0; i < image.getWidth(); i++)
				for (int j = 0; j < image.getHeight(); j++) {
					g.setColor(new Color(image.getRGBPixel(i, j)));
					g.drawRect(i, j, 1, 1);
				}
			this.getTopLevelAncestor().setSize(image.getWidth() + 7,
					image.getHeight() + 53);
		}
	}
	
	public void loadImage(Image image) {
		this.image = image;
		this.original = image;
		((Window) getTopLevelAncestor()).enableTools();
	}
	
	public void loadMask(List<Coordinate> mask) {
		this.mask = mask;
	}

	public boolean setPixel(String xText, String yText, String colorText) {
		
		int x = 0;
		int y = 0;
		int color = 0;
		
		try {
			x = Integer.valueOf(xText);
			y = Integer.valueOf(yText);
			color = Integer.valueOf(colorText);
		} catch (NumberFormatException ex){
			new MessageFrame("Values entered are not valid");
			return false;
		}
		
		//TODO: Create method to avoid this 
		this.image.setPixel(x, y, Channel.RED, color);
		this.image.setPixel(x, y, Channel.GREEN, color);
		this.image.setPixel(x, y, Channel.BLUE, color);
		
		this.repaint();
		return true;
	}
	
	public Image getImage(){
		return original;
	}
	
	public List<Coordinate> getMask(){
		if(mask == null)
			return null;
		Coordinate p1 = mask.get(0);
		Coordinate p2 = mask.get(1);
		this.mask = new ArrayList<Coordinate>();
		for(int y = Math.min(p1.y, p2.y); y <= Math.max(p1.y, p2.y); y++){
			for(int x = Math.min(p1.x, p2.x); x <= Math.max(p1.x, p2.x); x++){
				mask.add(new Coordinate(x, y));
			}
		}
		return mask;
	}
	
	public Image getWorkingImage() {
		return image;
	}

	public void setImage(Image image) {
		previous = this.image.clone();
		this.image = image;
		this.original = image;
	}

	public void setWorkingImage(Image workingImage) {
//		previous = this.original.clone();
		this.image = workingImage;
	}

//	public void undo() {
//		image = previous;
//	}
	
	
}
