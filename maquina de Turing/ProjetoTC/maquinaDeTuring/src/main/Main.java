/**
 * UFCG - Universidade Federal de Campina Grande
 * TEORIA DA COMPUTACAO - PROFº KYLLER COSTA
 * CURSO - CIENCIA DA COMPUTACAO
 * LUAN CARLOS
 * REDSON FARIAS
 * LUCAS ANTHONY
 * TIBERIO GADELHA
 * CAIO BENJAMIN
 * MATHEUS THIAGO
 */
package main;

import operador.Operador;

public class Main {
	
	public static Operador operation;
	
	public static void main(String[] args) throws Exception {
		operation = Operador.getInstance();
		
		operation.init();
		operation.showMenu();
	}

}
