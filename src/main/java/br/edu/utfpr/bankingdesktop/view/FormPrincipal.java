package br.edu.utfpr.bankingdesktop.view;


import br.edu.utfpr.bankingcore.lancamento.LancamentoService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import java.awt.*;

@org.springframework.stereotype.Component
public class FormPrincipal extends JFrame {
	private JButton sacarButton;
	private JButton depositarButton;
	private JButton transferirButton;
	private JButton extratoButton;
	private JPanel main;

	@Autowired
	private LancamentoService lancamentoService;

	/**
	 * Constructs a new frame that is initially invisible.
	 * <p>
	 * This constructor sets the component's locale property to the value
	 * returned by <code>JComponent.getDefaultLocale</code>.
	 *
	 * @throws HeadlessException if GraphicsEnvironment.isHeadless()
	 *                           returns true.
	 * @see GraphicsEnvironment#isHeadless
	 * @see Component#setSize
	 * @see Component#setVisible
	 * @see JComponent#getDefaultLocale
	 */
	public FormPrincipal() throws HeadlessException {

		initComponents();
		initListeners();
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
	}


	private void initListeners() {
		this.sacarButton.addActionListener(e -> sacarEvt());
		this.depositarButton.addActionListener(e -> depositarEvt());
		this.transferirButton.addActionListener(e -> transferirEvt());
		this.extratoButton.addActionListener(e -> extratoEvt());
	}

	private void extratoEvt() {
		FormExtrato formExtrato = new FormExtrato(lancamentoService);
		formExtrato.pack();
		formExtrato.toFront();
		formExtrato.setPosicao();
		formExtrato.setVisible(true);
	}

	private void transferirEvt() {
		FormTransferir formTransferir = new FormTransferir(lancamentoService);
		formTransferir.toFront();
		formTransferir.pack();
		formTransferir.setVisible(true);
	}

	private void depositarEvt() {
		FormDepositar formDepositar = new FormDepositar(lancamentoService);
		formDepositar.toFront();
		formDepositar.pack();
		formDepositar.setVisible(true);
	}

	private void sacarEvt() {
		FormSaque formSaque = new FormSaque(lancamentoService);
		formSaque.toFront();
		formSaque.pack();
		formSaque.setVisible(true);
	}

	private void initComponents() {
		setContentPane(main);
		setLocationRelativeTo(null);
		this.setTitle("Terminal Bancário");
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		//this.setUndecorated(true);
	}

	public JButton getSacarButton() {
		return sacarButton;
	}

	public JButton getDepositarButton() {
		return depositarButton;
	}

	public JButton getTransferirButton() {
		return transferirButton;
	}

	public JButton getExtratoButton() {
		return extratoButton;
	}
}
