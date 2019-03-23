/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reto4;

import java.util.Scanner;

/**
 *
 * @author Ingenieria
 */
public class Reto4 {
    public static String[] palabras;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[][] sopa = initSopa();
        initPalabras();
        printSopa(sopa);
        System.out.println("Ingrese la posicion inicial");
        int inicialPosA = sc.nextInt();    
        int inicialPosB = sc.nextInt();  
        System.out.println("Ingrese la posicion final");
        int finalPosA = sc.nextInt();
        int finalPosB = sc.nextInt();
        buscarPalabra(inicialPosA, inicialPosB, finalPosA, finalPosB,sopa);       
    }
    
   
    /**
     * Metodo para imprimir la sopa de letras
     * @param sopa Matriz a imprimir
     */
    private static void printSopa(char[][] sopa){
        for (int i = 0; i < sopa[0].length; i++) {
            for (int j = 0; j < sopa[0].length; j++) {
                System.out.print("[ "+sopa[i][j]+" ] ");
            }
            System.out.println("");
        }
    }

    /**
     * Metodo para determinar que tipo de Busqueda se va a realizar.
     * Para el reto 4 solo se implemanta horizontal.
     * Posibilidad de escalar luego.
     * @param inicialPosA
     * @param inicialPosB
     * @param finalPosA
     * @param finalPosB
     * @param sopa 
     */
    private static void buscarPalabra(int inicialPosA, int inicialPosB, int finalPosA, int finalPosB, char[][] sopa) {
        if(inicialPosA == finalPosA){
            solucionarHorizontal(inicialPosB, finalPosB, sopa[inicialPosA]);
        }
        else{
            System.out.println("No es una linea horizontal");
        }
    }

    /**
     * Metodo para buscar una palabra horizontal
     * @param inicialPosB
     * @param finalPosB
     * @param linea 
     */
    private static void solucionarHorizontal(int inicialPosB, int finalPosB, char[] linea) {
        String lineaString = new String(linea);
        int maxIndex = Math.max(inicialPosB, finalPosB)+1;
        int minIndex = Math.min(inicialPosB, finalPosB);
        lineaString = lineaString.substring(minIndex, maxIndex);
        for (String palabra : palabras) {
            if (palabra.length() == lineaString.length()) {
                if(palabra.charAt(0) == lineaString.charAt(0)){
                    if (palabra.equalsIgnoreCase(lineaString)) {
                        System.out.println("Se encontro la palabra "+palabra);
                        System.out.println(comprobarPalindroma(lineaString));
                        return;
                    }
                }
                else if(lineaString.charAt(lineaString.length()-1) == palabra.charAt(0)){
                    if(comprobarEnReversa(lineaString, palabra)){
                        System.out.println("Se encontro la palabra "+palabra);
                        System.out.println(comprobarPalindroma(palabra));
                        return;
                    }
                }
            }
        }
        System.out.println("No se encontro ninguna palabra");
    }
    
    /**
     * Metodo que revisa si una palabra esta escrita al reves en la sopa.
     * Funciona en palabras no palindromas
     * @param lineaString
     * @param palabra
     * @return 
     */
    private static boolean comprobarEnReversa(String lineaString, String palabra) {
        int j = 0;
        for(int i = lineaString.length()-1; i >= 0; i--){
            if(lineaString.charAt(i)!= palabra.charAt(j))
                return false;
            j++;
        }
        return true;
    }    
    
    /**
     * Metodo que comprueba si una palabra es palindroma 
     * @param lineaString
     * @return String con mensaje informativo
     */
    private static String comprobarPalindroma(String lineaString) {
        int j = 0;
        for(int i = lineaString.length()-1; i >= lineaString.length()/2; i--){
            if(lineaString.charAt(i)!= lineaString.charAt(j))
                return "La palabra "+lineaString+" no es palindroma ";
            j++;
        }
        return "La palabra "+lineaString+" es palindroma ";
    }

    /**
     * Metodo para quemar datos de la sopa
     * @return 
     */
    private static char[][] initSopa() {
        char[][] sopa = new char[10][];
        char lineaUno[]  = {'T','E','C','N','O','L','O','G','I','A'};
        char lineaDos[]  = {'V','W','B','I','S','E','R','E','S','F'};
        char lineaTres[]  = {'O','R','E','C','O','N','O','C','E','R'};
        char lineaCuatro[]  = {'W','I','W','R','Z','O','F','Q','O','N'};
        char lineaCinco[]  = {'P','E','Y','R','A','D','A','R','V','O'};
        char lineaSeis[]  = {'I','N','D','U','S','T','R','I','A','D'};
        //char lineaSeis[]  = {'A','I','R','T','S','U','D','N','I','D'};
        char lineaSiete[]  = {'F','C','F','E','U','Q','H','B','C','A'};
        char lineaOcho[]  = {'Z','I','M','G','E','T','K','B','I','C'};
        char lineaNueve[]  = {'J','A','V','A','M','R','H','G','O','I'};
        char lineaDiez[]  = {'S','O','M','E','T','E','M','O','S','S'};
        sopa[0] = lineaUno;     
        sopa[1] = lineaDos;     
        sopa[2] = lineaTres;     
        sopa[3] = lineaCuatro;     
        sopa[4] = lineaCinco;     
        sopa[5] = lineaSeis;     
        sopa[6] = lineaSiete;     
        sopa[7] = lineaOcho;     
        sopa[8] = lineaNueve;   
        sopa[9] = lineaDiez;
        return sopa;
    }
    
     /**
     * Metodo para quemar las palabras que se pueden encontrar en la sopa
     */
    private static void initPalabras() {
        palabras = new String[7];
        palabras[0] = "Tecnologia".toUpperCase();
        palabras[1] = "Reconocer".toUpperCase();
        palabras[2] = "Industria".toUpperCase();
        palabras[3] = "Java".toUpperCase();
        palabras[4] = "Sometemos".toUpperCase();
        palabras[5] = "Radar".toUpperCase();
        palabras[6] = "Seres".toUpperCase();
    }    
}