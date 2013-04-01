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
		super("Filtros");
		this.setEnabled(false);
    
    JMenuItem media = new JMenuItem("Suavizado");
    media.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			Panel panel = (((Window) getTopLevelAncestor()).getPanel());
			JDialog mediaDialog = new LowPassFilterDialog(panel);    		
    		mediaDialog.setVisible(true);
		}
	});
    
    JMenuItem bordes = new JMenuItem("Realce de Bordes");
    bordes.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			Panel panel = (((Window) getTopLevelAncestor()).getPanel());
			JDialog pasaAlto = new HighPassFilterDialog(panel);    		
    		pasaAlto.setVisible(true);
		}
	});
    
    JMenuItem medianMenuItem = new JMenuItem("Filtro de la Mediana");
    medianMenuItem.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			Panel panel = (((Window) getTopLevelAncestor()).getPanel());
			JDialog medianDialog = new MedianFilterDialog(panel);    		
    		medianDialog.setVisible(true);
		}
	});
    
    this.add(media);
    this.add(bordes);
    this.add(medianMenuItem);    
	}


}
