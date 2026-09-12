package estudantes.entidades;

import professor.entidades.*;
import java.util.*;
/**
 * Classe que traz a lógica do algoritmo de organização e despacho de processos.
 * <br><br>
 * Você pode incluir novos atributos e métodos nessa classe para criar
 * lógicas mais complexas para o gerenciamento da organização e despacho de
 * processos, mas eles não serão invocados diretamente pelo simulador e devem
 * respeitar propriedades de encapsulamento e coesão.
 *
 * @author coloque os nomes dos autores aqui
 */
public class Burocrata {
    private int estresse = 0;
    private Mesa mesa;
    private Universidade universidade;

    //limite de páginas por processo (a secretaria destrói o processo acima disso)
    private static final int LIMITE_PAGINAS = 250;
    //margem de segurança: com essa quantidade de páginas ainda livres, já despacha
    private static final int MARGEM_DESPACHO = 20;
<<<<<<< HEAD

    //faixa de valores possíveis para a exigência de "bom lote" (ver calcularMinimoParaDespacho)
    private static final int MIN_DOCUMENTOS_PARA_DESPACHO_MINIMO = 3;  //pouca oferta de documentos: não vale esperar por um lote que pode nunca se formar
    private static final int MIN_DOCUMENTOS_PARA_DESPACHO_MAXIMO = 20; //oferta alta: aproveita para formar lotes maiores e mais eficientes por despacho
    //faixa de backlog (documentos pendentes nos montes) usada para interpolar entre os dois valores acima
    private static final int BACKLOG_LIMIAR_BAIXO = 300;
    private static final int BACKLOG_LIMIAR_ALTO = 5000;

    /**
     * Construtor de Burocrata.
     *
     * @param m mesa com os processos
     * @param u universidade com os montes dos cursos e a secretaria
     */
    public Burocrata(Mesa m, Universidade u){
        this.mesa = m;
        this.universidade = u;
    }

    /**
     * Executa a lógica de criação e despacho dos processos.
     * <br><br>
     * Esse método é o único método de controle invocado durante a simulação
     * da universidade.
     * <br><br>
     * Aqui podem ser feitas todas as verificações sobre os documentos nos
     * montes dos cursos e dos processos abertos na mesa do Burocrata. A partir
     * dessas informações, você pode colocar documentos nos processos abertos
     * e despachar os processos para a secretaria acadêmica.
     * <br><br>
     * Cuidado com a complexidade do seu algoritmo, porque se ele demorar muito
     * serão criados menos documentos na sua execução e sua produtividade geral
     * vai cair.
     * <br><br>
     * Esse método será chamado a cada 50 milissegundos pelo simulador da
     * universidade.
     * <br><br>
     * <strong>O burocrata não pode manter documentos com ele</strong> depois
     * que o método trabalhar terminar de executar, ou seja, você deve devolver
     * para os montes dos cursos todos os documentos que você removeu dos montes
     * dos cursos.
     *
     * @see professor.entidades.Universidade#despachar(Processo)
     * @see professor.entidades.Universidade#removerDocumentoDoMonteDoCurso(estudantes.entidades.Documento, professor.entidades.CodigoCurso)
     * @see professor.entidades.Universidade#devolverDocumentoParaMonteDoCurso(estudantes.entidades.Documento, professor.entidades.CodigoCurso)
     */
    public void trabalhar(){
        //1) tenta encaixar documentos dos montes nos processos abertos
        encaixarDocumentosDosMontes();

        //2) despacha os processos que já estão prontos (cheios, com bom lote ou travados)
        despacharProcessosProntos();
    }

