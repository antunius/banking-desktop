package br.edu.utfpr.bankingdesktop.view;

import br.edu.utfpr.bankingcore.lancamento.LancamentoService;
import br.edu.utfpr.bankingcore.lancamento.Transferencia;

import javax.swing.*;
import java.text.NumberFormat;

public class FormTransferir extends JDialog {
	private JPanel painel;
	private JTextField contaOrigem;
	private JTextField contaDestino;
	private JButton transferirButton;
	private JTextField valor;
	private final LancamentoService lancamentoService;

	public FormTransferir(LancamentoService lancamentoService) {
		setContentPane(painel);
		setTitle("Transferir");

		this.lancamentoService = lancamentoService;
		this.transferirButton.addActionListener(e -> transferirEvt());
	}

	private void transferirEvt() {
		try {
			Transferencia transferencia = lancamentoService.transferencia(Long.parseLong(contaOrigem.getText()),
				Long.parseLong(contaDestino.getText()),
				Double.parseDouble(valor.getText()));
			JOptionPane.showMessageDialog(painel, "Transferencia efetuada!", "Transferencia de " + NumberFormat.getCurrencyInstance().format(transferencia.getLancamentoDestino().getValor()), JOptionPane.INFORMATION_MESSAGE);
			setVisible(false);
		} catch (Exception exception) {
			JOptionPane.showMessageDialog(painel, exception.getMessage(), "Falha ao Transferir", JOptionPane.ERROR_MESSAGE);
		}
	}
}
