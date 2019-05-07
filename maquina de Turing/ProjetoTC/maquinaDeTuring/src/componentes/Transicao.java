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

public class Transicao {

	public String simboloAtual;
	public String novoSimbolo;
	public String direcao;
	public Estado novoEstado;
	
	public Transicao(String simboloAtual, String novoSimbolo, String direcao, Estado novoEstado) {
		this.simboloAtual = simboloAtual;
		this.novoSimbolo = novoSimbolo;
		this.direcao = direcao;
		this.novoEstado = novoEstado;
	}

	public Estado getNovoEstado() {
		return novoEstado;
	}

	public void setNovoEstado(Estado novoEstado) {
		this.novoEstado = novoEstado;
	}

	public String getSimboloAtual() {
		return simboloAtual;
	}

	public void setSimboloAtual(String simboloAtual) {
		this.simboloAtual = simboloAtual;
	}

	public String getNovoSimbolo() {
		return novoSimbolo;
	}

	public void setNovoSimbolo(String novoSimbolo) {
		this.novoSimbolo = novoSimbolo;
	}

	public String getDirecao() {
		return direcao;
	}

	public void setDirecao(String direcao) {
		this.direcao = direcao;
	}
	
	
	
}
