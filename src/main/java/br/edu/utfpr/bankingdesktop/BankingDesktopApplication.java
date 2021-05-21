package br.edu.utfpr.bankingdesktop;

import br.edu.utfpr.bankingdesktop.view.FormPrincipal;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.awt.*;

@SpringBootApplication(scanBasePackages = {
	"br.edu.utfpr.bankingdesktop", "br.edu.utfpr.bankingcore"})
public class BankingDesktopApplication {

	public static void main(String[] args) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(BankingDesktopApplication.class);
		builder.headless(false);
		ConfigurableApplicationContext context = builder.run(args);

		EventQueue.invokeLater(() -> {
			FormPrincipal formPrincipal = context.getBean(FormPrincipal.class);
			formPrincipal.setVisible(true);
		});
	}

}
