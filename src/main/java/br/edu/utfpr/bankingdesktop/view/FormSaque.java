package br.edu.utfpr.bankingdesktop.view;

import br.edu.utfpr.bankingcore.lancamento.Lancamento;
import br.edu.utfpr.bankingcore.lancamento.LancamentoService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

public class FormSaque extends JDialog {

	private JTextField contaTxt;
	private JTextField valorTxt;
	private JButton sacarButton;
	private JLabel contaLbl;
	private JLabel valorLbl;
	private JPanel painel;

	private final LancamentoService lancamentoService;

	/**
	 * Creates a non-resizable, non-closable, non-maximizable,
	 * non-iconifiable <code>JDialog</code> with no title.
	 *
	 * @param lancamentoService
	 */
	public FormSaque(LancamentoService lancamentoService) {
		initComponents();
		setTitle("Sacar");
		pack();
		//setClosable(true);
		this.lancamentoService = lancamentoService;
		sacarButton.addActionListener(new ActionListener() {
			/**
			 * Invoked when an action occurs.
			 *
			 * @param e the event to be processed
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Lancamento lancamento = lancamentoService.saque(Long.parseLong(contaTxt.getText()), Double.parseDouble(valorTxt.getText()));
					JOptionPane.showMessageDialog(painel, "Saque efetuado, retire suas cédulas!", "Saque de " + NumberFormat.getCurrencyInstance().format(lancamento.getValor()), JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception exception) {
					JOptionPane.showMessageDialog(painel, exception.getMessage(), "Falha ao Sacar", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	private void initComponents() {

		setContentPane(painel);
	}

	public JTextField getContaTxt() {
		return contaTxt;
	}

	public JTextField getValorTxt() {
		return valorTxt;
	}

	public JButton getSacarButton() {
		return sacarButton;
	}

	public JLabel getContaLbl() {
		return contaLbl;
	}

	public JLabel getValorLbl() {
		return valorLbl;
	}

	public JPanel getPainel() {
		return painel;
	}

	public LancamentoService getLancamentoService() {
		return lancamentoService;
	}
}