    /**
     * Percorre o monte de cada curso e tenta encaixar cada documento em algum
     * processo compatível da mesa. Se não houver processo compatível, o
     * documento simplesmente permanece no monte para ser tentado no próximo
     * ciclo.
     * <br><br>
     * Os cursos são visitados em ordem decrescente de tamanho do monte, e não
     * na ordem fixa da enumeração. Isso evita que os cursos de graduação
     * (maioria entre os 10 cursos) sempre tomem a frente e ocupem todos os
     * processos vazios da mesa antes que os cursos de pós-graduação sejam
     * avaliados, o que causaria acúmulo permanente de documentos no monte
     * desses cursos.
     */
    private void encaixarDocumentosDosMontes(){
        CodigoCurso[] cursosPorPrioridade = CodigoCurso.values();
        Arrays.sort(cursosPorPrioridade, (a, b) ->
            universidade.contarDocumentosNoMonteDoCurso(b) - universidade.contarDocumentosNoMonteDoCurso(a));

        for(CodigoCurso codigo : cursosPorPrioridade){    //percorre os cursos, maior monte primeiro
            Documento[] documentos = universidade.pegarCopiaDoMonteDoCurso(codigo);

            //tenta encaixar cada documento do monte em algum processo
            for(Documento doc : documentos){
                int indiceProcesso = encontrarProcessoCompativel(doc);

                //remove do monte e adiciona ao processo escolhido
                if(indiceProcesso >= 0){
                    Processo processo = mesa.getProcesso(indiceProcesso);
                    if(processo != null && universidade.removerDocumentoDoMonteDoCurso(doc, codigo)){
                        processo.adicionarDocumento(doc);
                    }
                }
                //se não tiver processo compatível, o documento fica no monte
            }
        }
    }

    /**
     * Escolhe, entre os processos abertos na mesa, o melhor processo para
     * receber o documento candidato.
     * <br><br>
     * Um processo só é candidato se o documento couber no limite de páginas
     * e se a combinação não violar nenhuma das regras administrativas
     * verificadas pela secretaria. Entre os processos compatíveis, prefere-se
     * sempre o mais cheio, para maximizar a quantidade de documentos por
     * processo despachado (o que aumenta a eficiência do burocrata).
     *
     * @param candidato documento que se quer encaixar
     * @return índice do processo escolhido, ou -1 se nenhum processo servir
     */
    private int encontrarProcessoCompativel(Documento candidato){
        int melhorIndice = -1;
        int maiorOcupacao = -1;

        for(int i = 0; i < 5; i++){
            Processo processo = mesa.getProcesso(i);
            if(processo == null){
                continue;
            }

            int paginasAtuais = somarPaginas(processo);
            if(paginasAtuais + candidato.getPaginas() > LIMITE_PAGINAS){
                continue;
            }

            if(!compativel(processo, candidato)){
                continue;
            }

            //entre os compatíveis, prefere o processo mais ocupado
            if(paginasAtuais > maiorOcupacao){
                maiorOcupacao = paginasAtuais;
                melhorIndice = i;
            }
        }

        return melhorIndice;
    }

    /**
     * Verifica se acrescentar o documento candidato ao processo continuaria
     * respeitando todas as regras administrativas (as mesmas verificadas pela
     * Secretaria no despacho).
     *
     * @param processo processo já aberto na mesa
     * @param candidato documento que se quer acrescentar
     * @return true se a combinação resultante não estressa o burocrata
     */
    private boolean compativel(Processo processo, Documento candidato){
        Documento[] atuais = processo.pegarCopiaDoProcesso();
        Documento[] simulado = Arrays.copyOf(atuais, atuais.length + 1);
        simulado[atuais.length] = candidato;
        return !teriaProblema(simulado);
    }

