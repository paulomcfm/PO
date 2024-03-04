import Arquivo.Arquivo_Java;
import Lista.Lista;

public class Main {
    public static void listas(){
        Lista lista = new Lista();
        lista.insercaoAleatoria(10);
        lista.exibir();
        //lista.insercaoDireta();
        lista.insercaoBinaria();
        lista.exibir();
    }
    public static void vetores(){
        Vetor vetor = new Vetor(10);
        vetor.preencher();
        vetor.exibir();
        //vetor.insercaoDireta();
        //vetor.insercaoBinaria();
        //vetor.selecaoDireta();
        //vetor.bubble();
        //vetor.shake();
        vetor.exibir();
    }

    public static void arquivos(){
        Arquivo_Java arq = new Arquivo_Java("./arquivo.dat");
        arq.executa();
    }
    public static void main(String[] args) {
        listas();
        //vetores();

    }
}