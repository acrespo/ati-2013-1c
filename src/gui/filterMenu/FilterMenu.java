package gui.filterMenu;

import gui.Panel;
import gui.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class FilterMenu extends JMenu {

	private static final long serialVersionUID = 1L;

	public FilterMenu(){
		super("Filters");
		this.setEnabled(false);
    
    JMenuItem mediaFilter = new JMenuItem("Smoothing - Media Filter");
    mediaFilter.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			Panel panel = (((Window) getTopLevelAncestor()).getPanel());
			JDialog mediaDialog = new LowPassFilterDialog(panel);    		
    		mediaDialog.setVisible(true);
		}
	});
    
    JMenuItem edgeEnhancement = new JMenuItem("Edge enhancement");
    edgeEnhancement.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			Panel panel = (((Window) getTopLevelAncestor()).getPanel());
			JDialog pasaAlto = new HighPassFilterDialog(panel);    		
    		pasaAlto.setVisible(true);
		}
	});
    
    JMenuItem medianFilter = new JMenuItem("Smoothing - Median Filter");
    medianFilter.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			Panel panel = (((Window) getTopLevelAncestor()).getPanel());
			JDialog medianDialog = new MedianFilterDialog(panel);    		
    		medianDialog.setVisible(true);
		}
	});
    
    this.add(mediaFilter);
    this.add(medianFilter);   
    this.add(edgeEnhancement);

	}


}
