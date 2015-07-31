package natacion.pkg2.pkg0;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JOptionPane;


public class Natacion20 {
    
    public static void main(String[] args) {
        // TODO code application logic here
    //Declaramos nuestro obj scanner para leer
    Scanner entrada= new Scanner(System.in);
    //Variables nescesarias enviar los datos con el constructor
    long ci=0;
    String nombre="";
    String apellido="";
    double marcapersonal=0.0d;
    String prueba="";
    String categoria="";
    String estilo=" ";
    int cantidad=0;
        //Datos de los nadadores
        System.out.print("Categoria de los nadadores: ");
        categoria=entrada.nextLine();
        System.out.print("Prueba: ");
        prueba=entrada.nextLine();   
        System.out.print("Estilo: ");
        estilo=entrada.nextLine();
        
        //Arreglo de nadadores  
        System.out.print("¿Cuantos nadadores hay en el lote?: ");
        cantidad=entrada.nextInt();
        Nadador nadadores[]= new Nadador[cantidad];
        Carril carriles[] = new Carril[8];
        for (int i=0;i<nadadores.length;i++){
             System.out.print("\n \t Datos del nadador "+(i+1));
             System.out.print("\n numero de cedula: ");
             ci=entrada.nextLong();
             entrada.nextLine();//Limpiar el buffer y lea la siguiente instruccion
             System.out.print("\n nombre: ");
             nombre=entrada.nextLine();
             System.out.print("\n apellido: ");             
             apellido=entrada.nextLine();
             System.out.print("\n marca personal: ");
             marcapersonal=entrada.nextDouble();
             //validar usando isnumeric de ecl);ipse, clase Matriz_metodo y comprueba email
             nadadores[i]= new Nadador(ci,nombre,apellido,marcapersonal);             
        }
        
         //Imprime los nadadores
         for (int k=0;k<nadadores.length;k++){
             System.out.print("\n Nadador numero "+(k+1)+", "+ " cedula:"+nadadores[k].getCi()+" nombre "+ nadadores[k].getNombre()+" apellido "+nadadores[k].getApellido()+" marca personal "+nadadores[k].getMarcapersonal());
             System.out.println();
         }
         
         //Asignando valores al carril
        String posiciones[] = {"Carril 4","Carril 5", "Carril 3", "Carril 6", "Carril 2", "Carril 7", "Carril 1", "Carril 8"} ;
         //Ordenando el carril
         int h = 0;//Contador
         Carril auxc = new Carril(); 
         for( String x : posiciones){//Arreglo for each, para asignar las posiciones con setCarril
            carriles[h] = auxc;//esta linea
            h++; 
        }
        //ordenar nadadores, para asignarlos en los mejores carriles. 
        //Metodo burbuja de ordenamiento
        Nadador aux; 
        for(int p = 0; p < nadadores.length; p++){
            for(int j = p +1 ; j < nadadores.length; j++){ 
                if(nadadores[p].getMarcapersonal() > nadadores[j].getMarcapersonal()){
                    aux = nadadores[p];
                    nadadores[p] = nadadores[j];
                    nadadores[j] = aux;
                }
            }
        }
       /*TOMA DE DECISIONES, ASIGNACION DE NADADORES*/
        h = 0;
        for (Nadador swimmer: nadadores) {
            carriles[h].setNadador(swimmer);
            carriles[h].setCarril(posiciones[h]);//sobreescribiendo las posiciones 
            System.out.println(carriles[h].getNadador() + " asignado al "+ carriles[h].getCarril());
            h++;
        }
        
        /*SIMULACION Y MODELOS, SIMULACION DE COMPETENCIA*/
       
        DecimalFormat df = new DecimalFormat("0.00"); 
        double[] tiempof= new double[cantidad];
        double[] tiemponadadores= new double[cantidad];
        //El for llena el arreglo con los tiempo aleatorios para cada nadador.
        System.out.println("\n \t **** Resultados de la competencia **** ");
        for(int i=0; i<nadadores.length;i++){
            //libre
            if(prueba.equalsIgnoreCase("50 metros") && estilo.equalsIgnoreCase("Libre")&& categoria.equalsIgnoreCase("Maxima") && nadadores[i].getMarcapersonal()<=29){ 
            do{
                tiempof[i]=Math.random()*(25-29)+29;
                //El while hara la funcion de mejora del nadador..
            }while(tiempof[i]>nadadores[i].getMarcapersonal());   
            tiemponadadores[i]=tiempof[i];
            //Usar cod ordenar nadadores para simular la victoria
            
            }
           
            //espalda
            if(prueba.equalsIgnoreCase("50 metros") && estilo.equalsIgnoreCase("Espalda")&& categoria.equalsIgnoreCase("Maxima")&& nadadores[i].getMarcapersonal()<30){
            tiempof[i]=Math.random()*(26-30)+30;
            
            }
            //mariposa
            if(prueba.equalsIgnoreCase("50 metros") && estilo.equalsIgnoreCase("Mariposa")&& categoria.equalsIgnoreCase("Maxima")&& nadadores[i].getMarcapersonal()<30){
            tiempof[i]=Math.random()*(25-30)+30;
            
            }
            //pecho
            if(prueba.equalsIgnoreCase("50 metros") && estilo.equalsIgnoreCase("Pecho")&& categoria.equalsIgnoreCase("Maxima")&& nadadores[i].getMarcapersonal()<31){
            tiempof[i]=Math.random()*(27-31)+31;
            
            }
            //Resultados
            System.out.println("El tiempo del nadador "+nadadores[i].getNombre()+" "+nadadores[i].getApellido()+" es de " +df.format(tiempof[i]));
          
        }
    }   
 }

//Nadador
class Nadador {
    //atributos
    private long ci=0;
    private String nombre="";
    private String apellido="";
    private double marcapersonal=0.0;
  
    // constructor
    public Nadador(long ci, String nombre, String apellido, double marcapersonal){
     this.ci=ci;
     this.nombre=nombre;
     this.apellido=apellido;
     this.marcapersonal=marcapersonal;
    }    
    
    public long getCi() {
        return ci;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public double getMarcapersonal() {
        return marcapersonal;
    }   

    @Override
    public String toString() {
        return "Nadador{" + "ci=" + ci + ", nombre=" + nombre + ", apellido=" + apellido + ", marcapersonal=" + marcapersonal + '}';
    }
        
}

//Carril
class Carril{
    //atributos con un solo carril bastaba, y se hacia un array de carriles. 
    private String carril;
    private Nadador x;

    public String getCarril() {
        return carril;
    }

    public void setCarril(String nombre){ // u.u idiota
        carril = nombre;
    }
    
    public Nadador getNadador(){
        return this.x;
    }
    
    public void setNadador(Nadador nadador){
        this.x = nadador;
    }
}

   


