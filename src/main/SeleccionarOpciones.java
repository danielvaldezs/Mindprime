package main;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SeleccionarOpciones {
	// Attr main
	public String mainWord;
	public int numSilabasMain;
	public int initialLetterMain;
	public String categoryMain;

	// attr opciones
	public String catOpcion1;
	public String catOpcion2;

//    public ArrayList<Palabra> listaPalabras;

	public String opciones[] = new String[2];

//    public String[] seleccionarOpciones(ArrayList<Palabra> lista,String categoryMain, String mainWord, int initialLetterMain, int numSilabasMain){
//        this.listaPalabras = lista;
//        this.initialLetterMain= initialLetterMain;
//        this.numSilabasMain=numSilabasMain;
//        this.categoryMain=categoryMain;
//
//        //Obtener categoria opciones
//        this.mainWord=mainWord;
//        while(opciones[0]==null || opciones[1]==null){
//            if(categoryMain=="Positiva"){
//                catOpcion1="Negativa";
//                catOpcion2="Neutra";
//            }else if(categoryMain=="Negativa"){
//                catOpcion1="Positiva";
//                catOpcion2="Neutra";
//            }else if(categoryMain=="Neutra"){
//                catOpcion1="Positiva";
//                catOpcion2="Negativa";
//            }
//
//            for (int i = 0; i < listaPalabras.size() ; i++) {
//                Palabra palabra = listaPalabras.get(i);
//
//                //Obtener Opcion 1
//                if(palabra.getCategoria()==catOpcion1){
//                    if(palabra.getLetraInicial()==initialLetterMain){
//                        if(palabra.getNumSilabas()==numSilabasMain){
//                            opciones[0]= palabra.getPalabra();
//                        }
//                    }
//                }//fin if anidado
//
//                //Obtener Opcion 2
//                if(palabra.getCategoria()==catOpcion2){
//                    if(palabra.getLetraInicial()==initialLetterMain){
//                        if(palabra.getNumSilabas()==numSilabasMain){
//                            opciones[1]= palabra.getPalabra();
//                        }
//                    }
//                }//FIn if anidado
//
//            }//Fin for
//
//        }//fin while
//        return opciones;
//
//    }
}
