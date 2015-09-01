package battleship;

import java.util.Scanner;

public class Battleship {
	//matrica za smijestanje polozaja brodova
	//brodovi se ne generisu u krajnjim redovima i kolonama
	static char[][] tabla = new char[12][12];
	//matrica za vodjenje evidencije o gadjanju korisnika
	static char[][] tablaGadjanja = new char [10][10];
	static int brojPogodaka = 0;
	
	public void igra() {
		
		boolean isOn = true;
		//kreiranje rasporeda brodova za gadjanje
		kreirajRaspored();
		//stampanje table gadjanja
		stampajTablu();
		//petlja se vrti dok se igra ne zavrsi
		while(isOn){
			//unos koordinata za poziciju koju gadjamo
			gadjaj();
			//provjera da li su svi brodovi potopljeni sto predstavlja uslov za kraj igre
			if(brojPogodaka == 20){
				isOn = false;
			}
			//stampanje table gadjanja
			stampajTablu();
		}
		//stampanje table sa brodovima radi provjere
		stampajRijesenje();
	}

	/*
	 * metoda za kreiranje rasporeda protivnickih brodova
	 */
	public void kreirajRaspored() {
		//popunjavanje table za gadjanje praznim mjestima
		for(int i = 0; i < 10; i++){
			for(int j = 0; j < 10; j++){
				tablaGadjanja[i][j] = '-';
			}
		}
		//popunjavanje table sa brodovima praznim mjestima
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 12; j++) {
				tabla[i][j] = '-';
			}
		}
		//petlja se vrti dok se ne upisu svi brodovi
		for(int i = 0; i < 10; i++){
			//upis jednog broda duzine 4
			if(i == 0){
				upisiBrod(4);
			//upis dva broda duzine 3
			}else if(i > 0 && i <= 2){
				upisiBrod(3);
			//upis tri broda duzine 2
			}else if(i > 2 && i <= 5){
				upisiBrod(2);
			//upis cetri broda duzine 1 
			}else{
				upisiBrod(1);
			}
		}

	}

	/*
	 * metoda za upis broda na tablu
	 */
	public void upisiBrod(int duzina) {
		boolean uslov = true;
		int x, y;
		//odredjivanje da li ce se upisivati horizontalno(0) ili vertikalno(1)
		int smijer = (int) (Math.random() * 2);
		if (smijer == 0) {
			//postavljanje broda horizontalno
			do {
				//generisanje koordinata x i y
				x = (int) (Math.random() * 10) + 1;
				y = (int) (Math.random() * (11 - duzina)) + 1;
				//provjera da li su mjesta gdje brod treba biti upisana prazna
				for (int i = y; i < y + duzina; i++) {
					if(tabla[x][i] != '-'){
						break;
					}
					//ako su polja prazna, znaci da se brod moze upisati na ovu poziciju
					//izlazi se iz petlje
					if(i == y + duzina - 1){
						uslov = false;
					}
				}		
			} while (uslov);
				//postavljanje zastite oko broda jer po pravilima brodovi ne smiju biti jedan do drugog
				for (int i = x - 1; i <= x + 1; i++) {
					for (int j = y - 1; j < y + (duzina + 1); j++) {
						tabla[i][j] = 'z';
					}
				}
				// upisivanje broda
				for (int i = y; i < y + duzina; i++) {
					tabla[x][i] = 'O';
				}
		} else {
			//postavljanje broda vertikalno
			do{
				//generisanje koordinata x i y
				x = (int) (Math.random() * (11 - duzina)) + 1;
				y = (int) (Math.random() * 10) + 1;
				//provjera da li su mjesta gdje brod treba biti upisana prazna
				for (int i = x; i < x + duzina; i++) {
					if(tabla[i][y] != '-'){
						break;
					}
					//ako su polja prazna, znaci da se brod moze upisati na ovu poziciju
					//izlazi se iz petlje
					if(i == x + duzina - 1){
						uslov = false;
					}
				}
				
			}while(uslov);

			//postavljanje zastite oko broda jer po pravilima brodovi ne smiju biti jedan do drugog
			for (int i = x - 1; i < x + (duzina + 1); i++) {
				for (int j = y - 1; j <= y + 1; j++) {
					tabla[i][j] = 'z';
				}
			}
			// upisivanje broda
			for (int i = x; i < x + duzina; i++) {
				tabla[i][y] = 'O';
			}
		}
	}
	/*
	 * metoda za gadjanje
	 */
	public void gadjaj(){
		boolean uslov = true;
		int x, y;
		Scanner in = new Scanner(System.in);
		//unos koordinata od strane korisnika
		do{
			System.out.println("Unesi koordinate");
			y = in.nextInt();
			x = in.nextInt();
			
			if(x >= 0 && y >= 0 && x <= 9 && y <= 9){
				uslov = false;
			}
		}while(uslov);
			//provjera da li je korisnik pogodio brod
			if(tabla[x + 1][y + 1] == 'O'){
				//ako isto mjesto nije gadjano i ranije
				//povecava se broj pogodaka
				if(tablaGadjanja[x][y] != 'O'){
					brojPogodaka++;					
				}
				//prikazu je se pogodjeni dio broda
				tablaGadjanja[x][y] = 'O';
			}
			//ako korisnik nije pogodio brod, upisuje se znak 'x' sto oznacava promasaj
			else{
				tablaGadjanja[x][y] = 'x';
			}
		
	}
	
	/*
	 * metoda za stampanje table gadjanja
	 */
	public void stampajTablu() {
		System.out.println("   0 1 2 3 4 5 6 7 8 9");
		for (int i = 0; i < 10; i++) {
			System.out.print(i + "  ");
			for (int j = 0; j < 10; j++) {
				System.out.print(tablaGadjanja[i][j] + " ");
			}
			System.out.println();
		}
	}
	/*
	 * metoda za stampanje rijesenja
	 */
	public void stampajRijesenje(){
		
		System.out.println("\nRijesenje:\n   0 1 2 3 4 5 6 7 8 9");
		for(int i = 1; i < 11; i++){
			System.out.print((i - 1) + "  ");
			for(int j = 1; j < 11; j++){
				if(tabla[i][j] == 'O'){
					System.out.print('O' + " ");
				}
				else{
					System.out.print('-' + " ");
				}
			}
			System.out.println();
		}
	}
		
}
