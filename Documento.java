package estudantes.entidades;

/**
 * Classe que representa um documento genérico.
 * <br><br>
 * <strong>Seu trabalho começa aqui...</strong>
 * 
 * @author coloque os nomes dos autores aqui
 */
public abstract class Documento {
    private String criador;
    private CodigoCurso codigoCurso;
    private int paginas;

    //construtor
    public Documento(String criador, CodigoCurso codigoCurso, int paginas){
        this.criador = criador;
        this.codigoCurso = codigoCurso;
        this.paginas = paginas;
    }

    //getters
    public int getPaginas(int paginas){
        this.paginas = paginas;

    }

    public CodigoCurso getCodigoCurso(CodigoCurso codigoCurso){
        this.codigoCurso = codigoCurso;


    } 

    public String getCriador(){
        this.criador = criador;
    }
    //hascode
}
