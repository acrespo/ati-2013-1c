package gui.toolsMenu;

import gui.Panel;
import gui.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

public class ToolsMenu extends JMenu {

	private static final long serialVersionUID = 1L;

	public ToolsMenu(){
		super("Tools");
		this.setEnabled(false);
	
	    
	JMenuItem getPixel = new JMenuItem("Modify Pixel");
    getPixel.addActionListener(new ActionListener() {
    	@Override
    	public void actionPerformed(ActionEvent e) {
    		Panel panel = (((Window) getTopLevelAncestor()).getPanel());
    		JDialog askPixel = new PixelModificationDialog(panel);
    		askPixel.setVisible(true);
		}
	});
    
    JMenuItem crop = new JMenuItem("Cut Image");
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
    
    JMenuItem multiplyByScalar = new JMenuItem("Multiply by scalar");
    multiplyByScalar.addActionListener(new ActionListener() {
	    	@Override
	    	public void actionPerformed(ActionEvent e) {
	    		Panel panel = (((Window) getTopLevelAncestor()).getPanel());
	    		JDialog scalarMultiplier = new MultiplyByScalarDialog(panel);
	    		scalarMultiplier.setVisible(true);
			}
		}); 
    
    
    JMenuItem negative = new JMenuItem("Negative");
    negative.addActionListener(new ActionListener() {
	    	@Override
	    	public void actionPerformed(ActionEvent e) {
	    		Panel panel = (((Window) getTopLevelAncestor()).getPanel());
	    		panel.getImage().negative();	 
	    		panel.repaint();
			}
		}); 
    
    JMenuItem histogramItem = new JMenuItem("Grey Scale Histogram");
    histogramItem.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			Panel panel = (((Window) getTopLevelAncestor()).getPanel());
			JDialog dialog = new HistogramDialog(panel);
			dialog.setVisible(true);
		}
	});
    
    JMenuItem contrast = new JMenuItem("Increment Contrast");
    contrast.addActionListener(new ActionListener() {
	    	@Override
	    	public void actionPerformed(ActionEvent e) {
	    		Panel panel = (((Window) getTopLevelAncestor()).getPanel());
	    		JDialog contraste = new IncrementContrastDialog(panel);
	    		contraste.setVisible(true);
			}
		}); 
    
    JMenuItem threshold = new JMenuItem("Threshold");
    threshold.addActionListener(new ActionListener() {
    	@Override
    	public void actionPerformed(ActionEvent e) {
    		Panel panel = (((Window) getTopLevelAncestor()).getPanel());
    		JDialog umbral = new ThresholdDialog(panel);
    		umbral.setVisible(true);
		}
	}); 
    
    JMenuItem equalize = new JMenuItem("Equalize");
    equalize.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			Panel panel = (((Window) getTopLevelAncestor()).getPanel());
			panel.getImage().equalizeGrays();
			panel.repaint();
		}
	});
    
    JMenuItem dynamicRangeCompression = new JMenuItem("Dynamic Range Compression");
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
    
    
//    JMenuItem undo = new JMenuItem("Undo");
//    undo.addActionListener(new ActionListener() {
//		@Override
//		public void actionPerformed(ActionEvent arg0) {
//			Panel panel = (((Window) getTopLevelAncestor()).getPanel());
//			panel.undo();
//			panel.repaint();
//		}
//	});
//    this.add(undo);

	}

}
