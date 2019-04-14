/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reto4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Ingenieria
 */
public class Reto4 {
    public static String[] palabras = new String[5];
    public static Scanner sc = new Scanner(System.in);
    public static ArrayList posVertical = new ArrayList();
    public static ArrayList posHorizontal = new ArrayList();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        char[][] sopa = initSopa();
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
     * Metodo para crear datos de la sopa
     * @return 
     */
    private static char[][] initSopa() {
        char[][] sopa = new char[10][10];
        for (int i = 0; i < 5; i++) {
            System.out.println("ingrese la palabra numero "+(i+1)+"/5 de la sopa de letras");
            String palabra = sc.nextLine();
            System.out.println("Seleccione la forma de escritura");
            System.out.println("1. Vertical\n2. Horizontal\n3. Diagonal"); 
            int seleccion = Integer.parseInt(sc.nextLine());
            agregarPalabra(palabra,seleccion, sopa);
            palabras[i] = palabra;
            printSopa(sopa);
        }
        llenarEspacios(sopa);
        return sopa;
    }
    
    /**
     * Metodo para agregar caracteres a la sopa
     * @param sopa 
     */
    private static void llenarEspacios(char[][] sopa) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if(sopa[i][j] == '\u0000'){
                    int valor = (int) (Math.random() * 26);
                    char letra = (char)((char) valor+'A');
                    sopa[i][j] = letra;
                }
            }
        }
    }  
    
    /**
     * Metodo que agrega las palabras ingresadas por el usuario a la sopa
     * caso 1, verticales
     * caso 2, horizontales
     * caso 3, diagonales
     * probabilidad de palabra inversa 30%
     * @param palabra
     * @param seleccion
     * @param sopa 
     */
    public static void agregarPalabra(String palabra, int seleccion, char[][] sopa) {
        palabra = palabra.toUpperCase();
        boolean flag = true;
        int intentos = 0;
        int fil = 0;
        int col = 0;
        switch (seleccion) {
            case 1:
                while (flag) {
                    fil = (int) (Math.random() * (10 - palabra.length()));
                    col = (int) (Math.random() * (10));
                    flag = false;
                    int pos = 0;
                    if (intentos > 100000) {
                        System.out.println("No se encontro un lugar para la palabra");
                    }
                    if (Math.random() < 0.3) {
                        for (int i = fil+palabra.length()-1; i >= fil ; i--) {
                            if (sopa[i][col] != '\u0000' && sopa[i][col] != palabra.charAt(pos)) {
                                flag = true;
                                break;
                            }
                        pos++;
                    }
                    intentos++;
                    }       
                    else{
                        for (int i = fil; i < fil + palabra.length(); i++) {
                            if (sopa[i][col] != '\u0000' && sopa[i][col] != palabra.charAt(pos)) {
                                flag = true;
                                break;
                            }
                            pos++;
                        }
                        intentos++;
                    }
                }
                int pos = 0;
                if (!flag) {
                    for (int i = fil; i < fil + palabra.length(); i++) {
                        sopa[i][col] = palabra.charAt(pos);
                        pos++;
                    }
                }
                break;
            case 2:
                while (flag) {
                    fil = (int) (Math.random() * (sopa.length));
                    col = (int) (Math.random() * (sopa[fil].length - palabra.length()));
                    flag = false;
                    pos = 0;
                    if (intentos > 1000) {
                        System.out.println("No se encontro un lugar para la palabra");
                    }
                    if (Math.random() < 0.3) {
                        for (int i = col+palabra.length()-1; i >= col; i--) {
                            if (sopa[fil][i] != '\u0000' && sopa[fil][i] != palabra.charAt(pos)) {
                                flag = true;
                                break;
                            }
                            pos++;
                        }
                    }
                    else{
                        for (int i = col; i < col + palabra.length(); i++ ) {
                            if (sopa[fil][i] != '\u0000' && sopa[fil][i] != palabra.charAt(pos)) {
                                flag = true;
                                break;
                            }
                        pos++;
                        }
                        intentos++; 
                    }
                }
                pos = 0;
                for (int i = col; i < col + palabra.length(); i++) {
                    sopa[fil][i] = palabra.charAt(pos);
                    pos++;
                }
                break;
            case 3:                
                while (flag) {
                    fil = (int) (Math.random() * (sopa.length - palabra.length()));
                    col = (int) (Math.random() * (sopa[fil].length - palabra.length()));
                    flag = false;
                    pos = 0;            
                    if (Math.random() < 0.5) { 
                        for (int i = fil, j = col; pos < palabra.length(); i++, j++) {
                            if (sopa[i][j] != '\u0000' && sopa[i][j] != palabra.charAt(pos)) {
                                flag = true;
                                break;
                            }
                            pos++;
                        }
                        pos = 0;
                        if (!flag) {
                            for (int i = fil, j = col; pos < palabra.length(); i++, j++) {
                                sopa[i][j] = palabra.charAt(pos);
                                pos++;
                            }
                        }
                    } else { 
                        fil += palabra.length() - 1;
                        pos = 0;
                        for (int i = fil, j = col; pos < palabra.length(); i--, j++) {
                            if (sopa[i][j] != '\u0000' && sopa[i][j] != palabra.charAt(pos)) {
                                flag = true;
                                break;
                            }
                            pos++;
                        }
                        pos = 0;
                        if (!flag) {
                            for (int i = fil, j = col; pos < palabra.length(); i--, j++) {
                                sopa[i][j] = palabra.charAt(pos);
                                pos++;
                            }
                        }
                    }
                    intentos++;
                }
            break;
        }
    }
}