/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reto4;

import java.util.Arrays;
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
     * Implementada busqueda vertical y diagonal para el reto 5
     * Posibilidad de escalar luego.
     * @param inicialPosA
     * @param inicialPosB
     * @param finalPosA
     * @param finalPosB
     * @param sopa 
     */
    private static void buscarPalabra(int inicialPosA, int inicialPosB, int finalPosA, int finalPosB, char[][] sopa) {
        if(inicialPosA == finalPosA){
            busquedaHorizontal(inicialPosB, finalPosB, sopa[inicialPosA]);
        }
        else if(inicialPosB == finalPosB){
            busquedaVertical(inicialPosA, finalPosA, sopa, inicialPosB);
        }
        else{
            if(verDiagonalidad( inicialPosA, finalPosA,  inicialPosB, finalPosB)){
                busquedaDiagonal(inicialPosA, inicialPosB, finalPosA, finalPosB,sopa);
            }
            else{
                System.out.println("No es una coordenada valida");
            }
        }
    }
    
    /**
     * Metodo para realizar la busqueda horizontal
     * @param inicialPosB
     * @param finalPosB
     * @param sopa 
     */
    private static void busquedaHorizontal(int inicialPosB, int finalPosB, char[] sopa) {
        int maxIndex = Math.max(inicialPosB, finalPosB)+1;
        int minIndex = Math.min(inicialPosB, finalPosB);
        char[] linea = Arrays.copyOfRange(sopa, minIndex, maxIndex);
        verificar(linea);
    }
    
    /**
     * Metodo para realizar la busqueda vertical
     * @param inicialPosA
     * @param finalPosA
     * @param sopa
     * @param inicialPosB 
     */
    private static void busquedaVertical(int inicialPosA, int finalPosA, char[][] sopa, int inicialPosB) {
       int maxIndex = Math.max(inicialPosA, finalPosA)+1;
        int minIndex = Math.min(inicialPosA, finalPosA);
        char linea[] = new char[maxIndex-minIndex];
        int j = minIndex;
        for (int i = 0; i < linea.length; i++) {
            linea[i] = sopa[j][inicialPosB];
            j++;
        }
        verificar(linea);
    }
    
    /**
     * Metodo para realizar la busqueda Diagonal
     * @param inicialPosA
     * @param inicialPosB
     * @param finalPosA
     * @param finalPosB
     * @param sopa 
     */
    private static void busquedaDiagonal(int inicialPosA, int inicialPosB, int finalPosA, int finalPosB, char[][] sopa){
        int maxIndexA = Math.max(inicialPosA, finalPosA)+1;
        int minIndexA = Math.min(inicialPosA, finalPosA);
        int minIndexB = Math.min(inicialPosB, finalPosB);
        int diferencia = maxIndexA - minIndexA;
        char linea[] = new char[diferencia];
        if((inicialPosA < finalPosA && inicialPosB < finalPosB)||(inicialPosA > finalPosA && inicialPosB > finalPosB)){
            int x = minIndexA;
            int y = minIndexB;
            for (int i = 0; i < linea.length; i++) {
                linea[i] = sopa[x][y];
                x++;
                y++;
            }
        }
        else{
            int x = maxIndexA-1;
            int y = minIndexB;
            for (int i = 0; i < linea.length; i++) {
                linea[i] = sopa[x][y];
                x--;
                y++;
            }
        }                
        System.out.println(Arrays.toString(linea));
        verificar(linea);
    }
    
    /**
     * Metodo que valida que dos puntos sean diagonales
     * @param inicialPosA
     * @param finalPosA
     * @param inicialPosB
     * @param finalPosB
     * @return 
     */
    private static boolean verDiagonalidad(int inicialPosA, int finalPosA, int inicialPosB, int finalPosB) {
        int maxIndexA = Math.max(inicialPosA, finalPosA)+1;
        int minIndexA = Math.min(inicialPosA, finalPosA);
        int maxIndexB = Math.max(inicialPosB, finalPosB)+1;
        int minIndexB = Math.min(inicialPosB, finalPosB);
        return (maxIndexA-minIndexA) == (maxIndexB-minIndexB);
    }

    /**
     * Metodo para verificar si la palabra esta contenida
     * @param linea 
     */
    private static void verificar(char[] linea) {
        String lineaString = new String(linea);
        System.out.println(linea);
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
        char lineaDos[]  = {'V','W','B','I','V','R','Q','H','N','F'};
        char lineaTres[]  = {'O','C','E','J','T','I','E','O','N','W'};
        char lineaCuatro[]  = {'W','I','W','R','Z','O','F','Q','O','N'};
        char lineaCinco[]  = {'P','E','Y','D','V','Y','N','N','V','O'};
        char lineaSeis[]  = {'I','N','D','U','S','T','R','I','A','D'};
        //char lineaSeis[]  = {'A','I','R','T','S','U','D','N','I','D'};
        char lineaSiete[]  = {'F','C','F','E','U','Q','H','B','C','A'};
        char lineaOcho[]  = {'Z','I','M','G','E','T','K','B','I','C'};
        char lineaNueve[]  = {'J','A','V','A','M','R','H','G','O','I'};
        char lineaDiez[]  = {'D','K','I','Y','T','I','H','A','N','S'};
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
        palabras = new String[4];
        palabras[0] = "TECNOLOGIA";
        palabras[1] = "CIENCIA";
        palabras[2] = "INNOVACION";
        palabras[3] = "JAVA";
    }   
}