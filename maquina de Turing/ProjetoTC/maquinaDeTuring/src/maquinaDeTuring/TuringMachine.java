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
package maquinaDeTuring;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;

import componentes.Estado;
import componentes.Fita;
import componentes.Transicao;

/**
 * CLASSE TuringMachine, REALIZA AS OPERACOES DA MAQUINA
 * @author Luan Carlos
 * @version 1.0
 *
 */
public class TuringMachine {

	private HashSet<Estado> estados;
	private Estado estadoInicial;
	private Estado estadoAtual;
	private HashSet<Estado> estadosFinais;
	private int passos;
	private Fita fita;
	private HashMap<String, String> sintaxes;

	/**
	 * CONSTRUTOR DA MAQUINA DE TURING
	 */
	public TuringMachine() {
		this.estados = new HashSet<Estado>();
		this.estadoInicial = new Estado("0");
		this.setEstadoAtual(this.estadoInicial);
		this.estadosFinais = new HashSet<Estado>();
		this.setPassos(0);
		this.setFita(new Fita());
		this.estados.add(estadoInicial);
		sintaxes = new HashMap<>();
		sintaxes.put("Testa Palindromo", "sintaxe1.txt");
		sintaxes.put("Testa Primo", "sintaxe2.txt");
	}

	/**
	 * Retorna as Chaves da colecao de sintaxes padrao da maquina
	 * @return String
	 */
	public String getKeys() {
		String retorno = "";
		for (String nomeSintaxe : sintaxes.keySet()) {
			retorno += nomeSintaxe + ", ";
		}
		return retorno;
	}
	
	/**
	 * Retorna o valor referente a chave do HashMap relativa a uma sintaxe padrao da maquina
	 * @param key String
	 * @return String
	 */
	public String getValor(String key) {
		return sintaxes.get(key);
	}
	
	/**
	 * Verifica se a colecao de sintaxes padrao contem uma sintaxe especifica
	 * @param key String
	 * @return boolean
	 */
	public boolean containsKey(String key) {
		return sintaxes.containsKey(key);
	}
	
	/**
	 * Cria um novo estado
	 * @param state String
	 */
	protected void newState(String state) {
		if (this.searchState(state) == null) {
			Estado novoEstado = new Estado(state);
			estados.add(novoEstado);

			String[] palavra = state.split("");
			if (palavra.length >= 4) {
				String checkHalt = palavra[0] + palavra[1] + palavra[2] + palavra[3];
				if (checkHalt.equals("halt")) {
					estadosFinais.add(novoEstado);
				}
			}
		}
	}

	/**
	 * Procura um estado especifico
	 * @param estado String
	 * @return Estado
	 */
	protected Estado searchState(String estado) {
		for (Estado state : estados) {
			if (state.getNome().equals(estado)) {
				return state;
			}
		}
		return null;
	}

	/**
	 * Adiciona novas transicoes a maquina
	 * @param estado1 String
	 * @param simboloAtual String
	 * @param simboloNovo String
	 * @param direcao String
	 * @param estado2 String
	 */
	public void addTransition(String estado1, String simboloAtual, String simboloNovo, String direcao, String estado2) {
		this.newState(estado1);
		this.newState(estado2);

		Estado e1 = this.searchState(estado1);
		Estado e2 = this.searchState(estado2);

		Transicao transicao = new Transicao(simboloAtual, simboloNovo, direcao, e2);

		e1.addTransicao(transicao);
	}

	/**
	 * Escreve uma nova palavra
	 * @param palavra String
	 */
	public void writeNewWord(String palavra) {
		this.setPassos(0);
		this.setEstadoAtual(this.estadoInicial);
		this.setFita(new Fita());
		this.getFita().escreverPalavra(palavra);
	}

	/**
	 * Roda passo a passo
	 * @throws Exception
	 */
	public void runByStep() throws Exception {
		// Se tiver terminado
		if (estadosFinais.contains(this.getEstadoAtual())) {
			return;
		}
		
		Transicao transicao = this.getEstadoAtual().getTransicao(this.getFita().getSimboloAtual());
		
		if (transicao == null) {
			transicao = this.getEstadoAtual().getTransicao("*");
			
			// Se houver algum problema
			if (transicao == null) {
				throw new Exception("Algo de errado aconteceu! Mude a palavra (3) ou a sintaxe (4).");
			}
		}
		
		if (!transicao.getNovoSimbolo().equals("*")) {
			getFita().escreverSimbolo(transicao.getNovoSimbolo());
		}
		
		getFita().run(transicao.getDirecao());
		
		if (!transicao.getNovoEstado().equals("*")) {
			this.setEstadoAtual(transicao.getNovoEstado());
		}
		
		this.setPassos(this.getPassos() + 1);
	}

	/**
	 * Roda todos os passos possiveis da maquina
	 * @throws Exception
	 */
	public void runMachine() throws Exception {
		while (!this.estadosFinais.contains(getEstadoAtual())) {
			this.runByStep();
		}
	}

	/**
	 * Le uma nova sintaxe e aplica a mesma
	 * @throws IOException
	 */
	public void readFromConsole() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("\nDigite a nova sintaxe seguida de 'end' para continuar: ");
		String in;
		while (!(in = reader.readLine()).trim().equalsIgnoreCase("end")) {
			if (!in.isEmpty() && !in.trim().equals("")) {
				String[] read = in.split(" ");
				if (!read[0].equals(";")) {
					addTransition(read[0], read[1], read[2], read[3], read[4]);
				}
			}

		}
		
	}

	/**
	 * Reseta a maquina
	 */
	public void reset() {
		this.estados = new HashSet<Estado>();
		this.estadoInicial = new Estado("0");
		this.setEstadoAtual(this.estadoInicial);
		this.estadosFinais = new HashSet<Estado>();
		this.setPassos(0);
		this.setFita(new Fita());
		this.estados.add(estadoInicial);
	}
	/**
	 * Le um arquivo que contem sintaxe
	 * @param arquivo String
	 * @throws IOException
	 */
	public void readFile(String arquivo) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(arquivo));
		String line;

		while ((line = in.readLine()) != null) {
			if (!line.isEmpty()) {
				String[] read = line.split(" ");
				if (!read[0].equals(";")) {
					addTransition(read[0], read[1], read[2], read[3], read[4]);
				}
			}

		}
		
		in.close();
	}

	/**
	 * Retorna a quantidade de passos
	 * @return Integer
	 */
	public int getPassos() {
		return passos;
	}

	/**
	 * Altera a quantidade de passos
	 * @param passos Integer
	 */
	public void setPassos(int passos) {
		this.passos = passos;
	}

	/**
	 * Retorna o estado Atual que maquina esta
	 * @return Estado
	 */
	public Estado getEstadoAtual() {
		return estadoAtual;
	}

	/**
	 * Altera o estado atual da maquina
	 * @param estadoAtual Estado
	 */
	public void setEstadoAtual(Estado estadoAtual) {
		this.estadoAtual = estadoAtual;
	}

	/**
	 * Retorna a Fita atual da maquina
	 * @return Fita
	 */
	public Fita getFita() {
		return fita;
	}

	/**
	 * Altera a fita atual da maquina
	 * @param fita Fita
	 */
	public void setFita(Fita fita) {
		this.fita = fita;
	}

}
