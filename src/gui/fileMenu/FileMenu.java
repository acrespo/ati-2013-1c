package gui.fileMenu;

import gui.MessageFrame;
import gui.Panel;
import gui.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

import model.Image;

import org.apache.sanselan.ImageReadException;
import org.apache.sanselan.ImageWriteException;

import app.ImageLoader;
import app.ImageSaver;


public class FileMenu extends JMenu {
	
	public JMenuItem saveImage = new JMenuItem("Save image");
	JMenuItem loadImage = new JMenuItem("Load image");
	JMenuItem loadRaw = new JMenuItem("Load image as raw");
	JMenuItem exit = new JMenuItem("Exit");
	
	private static final long serialVersionUID = 1L;

	public FileMenu(){
		super("File");
		
		loadImage.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {

	    		JFileChooser selector = new JFileChooser();
	    		selector.showOpenDialog(FileMenu.this);
				File arch = selector.getSelectedFile();
				
				Panel panel = (((Window) getTopLevelAncestor()).getPanel());
				if(arch != null){
					Image image = null;
					
					try{
						image = ImageLoader.loadImage(arch);
					} catch (ImageReadException ex){
						new MessageFrame("Could not load image");
					} catch (IOException ex){
						new MessageFrame("Could not load image");
					}
					
					if(image != null){
						panel.loadImage(image);
						panel.repaint();
					}
					
				}
							
				
			}
		});
		
		loadRaw.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {

	    		JFileChooser selector = new JFileChooser();
	    		selector.showOpenDialog(FileMenu.this);
				File arch = selector.getSelectedFile();
				
				Panel panel = (((Window) getTopLevelAncestor()).getPanel());
				if(arch != null){
					JDialog rawParams = new RawImageDialog(panel, arch);
		    		rawParams.setVisible(true);					
				}		
			}
		});
		
		saveImage.setEnabled(false);
	    saveImage.addActionListener(new ActionListener() {
		    	@Override
		    	public void actionPerformed(ActionEvent e) {
		    		JFileChooser selector = new JFileChooser();
					selector.setApproveButtonText("Save");
		    		selector.showSaveDialog(FileMenu.this);	
		    		
		    		File arch = selector.getSelectedFile();
		    		
		    		if(arch != null){
		    			Image image = (((Window) getTopLevelAncestor()).getPanel().getImage());
		    			try {
		    			ImageSaver.saveImage(arch, image);
		    			} catch (ImageWriteException ex){
							new MessageFrame("Could not load image");
						} catch (IOException ex){
							new MessageFrame("Could not load image");
						}
		    		}
					
				}
			});
	      
	    	exit.addActionListener(new ActionListener() {
		    	
	    		@Override
	    		public void actionPerformed(ActionEvent e) {
	    			System.exit(0);
	    		}
	    	});

	    	this.add(loadImage);
	    	this.add(loadRaw);
	    	this.add(saveImage);
	    	this.add(new JSeparator());
	    	this.add(exit);
		
	}

}
