package br.edu.utfpr.bankingdesktop.view;

import br.edu.utfpr.bankingcore.lancamento.LancamentoService;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;

public class FormExtrato extends JDialog {
	private JPanel painel;
	private JTextArea info;
	private JButton exibirExtratoButton;
	private JTextField conta;

	private final LancamentoService lancamentoService;

	public FormExtrato(LancamentoService lancamentoService) {
		setContentPane(painel);

		setPosicao();
		this.lancamentoService = lancamentoService;
		exibirExtratoButton.addActionListener(e -> puxarExtrato());
	}

	public void setPosicao() {
		Dimension d = this.painel.getSize();
		this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
	}

	private void puxarExtrato() {

		try {
			final var extrato = lancamentoService.extrato(Long.parseLong(conta.getText()));

			final var infos = extrato.stream().iterator().next();
			info.setText(getInfoExtrato(infos));
			info.append("\n--------------------------------Lançamentos--------------------------------\n");
			info.append("\n Data Operação Valor\n");
			extrato.forEach(lancamento -> {
				info.append(String.format("%s %s %s \n", lancamento.getData().toString(), lancamento.getOperacao(), NumberFormat.getCurrencyInstance().format(lancamento.getValor())));
			});

		} catch (Exception exception) {
			JOptionPane.showMessageDialog(painel, exception.getMessage(), "Falha ao Buscar Extrato", JOptionPane.ERROR_MESSAGE);
		}

	}

	private String getInfoExtrato(br.edu.utfpr.bankingcore.lancamento.Lancamento infos) {
		return String.format("----------------Correntista----------------\n" +
				"Nome: %s\n" +
				"Documento: %s\n" +
				"Conta: %d\n" +
				"Agência: %d \n" +
				"Limite Crédito: %s  \n" +
				"Saldo : %s\n" +
				"\n",
			infos.getConta().getCorrentista().getNome(),
			infos.getConta().getCorrentista().getCpf(),
			infos.getConta().getId(),
			1, NumberFormat.getCurrencyInstance().format(infos.getConta().getLimiteCredito()),
			NumberFormat.getCurrencyInstance().format(infos.getConta().getSaldo()));
	}
}
