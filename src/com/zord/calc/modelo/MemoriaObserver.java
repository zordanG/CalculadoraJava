package com.zord.calc.modelo;

@FunctionalInterface
public interface MemoriaObserver {

	void valorAlterado(String novoValor);
	
}
