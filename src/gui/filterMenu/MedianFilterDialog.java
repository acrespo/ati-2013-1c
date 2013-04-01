package gui.filterMenu;

import gui.MessageFrame;
import gui.Panel;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MedianFilterDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	public MedianFilterDialog(final Panel panel){
		setTitle("Filtro de la mediana");
		setBounds(1, 1, 250, 120);
		Dimension size = getToolkit().getScreenSize();
		setLocation(size.width/3 - getWidth()/3, size.height/3 - getHeight()/3);
		this.setResizable(false);
		setLayout(null);

		JPanel pan1 = new JPanel();
		pan1.setBorder(BorderFactory.createTitledBorder("Tamaño mascara"));
		pan1.setBounds(0, 0, 250, 50);

		JLabel coordLabel1 = new JLabel("Ancho = ");
		final JTextField coordX = new JTextField("3");
		coordX.setColumns(3);

		JLabel coordLabel2 = new JLabel(", Alto = ");
		final JTextField coordY = new JTextField("3");
		coordY.setColumns(3);

		JButton okButton = new JButton("OK");
		okButton.setSize(250, 40);
		okButton.setBounds(0, 50, 250, 40);
		okButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				int x, y;
				try{
					x = Integer.valueOf(coordX.getText());
					y = Integer.valueOf(coordY.getText());

				} catch(NumberFormatException ex){
					new MessageFrame("Los datos ingresados son invalidos");
					return;
				}
				panel.getImage().applyMedianMask(new Point(x, y));
				panel.repaint();		
				dispose();

			}
		});

		pan1.add(coordLabel1);
		pan1.add(coordX);

		pan1.add(coordLabel2);
		pan1.add(coordY);

		this.add(pan1);
		this.add(okButton);
	}
}
