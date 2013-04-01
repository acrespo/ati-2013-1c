package gui.createMenu;

import gui.Panel;
import gui.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

import app.ImageCreator;

import model.Image;

public class CreateMenu extends JMenu {

	private static final long serialVersionUID = 1L;

	public CreateMenu(){
		super("Crear");
		this.setEnabled(true);
	
	JMenuItem degradeBW = new JMenuItem("Degrade de grises");
    degradeBW.addActionListener(new ActionListener() {
	    	@Override
	    	public void actionPerformed(ActionEvent e) {
	    		
	    		Panel panel = (((Window) getTopLevelAncestor()).getPanel());
	    		
	    		JDialog degrade = new DegradeDialog(panel, false);
	    		
	    		degrade.setVisible(true);
	    		
				
			}
		});
    
    JMenuItem degradeColor = new JMenuItem("Degrade de colores");
    degradeColor.addActionListener(new ActionListener() {
	    	@Override
	    	public void actionPerformed(ActionEvent e) {
	    		
	    		Panel panel = (((Window) getTopLevelAncestor()).getPanel());
	    		
	    		JDialog degrade = new DegradeDialog(panel, true);
	    		
	    		degrade.setVisible(true);
	    		
				
			}
		});
    
    JMenuItem binaryImage = new JMenuItem("Imagen binaria");
    binaryImage.addActionListener(new ActionListener() {
	    	@Override
	    	public void actionPerformed(ActionEvent e) {
	    		
	    		Panel panel = (((Window) getTopLevelAncestor()).getPanel());
	    		
	    		JDialog binaryImage = new CreateBinaryImageDialog(panel);
	    		
	    		binaryImage.setVisible(true);
	    		
				
			}
		});
    
    
    JMenuItem A = new JMenuItem("A");
    A.addActionListener(new ActionListener() {
	    	@Override
	    	public void actionPerformed(ActionEvent e) {
	    		
	    		Panel panel = (((Window) getTopLevelAncestor()).getPanel());
	    		
	    		Image img = ImageCreator.A(256, 256);
				
				if(img != null){
					panel.loadImage(img);
					panel.repaint();
				}
	    		
				
			}
		});
    
    JMenuItem B = new JMenuItem("B");
    B.addActionListener(new ActionListener() {
	    	@Override
	    	public void actionPerformed(ActionEvent e) {
	    		
	    		Panel panel = (((Window) getTopLevelAncestor()).getPanel());
	    		
	    		Image img = ImageCreator.B(256, 256);
				
				if(img != null){
					panel.loadImage(img);
					panel.repaint();
				}
	    		
				
			}
		});
    
    JMenuItem C = new JMenuItem("C");
    C.addActionListener(new ActionListener() {
	    	@Override
	    	public void actionPerformed(ActionEvent e) {
	    		
	    		Panel panel = (((Window) getTopLevelAncestor()).getPanel());
	    		
	    		Image img = ImageCreator.C(256, 256);
				
				if(img != null){
					panel.loadImage(img);
					panel.repaint();
				}
	    		
				
			}
		});
    
    JMenuItem D = new JMenuItem("D");
    D.addActionListener(new ActionListener() {
	    	@Override
	    	public void actionPerformed(ActionEvent e) {
	    		
	    		Panel panel = (((Window) getTopLevelAncestor()).getPanel());
	    		
	    		Image img = ImageCreator.D(512, 512);
				
				if(img != null){
					panel.loadImage(img);
					panel.repaint();
				}
	    		
				
			}
		});
    
    JMenuItem E = new JMenuItem("E");
    E.addActionListener(new ActionListener() {
	    	@Override
	    	public void actionPerformed(ActionEvent e) {
	    		
	    		Panel panel = (((Window) getTopLevelAncestor()).getPanel());
	    		
	    		Image img = ImageCreator.E(256, 256);
				
				if(img != null){
					panel.loadImage(img);
					panel.repaint();
				}
	    		
				
			}
		});
    
    JMenuItem F = new JMenuItem("F");
    F.addActionListener(new ActionListener() {
	    	@Override
	    	public void actionPerformed(ActionEvent e) {
	    		
	    		Panel panel = (((Window) getTopLevelAncestor()).getPanel());
	    		
	    		Image img = ImageCreator.F(256, 256);
				
				if(img != null){
					panel.loadImage(img);
					panel.repaint();
				}
	    		
				
			}
		});
    
    JMenuItem G = new JMenuItem("G");
    G.addActionListener(new ActionListener() {
	    	@Override
	    	public void actionPerformed(ActionEvent e) {
	    		
	    		Panel panel = (((Window) getTopLevelAncestor()).getPanel());
	    		
	    		Image img = ImageCreator.G(256, 256);
				
				if(img != null){
					panel.loadImage(img);
					panel.repaint();
				}
	    		
				
			}
		});
    
    JMenuItem H = new JMenuItem("H");
    H.addActionListener(new ActionListener() {
	    	@Override
	    	public void actionPerformed(ActionEvent e) {
	    		
	    		Panel panel = (((Window) getTopLevelAncestor()).getPanel());
	    		
	    		Image img = ImageCreator.H(256, 256);
				
				if(img != null){
					panel.loadImage(img);
					panel.repaint();
				}
	    		
				
			}
		});
    
    JMenuItem I = new JMenuItem("I");
    I.addActionListener(new ActionListener() {
	    	@Override
	    	public void actionPerformed(ActionEvent e) {
	    		
	    		Panel panel = (((Window) getTopLevelAncestor()).getPanel());
	    		
	    		Image img = ImageCreator.I(256, 256);
				
				if(img != null){
					panel.loadImage(img);
					panel.repaint();
				}
	    		
				
			}
		});
    
    JMenuItem circle = new JMenuItem("Circle");
    circle.addActionListener(new ActionListener() {
	    	@Override
	    	public void actionPerformed(ActionEvent e) {
	    		
	    		Panel panel = (((Window) getTopLevelAncestor()).getPanel());
	    		
	    		Image img = ImageCreator.circle(256, 256);
				
				if(img != null){
					panel.loadImage(img);
					panel.repaint();
				}
	    		
				
			}
		});

    this.add(degradeColor);
    this.add(degradeBW);
    this.add(binaryImage);
    
    this.add(new JSeparator());
    
    this.add(A);
    this.add(B);
    this.add(C);
    this.add(D);
    this.add(E);
    this.add(F);
    this.add(G);
    this.add(H);
    this.add(I);
    this.add(circle);
    
	}

}
