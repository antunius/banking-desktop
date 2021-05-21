package br.edu.utfpr.bankingdesktop.view;

import br.edu.utfpr.bankingcore.lancamento.Lancamento;
import br.edu.utfpr.bankingcore.lancamento.LancamentoService;

import javax.swing.*;
import java.text.NumberFormat;

public class FormDepositar extends JDialog {
	private JPanel painel;
	private JTextField conta;
	private JTextField valor;
	private JButton depositarButton;

	private final LancamentoService lancamentoService;

	/**
	 * Creates a non-resizable, non-closable, non-maximizable,
	 * non-iconifiable <code>JDialog</code> with no title.
	 */
	public FormDepositar(LancamentoService lancamentoService) {
		this.lancamentoService = lancamentoService;
		setContentPane(painel);
		setTitle("Depositar");
		depositarButton.addActionListener(e -> depositar());
	}

	private void depositar() {
		try {
			Lancamento lancamento = lancamentoService.depositar(Long.parseLong(conta.getText()), Double.parseDouble(valor.getText()));
			JOptionPane.showMessageDialog(painel, "Depósito efetuado!", "Depósito de " + NumberFormat.getCurrencyInstance().format(lancamento.getValor()), JOptionPane.INFORMATION_MESSAGE);
			setVisible(false);
		} catch (Exception exception) {
			JOptionPane.showMessageDialog(painel, exception.getMessage(), "Falha ao Depositar", JOptionPane.ERROR_MESSAGE);
		}
	}

	public LancamentoService getLancamentoService() {
		return lancamentoService;
	}

	public JPanel getPanel() {
		return painel;
	}

	public JTextField getConta() {
		return conta;
	}

	public JTextField getValor() {
		return valor;
	}

	public JButton getDepositarButton() {
		return depositarButton;
	}
}
