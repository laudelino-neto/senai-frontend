package br.com.senai.senaifrontend.view.table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.senai.senaifrontend.dto.Transportadora;

public class TransportadoraTableModel 
		extends AbstractTableModel {
	
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
		return 2;
	}
	
	@Override
	public String getColumnName(int column) {
		if (column == 0) {
			return "ID";
		}
		
		return "Nome Fantasia";
	}
	
	public Transportadora getPor(int rowIndex) {
		return transportadoras.get(rowIndex);
	}
	
	public void removePor(int rowIndex) {
		this.transportadoras.remove(rowIndex);
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (columnIndex == 0) {
			return this.transportadoras.get(rowIndex).getId();
		}
		return this.transportadoras.get(rowIndex).getNomeFantasia();
	}
	
}
