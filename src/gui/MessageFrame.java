package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MessageFrame extends JFrame {

	private static final long serialVersionUID = 1L;	

	
	public MessageFrame(String mensaje) {
		setTitle("Error");
		setLayout(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setLocation(450, 400);
		setSize(new Dimension(400,140));
		
		JLabel label = new JLabel(mensaje);
		label.setBounds(50, 0, 300, 50);
		JButton aceptar = new JButton("Accept");
		aceptar.setBounds(150, 60, 100, 30);
		aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				
			}
		});
		
		add(label);
		add(aceptar);
	}
	
}