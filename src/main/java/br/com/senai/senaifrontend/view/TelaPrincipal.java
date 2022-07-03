package br.com.senai.senaifrontend.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TelaPrincipal extends JFrame implements Serializable{

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	
	@Autowired
	private TelaListagemTransportadora listagem;

	/**
	 * Create the frame.
	 */
	public TelaPrincipal() {
		setTitle("Principal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnTransportadoras = new JButton("Transportadoras");
		btnTransportadoras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listagem.setVisible(true);
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(btnTransportadoras)
					.addContainerGap(335, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnTransportadoras)
					.addContainerGap(217, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
