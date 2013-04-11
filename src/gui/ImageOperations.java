package gui;

import gui.toolsMenu.ToolsMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;

import model.Image;

import org.apache.sanselan.ImageReadException;

import app.ImageLoader;

public abstract class ImageOperations extends JMenuItem {
	ToolsMenu toolsMenu;
	private static final long serialVersionUID = 1L;

	public ImageOperations(String s, final ToolsMenu t){
		super(s);
		toolsMenu = t;
		
		this.addActionListener(new ActionListener() {
	
		private static final long serialVersionUID = 1L;
	
			@Override
	    	public void actionPerformed(ActionEvent e) {
	    		
	    		JFileChooser selector = new JFileChooser();
	    		selector.showOpenDialog(t);
				File arch = selector.getSelectedFile();
				
				Panel panel = (((Window) t.getTopLevelAncestor()).getPanel());
				if(arch != null){
					Image image = null;
					
					try{
						image = ImageLoader.loadImage(arch);
					} catch (ImageReadException ex){
						new MessageFrame("Could not load image");
					} catch (IOException ex){
						new MessageFrame("Could not load image");
					}
					if(image.getHeight() != panel.getImage().getHeight()
						|| image.getWidth() != panel.getImage().getWidth()) {
	
			    		new MessageFrame("Images must have the exact same size");
						return;
					}
					try {
						doOperation(panel.getImage(), image);	 			    		
			    		panel.repaint();
			    	} 
					catch(IllegalArgumentException i){			    		
			    		new MessageFrame(i.getMessage());			    		
			    	}
				}
				
			}
		});
	}
	
	protected abstract void doOperation(Image img1, Image img2);

}
