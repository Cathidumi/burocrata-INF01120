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
    public int getPaginas(){
        return this.paginas;
    }

    public CodigoCurso getCodigoCurso(){
        return this.codigoCurso;
    }

    public String getCriador(){
        return this.criador;
    }

    //equals
    @Override
    public boolean equals(Object o){
        //se o documento eh valido retorna true
        if(this == o){
            return true;
        }

        //se o documento eh null, ou nao eh o mesmo tipo, retorna false
        if(o == null || this.getClass() != o.getClass()){
            return false;
        }

        //verifica se eh um documento valido
        Documento outro = (Documento) o;

        //verifica se os 3 dados do documento funcionam
        if(this.paginas == outro.paginas && this.codigoCurso == outro.codigoCurso && this.criador.equals(outro.criador)){
            return true;
        }else {
            return false;
            }
    }

    //hashcode
    @Override
    public int hashCode(){
        return Objects.hash(criador, codigoCurso, paginas);
    }
    
}
