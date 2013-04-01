package gui.toolsMenu;

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
import model.Image.Channel;

import org.apache.commons.math.complex.Complex;
import org.apache.sanselan.ImageReadException;
import org.apache.sanselan.ImageWriteException;

import app.ImageCreator;
import app.ImageLoader;
import app.ImageSaver;

public class ToolsMenu extends JMenu {

	private static final long serialVersionUID = 1L;

	public ToolsMenu(){
		super("Herramientas");
		this.setEnabled(false);
	
	    
	JMenuItem getPixel = new JMenuItem("Modificar Pixel");
    getPixel.addActionListener(new ActionListener() {
    	@Override
    	public void actionPerformed(ActionEvent e) {
    		Panel panel = (((Window) getTopLevelAncestor()).getPanel());
    		JDialog askPixel = new PixelModificationDialog(panel);
    		askPixel.setVisible(true);
		}
	});
    
    JMenuItem crop = new JMenuItem("Recortar Imagen");
    crop.addActionListener(new ActionListener() {
	    	@Override
	    	public void actionPerformed(ActionEvent e) {
	    		
	    		Panel panel = (((Window) getTopLevelAncestor()).getPanel());
	    		
	    		JDialog recortar = new CropImageDialog(panel);
	    		
	    		recortar.setVisible(true);
	    		
				
			}
		});
    
    JMenuItem addition = new AddImagesItem(this);
    
    JMenuItem substraction = new SubstractImagesItem(this);
    
    JMenuItem multiplication = new MultiplyImagesItem(this);
    
    JMenuItem multiplyByScalar = new JMenuItem("Multiplicar por escalar");
    multiplyByScalar.addActionListener(new ActionListener() {
	    	@Override
	    	public void actionPerformed(ActionEvent e) {
	    		Panel panel = (((Window) getTopLevelAncestor()).getPanel());
	    		JDialog scalarMultiplier = new MultiplyByScalarDialog(panel);
	    		scalarMultiplier.setVisible(true);
			}
		}); 
    
    
    JMenuItem negative = new JMenuItem("Negativo");
    negative.addActionListener(new ActionListener() {
	    	@Override
	    	public void actionPerformed(ActionEvent e) {
	    		Panel panel = (((Window) getTopLevelAncestor()).getPanel());
	    		panel.getImage().negative();	 
	    		panel.repaint();
			}
		}); 
    
    JMenuItem histogramItem = new JMenuItem("Histograma");
    histogramItem.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			Panel panel = (((Window) getTopLevelAncestor()).getPanel());
			JDialog dialog = new HistogramDialog(panel);
			dialog.setVisible(true);
		}
	});
    
    JMenuItem contrast = new JMenuItem("Contraste");
    contrast.addActionListener(new ActionListener() {
	    	@Override
	    	public void actionPerformed(ActionEvent e) {
	    		Panel panel = (((Window) getTopLevelAncestor()).getPanel());
	    		JDialog contraste = new IncrementContrastDialog(panel);
	    		contraste.setVisible(true);
			}
		}); 
    
    JMenuItem threshold = new JMenuItem("Umbral");
    threshold.addActionListener(new ActionListener() {
    	@Override
    	public void actionPerformed(ActionEvent e) {
    		Panel panel = (((Window) getTopLevelAncestor()).getPanel());
    		JDialog umbral = new ThresholdDialog(panel);
    		umbral.setVisible(true);
		}
	}); 
    
    JMenuItem equalize = new JMenuItem("Equalizar");
    equalize.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			Panel panel = (((Window) getTopLevelAncestor()).getPanel());
			panel.getImage().equalizeGrays();
			panel.repaint();
		}
	});
    
    JMenuItem dynamicRangeCompression = new JMenuItem("Compresión de Rango Dinámico");
    dynamicRangeCompression.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			Panel panel = (((Window) getTopLevelAncestor()).getPanel());
			panel.getImage().dynamicRangeCompression();
			panel.repaint();
		}
	});
    
    this.add(getPixel);
    this.add(crop);
    this.add(addition);
    this.add(substraction);
    this.add(multiplication);
    this.add(multiplyByScalar);
    
    this.add(new JSeparator());
    
    this.add(negative);
    this.add(histogramItem);
    this.add(contrast);
    this.add(threshold);
    this.add(equalize);
    this.add(dynamicRangeCompression);

    this.add(new JSeparator());

	}

}
