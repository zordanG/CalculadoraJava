package com.zord.calc.modelo;

import java.util.ArrayList;
import java.util.List;

public class Memoria {

	private enum TipoComando{
		ZERAR, NUM, DIV, MULT, SOMA, SUB, IGUAL, VIRG, INVER;
	};
	
	private static final Memoria instancia = new Memoria();
	private String textoAtual = "", textoBuffer = "";
	private boolean substituir = false;
	private TipoComando ultimaOperacao = null;
	private List<MemoriaObserver> observadores = new ArrayList<>();
	
	private Memoria() {
		
	}

	public static Memoria getInstancia() {
		return instancia;
	}
	
	public void adcionarObserver(MemoriaObserver o) {
		observadores.add(o);
	}

	public String getTextoAtual() {
		return textoAtual.isEmpty() ? "0" : textoAtual;
	}
	
	public void processarComando(String texto) {
		
		TipoComando tipo = detectarTipoComando(texto);
		
		if(tipo == null) {
			return;
		}else if(tipo == TipoComando.ZERAR) {
			textoAtual = "";
			textoBuffer = "";
			substituir = false;
			ultimaOperacao = null;
		}else if(tipo == TipoComando.INVER){
			textoAtual = Integer.toString(-1*Integer.parseInt(textoAtual));
		}else if(tipo == TipoComando.NUM || tipo == TipoComando.VIRG) {
		
			if(textoAtual.length()==0 && tipo == TipoComando.VIRG) {
				textoAtual = "0";
			}	
			textoAtual = substituir ? texto : textoAtual + texto;
			substituir = false;
		}else {
			substituir = true;
			textoAtual = obterResultadoOperacao();
			ultimaOperacao = tipo;
			textoBuffer = textoAtual;
		}
		
		observadores.forEach(o -> o.valorAlterado(getTextoAtual()));
	}

	private String obterResultadoOperacao() {
		
		if(ultimaOperacao == null || ultimaOperacao == TipoComando.IGUAL) {
			return textoAtual;	
		}
		
		double numeroBuffer = Double.parseDouble(textoBuffer.replace(",", "."));
		double numeroAtual = Double.parseDouble(textoAtual.replace(",", "."));
		double resultado = 0;
		
		switch(ultimaOperacao) {
		case SOMA:
			resultado = numeroBuffer+numeroAtual;
			break;
		case SUB:
			resultado = numeroBuffer-numeroAtual;
			break;
		case DIV:
			resultado = numeroBuffer/numeroAtual;
			break;
		case MULT:
			resultado = numeroBuffer*numeroAtual;
		default:
			break;
		}
		
		String resultadoString = Double.toString(resultado).replace(".",",");
		boolean inteiro = resultadoString.endsWith(",0");
		
		return inteiro ? resultadoString.replace(",0", "") : resultadoString;
	}

	private TipoComando detectarTipoComando(String texto) {
		
		if(textoAtual.isEmpty() && texto == "0") {
			return null;
		}
		
		try{
			Integer.parseInt(texto);
			return TipoComando.NUM;
		}catch(NumberFormatException e) {
			switch(texto) {
			case "AC":
				return TipoComando.ZERAR;
			case "/":
				return TipoComando.DIV;
			case "*":
				return TipoComando.MULT;
			case "+":
				return TipoComando.SOMA;
			case "-":
				return TipoComando.SUB;
			case "=":
				return TipoComando.IGUAL;
			case ",":
				if(!textoAtual.contains(",")) {
					return TipoComando.VIRG;
				}
			case "±":
				return TipoComando.INVER;
			}
		}
		
		return null;
	}
	
}
