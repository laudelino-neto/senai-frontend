package br.com.senai.senaifrontend.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.senai.senaifrontend.client.TransportadoraClient;
import br.com.senai.senaifrontend.dto.Transportadora;

@Component
public class TelaCadastroTransportadora extends JFrame implements Serializable{

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextField edtNomeFantasia;
	private JTextField edtRazaoSocial;
	private JTextField edtCnpj;
	
	@Autowired
	private TransportadoraClient client;
	
	private Transportadora transportadoraSalva;
	
	public void colocarEmEdicao(
			Transportadora transportadoraSalva) {
		this.edtRazaoSocial.setText(
				transportadoraSalva.getRazaoSocial());
		this.edtNomeFantasia.setText(
				transportadoraSalva.getNomeFantasia());
		this.edtCnpj.setText(transportadoraSalva.getCnpj());
		this.transportadoraSalva = transportadoraSalva;
		setVisible(true);
	}
	
	public void colocarEmInclusao() {
		this.transportadoraSalva = null;
		this.edtRazaoSocial.setText("");
		this.edtNomeFantasia.setText("");
		this.edtCnpj.setText("");
		setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public TelaCadastroTransportadora() {
		setTitle("Cadastro de Transportadora");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 233);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Nome Fantasia");
		
		edtNomeFantasia = new JTextField();
		edtNomeFantasia.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Razão Social");
		
		edtRazaoSocial = new JTextField();
		edtRazaoSocial.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("CNPJ");
		
		edtCnpj = new JTextField();
		edtCnpj.setColumns(10);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (transportadoraSalva != null) {
						transportadoraSalva.setNomeFantasia(
								edtNomeFantasia.getText());
						transportadoraSalva.setRazaoSocial(
								edtRazaoSocial.getText());
						transportadoraSalva.setCnpj(edtCnpj.getText());
						client.alterar(transportadoraSalva);
						JOptionPane.showMessageDialog(contentPane, 
								"Transportadora atualizada com sucesso");
					}else {
						Transportadora novaTransportadora = new Transportadora();
						novaTransportadora.setRazaoSocial(edtRazaoSocial.getText());
						novaTransportadora.setNomeFantasia(edtNomeFantasia.getText());
						novaTransportadora.setCnpj(edtCnpj.getText());
						transportadoraSalva = client.inserir(novaTransportadora);
						JOptionPane.showMessageDialog(contentPane, 
								"Transportadora inserida com sucesso");
					}
				}catch (Exception ex) {
					JOptionPane.showMessageDialog(contentPane, ex.getMessage());
				}
				
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel)
						.addComponent(edtNomeFantasia, GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
						.addComponent(lblNewLabel_1)
						.addComponent(edtRazaoSocial, GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
						.addComponent(lblNewLabel_2)
						.addComponent(edtCnpj, GroupLayout.PREFERRED_SIZE, 224, GroupLayout.PREFERRED_SIZE)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addContainerGap(314, Short.MAX_VALUE)
							.addComponent(btnSalvar)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(edtNomeFantasia, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(edtRazaoSocial, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_2)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(edtCnpj, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
					.addComponent(btnSalvar))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
