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
package operador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import maquinaDeTuring.TuringMachine;

public class Operador {
	
	private final String LS = System.lineSeparator();
	private Scanner sc = new Scanner(System.in);
	private TuringMachine turingMachine = new TuringMachine();
	public static Operador instance;
	
	
	// Apenas uma unica maquina pode ser criada!
	private Operador() {			
	}
	
	public static synchronized Operador getInstance() {
		if (instance == null) {
			instance = new Operador();
		}

		return instance;
	}
	
	
	public void showMenu() throws Exception {
		showMenu(-1);
	}
	
	private void showMenu(int opcao) throws Exception {
		
		if(opcao != 5) {
			System.out.println(LS + "Opcoes de operacoes:");
			System.out.println("1) Rodar ate o fim.");
			System.out.println("2) Rodar o proximo passo.");
			System.out.println("3) Mudar a palavra.");
			System.out.println("4) Mudar a sintaxe.");
			System.out.println("5) Sair.");
			System.out.print("Digite a opcao desejada: ");
			
			int option = sc.nextInt();
			sc.nextLine();
			
			if(option != 5) {
				runAplication(option);
			}
			
			showMenu(option);
			
		} else {
			System.out.println("Maquina de Turing desligada!");
			return;
		}
	}
	
	public void runAplication(int option) throws Exception {
		
		// Rodar a maquina ate o fim
		if (option == 1) {
			
			try {
				turingMachine.runMachine();
				
			} catch(Exception e) {
				System.out.println(e.getMessage());
			}
		
		// Rodar o proximo passo
		} else if (option == 2) {
			
			try {
				turingMachine.runByStep();
	
			} catch(Exception e) {
				System.out.println(e.getMessage());
			}
		
		//Mudar a palavra
		} else if(option == 3) {
			
			System.out.print(LS + "Digite a palavra: ");
			String novaPalavra = sc.nextLine();
			turingMachine.writeNewWord(novaPalavra);
			
		//Mudar a sintaxe
		} else if (option == 4) {
			// Resenta a sintaxe
			turingMachine.reset();			
			// Pega a nova sintaxe
			System.out.printf("(U)sar sintaxes padrao ou (I)nserir Nova? ");
			String opcao = sc.nextLine(); 
			if(opcao.equalsIgnoreCase("U")) {
				System.out.println(turingMachine.getKeys());
				opcao = sc.nextLine();
				if(turingMachine.containsKey(opcao)) {
					turingMachine.readFile(turingMachine.getValor(opcao));
				}else {
					throw new Exception("Erro na mudanca de Sintaxe: Sintaxe padrao invalida");
				}
			}else if(opcao.equalsIgnoreCase("i")) {
				turingMachine.readFromConsole();
			}else {
				throw new Exception("Erro na mudanca de Sintaxe: Opcao Invalida");
			}
			System.out.print(LS + "Digite a palavra: ");
			String novaPalavra = sc.nextLine();
			turingMachine.writeNewWord(novaPalavra);
		}
		
		System.out.println(showEverything());
	}
	
	public String showEverything() {
		return showRibbon() + LS + showHead() + LS + showActualState() + LS + showSteps();
	}

	private String showRibbon() {
		String toString = "";
		ArrayList<String> fita = turingMachine.getFita().palavra;
		
		for(String string : fita) {
			if(string.equals("_")) 
				toString += " ";
			else toString += string;
		}
		
		return "Fita: " + toString;
		
	}
	
	private String showHead() {
		String toString = "";
		String[] array = new String[turingMachine.getFita().palavra.size()];
		for (int i = 0; i < array.length; i++) {
			array[i] = " ";
		}
		array[turingMachine.getFita().cabecote] = "^";
		
		for (int i = 0; i < array.length; i++) {
			toString += array[i];
		}
		
		return "Head: " + toString;
	}
	
	private  String showActualState() {
		return "Estado atual: " + turingMachine.getEstadoAtual().getNome();
	}
	
	private String showSteps() {
		return "Numero de Passos: " + turingMachine.getPassos();
	}
	
	public void init() throws IOException {
		
		System.out.println("Maquina de Turing!");
		turingMachine.readFile("sintaxe2.txt");
		System.out.print(LS + "Digite a palavra: ");
		turingMachine.writeNewWord(sc.nextLine());
		System.out.println(showEverything());
	}
}
