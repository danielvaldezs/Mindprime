import java.lang.reflect.Array;

public class SeleccionarOpciones {
    public String mainWord;
    public int numSilabasMain;
    public int initialLetterMain;
    public String categoryMain;

    public Array listaPalabras[];

    public String opciones[]= new String[2];

    public String[] seleccionarOpciones(String mainWord){
        this.mainWord=mainWord;
        while(opciones[1]==null || opciones[2]==null){
            if(categoryMain=="Positiva"){

            }else if(categoryMain=="Negativa"){

            }else if(categoryMain=="Neutra"){
                
            }
        }
        return opciones;
    }
}
