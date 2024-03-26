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
//        vetor.merge();
        vetor.mergeSort();
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
//        lista.merge();
        lista.mergeSort();
        lista.exibir();
    }
    public static void arquivos(){
        Arquivo_Java arqOrd = new Arquivo_Java("./arquivoOrdenado.dat");
        Arquivo_Java arqRev = new Arquivo_Java("./arquivoReverso.dat");
        Arquivo_Java arqRand = new Arquivo_Java("./arquivoAleatorio.dat");
        Arquivo_Java auxRev= new Arquivo_Java(""), auxRand = new Arquivo_Java("");
        arqRand.geraArquivoRandomico(1024);
        arqOrd.geraArquivoReverso(1024);
        arqRev.geraArquivoOrdenado(1024);
        long ttotalOrd, ttotalRev, ttotalRand, tini, tfim;
        int compOrd, compRev, compRand, movOrd, movRev, movRand;
//        arqRand.exibirArq();
                                                //Insercao Direta
        //Arquivo Aleatorio
        auxRand.copiaArquivo(arqRand.getArquivo());
        auxRand.initMov();
        auxRand.initComp();
        tini=System.currentTimeMillis();
        auxRand.insercaoDireta();
        tfim=System.currentTimeMillis();
        ttotalRand=(tfim-tini)/1000;
        compRand=auxRand.getComp();
        movRand=auxRand.getMov();
        System.out.println("Segs = "+ttotalRand);
        System.out.println("Comps = "+compRand);
        System.out.println("Movs = "+movRand);

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
//        arq.merge();
        arqRand.exibirArq();
        auxRand.exibirArq();
    }
    public static void main(String[] args) {
//        vetores();
        listas();
//        arquivos();
    }
}