    /**
     * Reproduz as regras administrativas verificadas pela Secretaria no
     * despacho, para saber de antemão se um conjunto de documentos causaria
     * estresse se fosse despachado junto.
     *
     * @param documentos conjunto de documentos que estaria dentro do processo
     * @return true se alguma regra administrativa seria descumprida
     */
    private boolean teriaProblema(Documento[] documentos){
        //graduação x pós-graduação
        boolean graduacao = false, posgraduacao = false;
        for(Documento doc : documentos){
            if(doc.getCodigoCurso().equals(CodigoCurso.POS_GRADUACAO_COMPUTACAO) || doc.getCodigoCurso().equals(CodigoCurso.POS_GRADUACAO_ENGENHARIA_ELETRICA) || doc.getCodigoCurso().equals(CodigoCurso.POS_GRADUACAO_MICROELETRONICA)){
                posgraduacao = true;
            }else{
                graduacao = true;
            }
        }
        if(graduacao && posgraduacao){
            return true;
        }

        //administrativos x acadêmicos
        boolean administrativos = false, academicos = false;
        for(Documento doc : documentos){
            if(doc instanceof DocumentoAdministrativo){
                administrativos = true;
            }
            if(doc instanceof DocumentoAcademico){
                academicos = true;
            }
        }
        if(administrativos && academicos){
            return true;
        }

        //processo só com atas
        boolean apenasAtas = true;
        for(Documento doc : documentos){
            if(doc instanceof DocumentoAdministrativo || doc instanceof DocumentoAcademico){
                apenasAtas = false;
            }
        }
        if(apenasAtas){
            return true;
        }

        //portarias e editais substanciais precisam estar sozinhos no processo
        boolean documentoSubstancialValido = false;
        for(Documento doc : documentos){
            if(doc instanceof Edital || doc instanceof Portaria){
                Norma norma = (Norma) doc;
                if(norma.getPaginas() >= 100 && norma.isValido()){
                    documentoSubstancialValido = true;
                }
            }
        }
        if(documentoSubstancialValido && documentos.length > 1){
            return true;
        }

        //circulares e ofícios precisam ter destinatário em comum
        HashMap<String, Integer> destinatarios = new HashMap<>();
        int contagemDeOficiosECirculares = 0;
        for(Documento doc : documentos){
            if(doc instanceof Oficio){
                contagemDeOficiosECirculares++;
                Oficio oficio = (Oficio) doc;
                destinatarios.merge(oficio.getDestinatario(), 1, Integer::sum);
            }
            if(doc instanceof Circular){
                contagemDeOficiosECirculares++;
                Circular circular = (Circular) doc;
                for(String destinatario : circular.getDestinatarios()){
                    destinatarios.merge(destinatario, 1, Integer::sum);
                }
            }
        }
        if(contagemDeOficiosECirculares > 0){
            boolean semDestinatarioComum = true;
            for(int ocorrencias : destinatarios.values()){
                if(ocorrencias >= contagemDeOficiosECirculares){
                    semDestinatarioComum = false;
                }
            }
            if(semDestinatarioComum){
                return true;
            }
        }

        //diplomas só podem conviver com diplomas, certificados e atas
        boolean diplomas = false, documentosNaoDiplomasCertificadosAtas = false;
        for(Documento doc : documentos){
            if(doc instanceof Diploma){
                diplomas = true;
            }else if(!(doc instanceof Certificado) && !(doc instanceof Ata)){
                documentosNaoDiplomasCertificadosAtas = true;
            }
        }
        if(diplomas && documentosNaoDiplomasCertificadosAtas){
            return true;
        }

        //atestados de categorias diferentes não podem se misturar
        String categoriaDoPrimeiroAtestadoEncontrado = null;
        for(Documento doc : documentos){
            if(doc instanceof Atestado){
                Atestado atestado = (Atestado) doc;
                if(categoriaDoPrimeiroAtestadoEncontrado == null){
                    categoriaDoPrimeiroAtestadoEncontrado = atestado.getCategoria();
                }else if(!categoriaDoPrimeiroAtestadoEncontrado.equals(atestado.getCategoria())){
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Percorre os processos abertos na mesa e despacha aqueles que já valem a
     * pena mandar para a secretaria: processos quase cheios, processos com um
     * bom lote de documentos, ou processos travados (que já não conseguem
     * aceitar mais nenhum documento).
     * <br><br>
     * A exigência de "bom lote" é recalculada uma vez por ciclo com base no
     * volume de documentos pendentes nos montes (ver
     * {@link #calcularMinimoParaDespacho()}), então quanto mais documentos
     * estiverem esperando, maior o lote exigido para aproveitar melhor cada
     * processo despachado.
     */
    private void despacharProcessosProntos(){
        int minimoParaDespacho = calcularMinimoParaDespacho();

        for(int i = 0; i < 5; i++){
            Processo processo = mesa.getProcesso(i);
            if(processo == null || processo.contarDocumentos() == 0){
                continue;
            }

            int paginas = somarPaginas(processo);
            boolean quaseCheio = paginas >= LIMITE_PAGINAS - MARGEM_DESPACHO;
            boolean loteBom = processo.contarDocumentos() >= minimoParaDespacho;
            boolean travado = estaTravado(processo);

            if(quaseCheio || loteBom || travado){
                universidade.despachar(processo);
            }
        }
    }

    /**
     * Calcula, com base no total de documentos pendentes nos montes de todos
     * os cursos, o tamanho de lote que já vale a pena despachar.
     * <br><br>
     * Quanto maior o backlog, maior a exigência: com bastante documento à
     * disposição, vale a pena esperar um pouco mais para formar lotes maiores
     * por processo, o que pesa mais na fórmula de eficiência do que o pequeno
     * atraso causado. Quando o backlog está baixo (pouca oferta de
     * documentos), a exigência cai para não deixar um processo esperando
     * indefinidamente por um lote que talvez nunca se forme.
     * <br><br>
     * O resultado varia entre {@link #MIN_DOCUMENTOS_PARA_DESPACHO_MINIMO} e
     * {@link #MIN_DOCUMENTOS_PARA_DESPACHO_MAXIMO}, interpolando linearmente
     * entre {@link #BACKLOG_LIMIAR_BAIXO} e {@link #BACKLOG_LIMIAR_ALTO}.
     *
     * @return quantidade mínima de documentos para considerar um processo
     * como um bom lote para despacho
     */
    private int calcularMinimoParaDespacho(){
        int backlog = contarDocumentosPendentes();

        if(backlog <= BACKLOG_LIMIAR_BAIXO){
            return MIN_DOCUMENTOS_PARA_DESPACHO_MINIMO;
        }
        if(backlog >= BACKLOG_LIMIAR_ALTO){
            return MIN_DOCUMENTOS_PARA_DESPACHO_MAXIMO;
        }

        double proporcao = (double)(backlog - BACKLOG_LIMIAR_BAIXO) / (BACKLOG_LIMIAR_ALTO - BACKLOG_LIMIAR_BAIXO);
        int intervalo = MIN_DOCUMENTOS_PARA_DESPACHO_MAXIMO - MIN_DOCUMENTOS_PARA_DESPACHO_MINIMO;
        return MIN_DOCUMENTOS_PARA_DESPACHO_MINIMO + (int) Math.round(proporcao * intervalo);
    }

    /**
     * Soma a quantidade de documentos pendentes nos montes de todos os
     * cursos, ou seja, ainda não colocados em nenhum processo da mesa.
     *
     * @return total de documentos pendentes em todos os montes
     */
    private int contarDocumentosPendentes(){
        int total = 0;
        for(CodigoCurso codigo : CodigoCurso.values()){
            total += universidade.contarDocumentosNoMonteDoCurso(codigo);
        }
        return total;
    }

    /**
     * Um processo está travado quando já não é possível encaixar mais nenhum
     * documento nele, mesmo estando longe do limite de páginas (por exemplo,
     * uma portaria ou edital substancial sozinha no processo). Nesse caso não
     * adianta esperar mais ciclos: o processo deve ser despachado logo para
     * liberar espaço na mesa.
     *
     * @param processo processo a ser avaliado
     * @return true se o processo não aceita mais nenhum documento adicional
     */
    private boolean estaTravado(Processo processo){
        Documento[] atuais = processo.pegarCopiaDoProcesso();
        if(atuais.length != 1){
            return false;
        }

        Documento unico = atuais[0];
        if(unico instanceof Edital || unico instanceof Portaria){
            Norma norma = (Norma) unico;
            return norma.getPaginas() >= 100 && norma.isValido();
        }

        return false;
    }

    /**
     * Soma as páginas de todos os documentos atualmente dentro de um
     * processo.
     *
     * @param processo processo a ser somado
     * @return total de páginas do processo
     */
    private int somarPaginas(Processo processo){
        int total = 0;
        for(Documento doc : processo.pegarCopiaDoProcesso()){
            total += doc.getPaginas();
        }
        return total;
    }

    /**
     * Retorna o valor atual de estresse do burocrata.
     * @return estresse atual
     */
    public int getEstresse(){
        return this.estresse;
    }

    /**
     * Aumenta o estresse do burocrata em uma unidade.
     *
     * <strong>VOCÊ NÃO DEVERIA INVOCAR ESSE MÉTODO!!!</strong>
     */
    public void estressar(){
        this.estresse++;
    }

    /**
     * Aumenta o estresse do burocrata em 10 unidades.
     *
     * <strong>VOCÊ NÃO DEVERIA INVOCAR ESSE MÉTODO!!!</strong>
     */
    public void estressarMuito(){
        this.estresse += 10;
    }
}
