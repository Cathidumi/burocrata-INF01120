package estudantes.entidades;

//extensão de documento
public class Ata extends Documento{
    private int numero;
    private String texto;
    private String presentes[]; //array

    //construtor
    public Ata(String criador, CodigoCurso codigoCurso, int paginas, int numero, String texto, String presentes[]){
        super(criador, codigoCurso, paginas);   //herança de documento
        this.numero = numero;
        this.texto = texto;
        this.presentes = presentes;
    }   
}

//getters
