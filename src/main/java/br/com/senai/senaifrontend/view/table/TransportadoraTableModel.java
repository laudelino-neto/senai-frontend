package br.com.senai.senaifrontend.view.table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.senai.senaifrontend.dto.Transportadora;

public class TransportadoraTableModel extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;
	
	private final int QTDE_COLUNAS = 2;
	
	private List<Transportadora> transportadoras;
	
	public TransportadoraTableModel(
			List<Transportadora> transportadoras) {
		this.transportadoras = transportadoras;
	}

	@Override
	public int getRowCount() {
		return transportadoras.size();
	}

	@Override
	public int getColumnCount() {
		return QTDE_COLUNAS;
	}
	
	@Override
	public String getColumnName(int column) {
		
		if (column == 0) {
			return "ID";
		}else if (column == 1) {		
			return "Nome Fantasia";
		}
		
		throw new IllegalArgumentException("Indice inválido");
	}
	
	public Transportadora getPor(int rowIndex) {
		return transportadoras.get(rowIndex);
	}
	
	public void removePor(int rowIndex) {
		this.transportadoras.remove(rowIndex);
	}
	
	public void remover(Transportadora transportadora) {
		this.transportadoras.remove(transportadora);
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (columnIndex == 0) {
			return this.transportadoras.get(rowIndex).getId();
		}else if (columnIndex == 1) {			
			return this.transportadoras.get(rowIndex).getNomeFantasia();
		}
		throw new IllegalArgumentException("Índice inválido");
	}
	
}
