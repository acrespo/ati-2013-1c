package app;

import gui.Panel;
import gui.Window;
import gui.toolsMenu.HistogramDialog;

import java.io.IOException;

import javax.swing.JDialog;

import model.Image;

import org.apache.sanselan.ImageReadException;


public class Main {
	
	private static Window window;


	public static void main(String args[]) throws ImageReadException, IOException{
		
//		window = new Window();
//		window.setVisible(true);	
//		
//		Image img = ImageLoader.loadImage(new File("/home/acrespo/itba/2013-1c/ATI/lena512.bmp"));
//		Image img2 = ImageCreator.createDegrade(false, 512, 512, 13000, 0);
//		
//		histogram(img);
		
		window = new Window();
		window.setVisible(true);
	}
	
	
	public static void sum(Image img1, Image img2) {
		
		Image result = img1.add(img2);
		printImageToScreen(result);
	}


	private static void printImageToScreen(Image result) {
		Panel panel = (((Window) window).getPanel());
		panel.loadImage(result);
		panel.repaint();
	}
	
	public static void substract(Image img1, Image img2) {
		
		Image result = img1.substract(img2);
		printImageToScreen(result);
	}
	
	public static void multiply(Image image, double scalar) {
		
		image.multiply(scalar);
		printImageToScreen(image);
	}
	
	public static void multiply(Image img1, Image img2) {
		
		Image result = img1.multiply(img2);
		printImageToScreen(result);
		
	}
	
	public static void dynamicRangeCompression(Image img) {
		
		img.dynamicRangeCompression();
		printImageToScreen(img);
	} 
	
	public static void negative(Image img) {
		
		img.negative();
		printImageToScreen(img);
	}
	
	public static void histogram(Panel panel) {
		
		JDialog dialog = new HistogramDialog(panel);
		dialog.setVisible(true);
	}
	
	public static void increaseContrast(Image img, double x1, double x2, double y1, double y2) {
		
		img.incrementContrast(x1, x2, y1, y2);
		printImageToScreen(img);
	}
	
	public static void threshold(Image img, double threshold) {
		
		img.threshold(threshold);
		printImageToScreen(img);
	}
	
	public static void equalize(Image img) {
		img.equalizeGrays();
		printImageToScreen(img);
	}
	
	
	
}
