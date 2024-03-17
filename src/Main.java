import Arquivo.Arquivo_Java;
import Lista.Lista;

public class Main {
    public static void vetores(){
        Vetor vetor = new Vetor(10);
        vetor.preencher();
        vetor.exibir();
        //vetor.insercaoDireta();
        //vetor.insercaoBinaria();
        //vetor.selecaoDireta();
        //vetor.bubble();
        //vetor.shake();
        //vetor.heap();
//        vetor.shell();
//        vetor.counting();
//        vetor.quickSPivo();
//        vetor.quickCPivo();
//        vetor.bucket(5);
//        vetor.radix();
//        vetor.comb();
//        vetor.gnome();
        vetor.exibir();
    }
    public static void listas(){
        Lista lista = new Lista();
        lista.preencher(10);
        lista.exibir();
        //lista.insercaoDireta();
        //lista.insercaoBinaria();
        //lista.selecaoDireta();
        //lista.bubble();
        //lista.shake();
//        lista.heap();
//        lista.shell();
//        lista.counting();
//        lista.quickSpivo();
//        lista.quickCpivo();
//        lista.bucket(5);
//        lista.radix();
//        lista.comb();
//        lista.gnome();
        lista.exibir();
    }
    public static void arquivos(){
        Arquivo_Java arq = new Arquivo_Java("./arquivo.dat");
        arq.preencher(10);
        arq.exibirArq();
        //arq.insercaoDireta();
        //arq.insercaoBinaria();
        //arq.selecaoDireta();
        //arq.bubble();
//        arq.shake();
//        arq.heap();
//        arq.shell();
//        arq.counting();
//        arq.quickSPivo();
//        arq.quickCPivo();
//        arq.bucket(5);
//        arq.radix();
//        arq.comb();
//        arq.gnome();
        arq.exibirArq();
    }
    public static void main(String[] args) {
        vetores();
//        listas();
//        arquivos();
    }
}