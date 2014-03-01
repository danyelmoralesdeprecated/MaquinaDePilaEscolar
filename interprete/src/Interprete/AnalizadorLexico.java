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
public class AnalizadorLexico {    
    private String estadoActual;
    private String estadoSiguiente;
    private String lexema;
    private String token;
    private char simbolo;
    
    private TablasDelSistema tablasSys;
    private SourceCode frases;
    
    public AnalizadorLexico(){
       this.lexema = "";
       this.estadoActual = "q0";
       this.tablasSys = new TablasDelSistema();
    }
   
    public void setSourceCode(SourceCode frase){
        this.frases = frase;
    }
    
    public String getValorLexico()
    {
        return this.lexema;     
    }
    
    public String getToken()
    {
        if(!(this.frases instanceof SourceCode)){
            System.out.println("No existe instancia a codigo fuente.");
            return "";
        }
        
        this.generarToken();
        return this.token;
    }
   
   private void generarToken()
   {
       boolean isToken = false, isEOLF = false, isErr = false;
       String reconocedor, buffer = "";
       
       this.estadoActual = "q0";
       this.lexema = "";
       this.token = "";
       
       while(!(isToken || isEOLF || isErr))
       {
           this.simbolo = this.getNextChar(true);

           switch(this.simbolo){
               case '\0':
                   isEOLF = true;
                   break;
                   
               case ' ':
                   break;
                   
               default:
                   
                   this.estadoActual = this.obtenerEstado(this.simbolo);                  
                   this.estadoSiguiente = this.obtenerEstado(this.getNextChar(false)); 
                   
                   if(this.estadoActual.equals("ERR")){
                       //this.errores.setErrors();
                       System.out.println("Error: Token invalido para la gramatica");
                        isErr = true;
                        break;
                    }
                   
                   if(this.estadoSiguiente.equals("O") || this.getNextChar(false) == '\0'){
                            reconocedor = this.tablasSys.getSalida(estadoActual); 
                            this.token = this.tablasSys.getToken(reconocedor);
                            isToken = true;   
                   }
                   
                   buffer += this.simbolo;
                   break;
           }
       }
       
       this.lexema = buffer;
       
       //Diferenciamos entre identificadores y palabras clave.
       if(this.token.equals("unknown")){
           this.token = this.tablasSys.evaluarToken(this.lexema);
       }
   }
   
   private char getNextChar(boolean consumir)
   {
       if (consumir){
           return this.frases.getFrase(1);
       }
       
       return this.frases.getFrase(0);
   }
   
   private String clasificarSimbolo(char simboloEntrada)
   {
       String categoria;
       if (Character.isLetter(simboloEntrada)){
           categoria = "letra";
       }else if (Character.isDigit(simboloEntrada)){
           categoria = "digito";
       }else{
           categoria = "" + simboloEntrada;
       }
       return categoria;
   }

   private String obtenerEstado(char simbolo){
       String categoriaSimbolo;
       categoriaSimbolo = this.clasificarSimbolo(simbolo);
       String valor = this.tablasSys.getEstado(
                                this.estadoActual, 
                                categoriaSimbolo
                                );
       return valor;
   }

}
