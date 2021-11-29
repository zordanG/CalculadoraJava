package com.zord.calc.visao;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.zord.calc.modelo.Memoria;

@SuppressWarnings("serial")
public class Teclado extends JPanel implements ActionListener{

	private final Color COR_CINZA_ESCURO = new Color(68, 68, 68);
	private final Color COR_CINZA_CLARO = new Color(99, 99, 99);
	private final Color COR_LARANJA = new Color(242, 163, 60);
	
	Teclado(){
		
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		
		setLayout(layout);
		
		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		
		c.gridwidth = 2;
		adicinoarBotao("AC", COR_CINZA_ESCURO, c, 0, 0);
		c.gridwidth = 1;
		adicinoarBotao("±", COR_CINZA_ESCURO, c, 2, 0);
		adicinoarBotao("/", COR_LARANJA, c, 3, 0);

		adicinoarBotao("7", COR_CINZA_CLARO, c, 0, 1);
		adicinoarBotao("8", COR_CINZA_CLARO, c, 1, 1);
		adicinoarBotao("9", COR_CINZA_CLARO, c, 2, 1);
		adicinoarBotao("*", COR_LARANJA, c, 3, 1);

		adicinoarBotao("4", COR_CINZA_CLARO, c, 0, 2);
		adicinoarBotao("5", COR_CINZA_CLARO, c, 1, 2);
		adicinoarBotao("6", COR_CINZA_CLARO, c, 2, 2);
		adicinoarBotao("-", COR_LARANJA, c, 3, 2);

		adicinoarBotao("1", COR_CINZA_CLARO, c, 0, 3);
		adicinoarBotao("2", COR_CINZA_CLARO, c, 1, 3);
		adicinoarBotao("3", COR_CINZA_CLARO, c, 2, 3);
		adicinoarBotao("+", COR_LARANJA, c, 3, 3);

		c.gridwidth = 2;
		adicinoarBotao("0", COR_CINZA_CLARO, c, 0, 4);
		c.gridwidth = 1;
		adicinoarBotao(",", COR_CINZA_CLARO, c, 2, 4);
		adicinoarBotao("=", COR_LARANJA, c, 3, 4);
		
	}

	private void adicinoarBotao(String texto, Color cor, GridBagConstraints c, int x, int y) {
		
		c.gridx = x;
		c.gridy = y;
		Botao botao = new Botao(texto, cor);
		botao.addActionListener(this);
		add(botao, c);
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() instanceof JButton) {
			JButton botao = (JButton) e.getSource();
			Memoria.getInstancia().processarComando(botao.getText());
		}
		
	}
	
}
