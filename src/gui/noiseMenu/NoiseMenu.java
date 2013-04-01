package gui.noiseMenu;

import gui.Panel;
import gui.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class NoiseMenu extends JMenu {

	private static final long serialVersionUID = 1L;

	public NoiseMenu() {
		super("Ruido");
		this.setEnabled(false);

		JMenuItem gaussianNoise = new JMenuItem("Gaussiano");
		gaussianNoise.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Panel panel = (((Window) getTopLevelAncestor()).getPanel());
				JDialog askPixel = new GaussianNoiseDialog(panel);
				askPixel.setVisible(true);
			}
		});

		JMenuItem raileyghNoise = new JMenuItem("Rayleigh");
		raileyghNoise.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Panel panel = (((Window) getTopLevelAncestor()).getPanel());
				JDialog askPixel = new RayleighNoiseDialog(panel);
				askPixel.setVisible(true);
			}
		});

		JMenuItem exponentialNoise = new JMenuItem("Exponencial");
		exponentialNoise.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Panel panel = (((Window) getTopLevelAncestor()).getPanel());
				JDialog askPixel = new ExponentialNoiseDialog(panel);
				askPixel.setVisible(true);
			}
		});
		
		JMenuItem saltAndPepperNoise = new JMenuItem("Salt and pepper");
		saltAndPepperNoise.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Panel panel = (((Window) getTopLevelAncestor()).getPanel());
				JDialog askPixel = new SaltAndPepperNoiseDialog(panel);
				askPixel.setVisible(true);
			}
		});

		this.add(gaussianNoise);
		this.add(raileyghNoise);
		this.add(exponentialNoise);
		this.add(saltAndPepperNoise);
	}
}
