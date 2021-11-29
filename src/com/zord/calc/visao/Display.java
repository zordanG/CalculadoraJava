package com.zord.calc.visao;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.zord.calc.modelo.Memoria;
import com.zord.calc.modelo.MemoriaObserver;

@SuppressWarnings("serial")
public class Display extends JPanel implements MemoriaObserver{
	
	final private JLabel label;
	
	Display(){
		
		Memoria.getInstancia().adcionarObserver(this);
		
		setBackground(new Color(46,49,50));
		label = new JLabel(Memoria.getInstancia().getTextoAtual());
		label.setForeground(Color.WHITE);
		label.setFont(new Font("courier", Font.PLAIN, 30));
		
		setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 25));
		
		add(label);
		
	}

	@Override
	public void valorAlterado(String novoValor) {
		
		label.setText(novoValor);
		
	}

}
