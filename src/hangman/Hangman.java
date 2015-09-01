package hangman;

import java.util.ArrayList;
import java.util.Scanner;

public class Hangman {

	public void igra(){
		
		Scanner in = new Scanner(System.in);
		
		boolean isOn = true;
		int brojGresaka = 0;
		//pripremanje igre pozivanjem metode za pripremu
		char[][] matrica = pripremiIgru();
		//stampanje donjih crta, koje predstavljaju skrivenu rijec
		for(int i = 0; i < matrica.length; i++){
			System.out.print("_ ");
		}
		//petlja se vrti dok se rijec ne pronadje ili napravi previse gresaka
		while(isOn){
			boolean karakterPostoji = false;
			//unos slova od strane korisnika
			System.out.println("\n\nUnesite slovo");
			char karakter = in.next().toUpperCase().charAt(0);
			//prolazak kroz matricu, trazi se slovo
			for(int i = 0; i < matrica.length; i++){
				//ako se uneseno slovo nalazi u rijeci, slovo se 'otkriva' prilikom stampanja
				if(matrica[i][0] == karakter){
					matrica[i][1] = '1';  //oznacavanje da karakter postoji
					karakterPostoji = true;
				}
			}
			//ako uneseno slovo ne postoji u rijeci, broj gresaka se povecava
			if(!karakterPostoji){
				brojGresaka++;
			}
			//pozivanje metoda za crtanje covjeculjka koji predstavlja broj gresaka
			crtajCovjeculjka(brojGresaka);
			//stampanje rijeci
			for(int i = 0; i < matrica.length; i++){
				//ako slovo do sad nije pogodjeno, stampa se donja crta
				if(matrica[i][1] == '0'){
					System.out.print("_ ");
				}
				//ako je slovo pogodjeno, stampa se slovo
				else{
					System.out.print(matrica[i][0] + " ");
				}
			}
			//provjeravanje da li je igra gotova
			if(isOver(matrica, brojGresaka)){
				isOn = false;
			}
		}
		//stampanje trazene rijeci
		System.out.println("\n\nTrazena rijec: ");
		for(int i = 0; i < matrica.length; i++){
			System.out.print(matrica[i][0]);
		}
		in.close();
		
		
	}
	
	/*
	 * metoda za pripremu igre
	 */
	public char[][] pripremiIgru(){
		//uzimanje rijeci pozivanjem metode
		String nepoznataRijec = izaberiRijec().toUpperCase();
		//matrica za smijestanje slova rijeci i vodjenje evidencije da li je slovo pogodjeno
		char[][] matrica = new char[nepoznataRijec.length()][2];
		//u prvi red matrice smijestaju se slova rijeci
		for(int i = 0; i < nepoznataRijec.length(); i++){
			char karakter = nepoznataRijec.charAt(i);
			matrica[i][0] = karakter;
		}
		//u drugi red matrice smijestaju se karakteri '0', koji oznacavaju da slova sa istim indeksom kolone nisu pogodjena
		for(int i = 0; i < nepoznataRijec.length(); i++){
			matrica[i][1] = '0';
		}
		return matrica;
	}
	/*
	 * metoda za izbor rijeci
	 * na pocetku zamisljeno da cita rijeci iz fajla, ali sam odustao da ne komplikujem dzaba
	 */
	public String izaberiRijec(){
		//lista rijeci
		ArrayList<String> listaRijeci = new ArrayList<>();
		//ubacivanje rijeci u listu
		listaRijeci.add("lubenica");
		listaRijeci.add("tikva");
		listaRijeci.add("mrkva");
		listaRijeci.add("paradajz");
		//nasumican izbor rijeci iz liste
		return listaRijeci.get((int)(Math.random() * listaRijeci.size()));
	}
	/*
	 * metoda provjerava da li je igra gotova
	 */
	public boolean isOver(char[][] matrica, int brojGresaka){
		boolean isOver = true;
		//prolazak kroz matricu
		for(int i = 0; i < matrica.length; i++){
			//ako postoji barem jedno nepogodjeno slovo i broj gresaka manji je od 6, igra se nastavlja
			if(matrica[i][1] == '0' && brojGresaka < 6){
				isOver = false;
				break;
			}
		}
		
		return isOver;
	}
	/*
	 * metoda za stampanje covjeculjka
	 */
	public void crtajCovjeculjka(int brojGresaka){
		
		char[][] crtez = new char[6][6];
		//ubacivanje u matricu osnovnih elemenata (vjesalo)
		for(int i = 0; i < 6; i++){
			crtez[i][1] = '|';
		}
		for(int i = 1; i < 5; i++){
			crtez[0][i] = '_';
		}
		crtez[1][4] = '|';
		
		//kako broj gresaka raste, tako se dijelovi tijela dodaju u matricu
		switch(brojGresaka){
		case 1:{
			crtez[2][4] = '0';
			break;
		}
		case 2:{
			crtez[2][4] = '0';
			crtez[3][4] = '|';
			break;
		}
		case 3:{
			crtez[2][4] = '0';
			crtez[3][4] = '|';
			crtez[2][3] = '\\';
			break;
		}
		case 4:{
			crtez[2][4] = '0';
			crtez[3][4] = '|';
			crtez[2][3] = '\\';
			crtez[2][5] = '/';
			break;
		}
		case 5:{
			crtez[2][4] = '0';
			crtez[3][4] = '|';
			crtez[2][3] = '\\';
			crtez[2][5] = '/';
			crtez[4][3] = '/';
			break;
		}
		case 6:{
			crtez[2][4] = '0';
			crtez[3][4] = '|';
			crtez[2][3] = '\\';
			crtez[2][5] = '/';
			crtez[4][3] = '/';
			crtez[4][5] = '\\';
			break;
		}
		}
		//stampanje matrice
		for(int i = 0; i < 6; i++){
			for(int j = 0; j < 6; j++){
				System.out.print(crtez[i][j]);
			}
			System.out.println();
		}		
	}
}
