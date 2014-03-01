/*******************************************
 *            INTERPRETE ESCOLAR
 * -----------------------------------------
 * INSTITUTO TECNOLOGICO DE MERIDA, YUCATAN.
 * -----------------------------------------
 *          IN HOC SIGNO VINCES
 * -----------------------------------------
 *            DANIEL MORALES
 *          medennysoft@outlook.com
 * -----------------------------------------
 */

package Interprete;
import java.util.Scanner;

public class SourceCode {
    private Scanner input;
    private int iPointer;
    private String frase;
    
    public SourceCode(){
         input = new Scanner(System.in);
    }
    
    public void switchInputMode(int mode){
        switch(mode){
            case 0:
                this.setFraseDePrueba();
                break;
                
            case 1:
                this.inlineInput();
                break;
                
            case 2:
                this.fileInput();
                break;
                
            default:
                System.out.println("Modo de codigo fuente invalido.");
                break;
        }
    }
    
    public char getFrase(int consumir){
       char retorno = '\0';
       
       switch (consumir){
           case 0:
               if (iPointer != this.frase.length()){           
                   return this.frase.charAt(iPointer);
               } 
               break;
               
           case 1:
               if (iPointer != this.frase.length()){           
                   return this.frase.charAt(iPointer++);
               } 
               break;
               
           default:
               break;
       }
       
       return retorno;
    }
    
    private void setFraseDePrueba(){
        this.frase = "int x=y ,z;";
    }
    
    private void inlineInput(){
        //ILI = InLineInput
        boolean isReady = false;
        
        while(!(isReady)){
            System.out.print("Codigo> ");
            this.frase = this.input.nextLine();
            
            if (!(this.frase.equals(""))){
                isReady = true;
            }else{
                System.out.println("Introduce código valido.\n");
            }
        }
        System.out.println("**************************");
    }
    
    private void fileInput(){
       
    }
}
