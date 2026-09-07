package estudantes.entidades;

import professor.entidades.CodigoCurso;

//extensão de documento
public abstract class DocumentoAdministrativo extends Documento{
    public DocumentoAdministrativo(String criador, CodigoCurso codigoCurso, int paginas){ // construtor
        super(criador, codigoCurso, paginas);
    }

    //equals
    @Override
    public boolean equals(Object o){
        //mesmo objeto?
        if(this == o){
            return true;
        }
        
        //eh nulo ou classe diferente?
        if(o == null || this.getClass() != o.getClass()){
            return false;
        }
   
        //precisa retornar para a classe mãe
        return super.equals(o);
    }

    //hashcode
    @Override
    public int hashCode(){
        return super.hashCode();
    }
}

