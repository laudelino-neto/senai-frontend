package br.com.senai.senaifrontend.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumnModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.senai.senaifrontend.client.TransportadoraClient;
import br.com.senai.senaifrontend.dto.Transportadora;
import br.com.senai.senaifrontend.view.table.TransportadoraTableModel;

@Component
public class TelaListagemTransportadora extends JFrame implements Serializable{

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	@Autowired
	private TransportadoraClient client;
	
	@Autowired
	private TelaCadastroTransportadora cadastro;	
	
	private JTextField edtNomeFantasia;	
	
	private void atualizar(JTable tabela) {
		List<Transportadora> transportadoras = client.listarPor(edtNomeFantasia.getText());		
		TransportadoraTableModel model = new TransportadoraTableModel(transportadoras);
		tabela.setModel(model);
		TableColumnModel cm = tabela.getColumnModel();
		cm.getColumn(0).setPreferredWidth(50);
		cm.getColumn(1).setPreferredWidth(352);
		tabela.updateUI();
	}
	
	private Transportadora getTransportadoraSelecionadaNa(JTable tabela) {
		int linhaSelecionada = tabela.getSelectedRow();
		if (linhaSelecionada < 0) {
			throw new IllegalArgumentException("Nenhuma linha foi selecionada");
		}
		TransportadoraTableModel model = (TransportadoraTableModel)tabela.getModel();
		Transportadora itemSelecionado = model.getPor(linhaSelecionada);
		return itemSelecionado;
	}
	
	private void removerRegistroDa(JTable tabela) {
		try {
			
			Transportadora transportadoraSelecionada = getTransportadoraSelecionadaNa(tabela);
			
			int opcaoSelecionada = JOptionPane.showConfirmDialog(
					contentPane, "Deseja remover?", "Remoção", JOptionPane.YES_NO_OPTION);
			
			if (opcaoSelecionada == JOptionPane.YES_OPTION) {			
				this.client.excluir(transportadoraSelecionada);
				((TransportadoraTableModel)tabela.getModel()).remover(transportadoraSelecionada);
				tabela.updateUI();
				JOptionPane.showMessageDialog(contentPane, "Transportadora removida com sucesso");
			}
			
		}catch (Exception e) {
			JOptionPane.showMessageDialog(contentPane, e.getMessage());
		}
	}
	
	private void editarRegistroDa(JTable tabela) {
		try {		
			Transportadora registroSelecionado = getTransportadoraSelecionadaNa(tabela);
			this.cadastro.colocarEmEdicao(registroSelecionado);
		}catch (Exception e) {
			JOptionPane.showMessageDialog(contentPane, e.getMessage());
		}
	}

	/**
	 * Create the frame.
	 */
	public TelaListagemTransportadora() {
		setTitle("Listagem de Transportadora");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 377);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Nome Fantasia");
		
		edtNomeFantasia = new JTextField();
		edtNomeFantasia.setColumns(10);
		
		JTable tabela = new JTable();
		tabela.setFillsViewportHeight(true);
		
		JButton btnListar = new JButton("Listar");
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atualizar(tabela);
			}
		});			
		
		JScrollPane scrollPane = new JScrollPane(tabela);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editarRegistroDa(tabela);
			}
		});
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removerRegistroDa(tabela);
			}
		});
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cadastro.colocarEmInclusao();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel)
						.addComponent(edtNomeFantasia, GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addContainerGap(242, Short.MAX_VALUE)
							.addComponent(btnAdicionar)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnListar))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 413, GroupLayout.PREFERRED_SIZE)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addContainerGap(242, Short.MAX_VALUE)
							.addComponent(btnExcluir)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnEditar)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(edtNomeFantasia, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnListar)
						.addComponent(btnAdicionar))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnEditar)
						.addComponent(btnExcluir))
					.addContainerGap(43, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
