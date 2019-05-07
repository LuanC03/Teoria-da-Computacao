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
package componentes;

import java.util.ArrayList;

public class Fita {
	public ArrayList<String> palavra;
	public int cabecote;

	public Fita() {
		this.palavra = new ArrayList<String>();
		this.cabecote = 0;
	}

	public void run(String direcao) {
		if(direcao.equals("l")) {
			this.runToTheLeft();
		} else if (direcao.equals("r")) {
			this.runToTheRight();
		}
	}
	
	public void escreverSimbolo(String simbolo){
		palavra.set(cabecote, simbolo);
	}
	
	public void runToTheLeft(){
		if (cabecote == 0) {
			this.cabecote++;
			this.palavra.add(0, "_");
		}
		cabecote--;
	}
	
	public void runToTheRight(){
		if (cabecote == this.palavra.size() - 1) {
			this.palavra.add("_");
		}
		cabecote++;
	}
	
	public ArrayList<String> getPalavra(){
		return palavra;
	}
	
	public String getSimboloAtual() {
		return palavra.get(cabecote);
	}
	
	public void escreverPalavra(String palavra) {
		String[] saida = palavra.split("");
		for (int i = 0; i < saida.length; i++) {
			if(saida[i].equals(" ")) {
				this.palavra.add("_");
			} else {
				this.palavra.add(saida[i]);
			}
		}
		this.cabecote = 0;
	}
	
}
