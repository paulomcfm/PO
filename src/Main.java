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
//        vetor.mergeSort();
//        vetor.tim(4);
        vetor.exibir();
    }
    public static void listas(){
        Lista lista = new Lista();
        lista.inicializa();
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
//        lista.mergeSort();
//        lista.tim(4);
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

                                                //Insercao Direta
        //Arquivo Ordenado
        arqOrd.initMov();
        arqOrd.initComp();
        tini=System.currentTimeMillis();
        arqOrd.insercaoDireta();
        tfim=System.currentTimeMillis();
        ttotalOrd=(tfim-tini)/1000;
        compOrd=arqOrd.getComp();
        movOrd=arqOrd.getMov();
        arqOrd.IDcompMin();
        arqOrd.IDmovMin();

        //Arquivo Reverso
        auxRev.copiaArquivo(arqRev.getArquivo());
        auxRev.initMov();
        auxRev.initComp();
        tini=System.currentTimeMillis();
        auxRev.insercaoDireta();
        tfim=System.currentTimeMillis();
        ttotalRev=(tfim-tini)/1000;
        compRev=auxRev.getComp();
        movRev=auxRev.getMov();
        auxRev.IDcompMax();
        auxRev.IDmovMax();

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
        auxRand.IDcompMed();
        auxRand.IDmovMed();

        //GRAVA NO ARQ TXT

                                            //Insercao Binaria
        //Arquivo Ordenado
        arqOrd.initMov();
        arqOrd.initComp();
        tini=System.currentTimeMillis();
        arqOrd.insercaoBinaria();
        tfim=System.currentTimeMillis();
        ttotalOrd=(tfim-tini)/1000;
        compOrd=arqOrd.getComp();
        movOrd=arqOrd.getMov();
        arqOrd.IDcompMin();
        arqOrd.IDmovMin();

        //Arquivo Reverso
        auxRev.copiaArquivo(arqRev.getArquivo());
        auxRev.initMov();
        auxRev.initComp();
        tini=System.currentTimeMillis();
        auxRev.insercaoBinaria();
        tfim=System.currentTimeMillis();
        ttotalRev=(tfim-tini)/1000;
        compRev=auxRev.getComp();
        movRev=auxRev.getMov();
        auxRev.IDcompMax();
        auxRev.IDmovMax();

        //Arquivo Aleatorio
        auxRand.copiaArquivo(arqRand.getArquivo());
        auxRand.initMov();
        auxRand.initComp();
        tini=System.currentTimeMillis();
        auxRand.insercaoBinaria();
        tfim=System.currentTimeMillis();
        ttotalRand=(tfim-tini)/1000;
        compRand=auxRand.getComp();
        movRand=auxRand.getMov();
        auxRand.IDcompMed();
        auxRand.IDmovMed();

        //GRAVA NO ARQ TXT

                                                //Seleção Direta
        //Arquivo Ordenado
        arqOrd.initMov();
        arqOrd.initComp();
        tini=System.currentTimeMillis();
        arqOrd.selecaoDireta();
        tfim=System.currentTimeMillis();
        ttotalOrd=(tfim-tini)/1000;
        compOrd=arqOrd.getComp();
        movOrd=arqOrd.getMov();
        arqOrd.IDcompMin();
        arqOrd.IDmovMin();

        //Arquivo Reverso
        auxRev.copiaArquivo(arqRev.getArquivo());
        auxRev.initMov();
        auxRev.initComp();
        tini=System.currentTimeMillis();
        auxRev.selecaoDireta();
        tfim=System.currentTimeMillis();
        ttotalRev=(tfim-tini)/1000;
        compRev=auxRev.getComp();
        movRev=auxRev.getMov();
        auxRev.IDcompMax();
        auxRev.IDmovMax();

        //Arquivo Aleatorio
        auxRand.copiaArquivo(arqRand.getArquivo());
        auxRand.initMov();
        auxRand.initComp();
        tini=System.currentTimeMillis();
        auxRand.selecaoDireta();
        tfim=System.currentTimeMillis();
        ttotalRand=(tfim-tini)/1000;
        compRand=auxRand.getComp();
        movRand=auxRand.getMov();
        auxRand.IDcompMed();
        auxRand.IDmovMed();

        //GRAVA NO ARQ TXT


                                            //Bubble Sort
        //Arquivo Ordenado
        arqOrd.initMov();
        arqOrd.initComp();
        tini=System.currentTimeMillis();
        arqOrd.bubble();
        tfim=System.currentTimeMillis();
        ttotalOrd=(tfim-tini)/1000;
        compOrd=arqOrd.getComp();
        movOrd=arqOrd.getMov();

        //Arquivo Reverso
        auxRev.copiaArquivo(arqRev.getArquivo());
        auxRev.initMov();
        auxRev.initComp();
        tini=System.currentTimeMillis();
        auxRev.bubble();
        tfim=System.currentTimeMillis();
        ttotalRev=(tfim-tini)/1000;
        compRev=auxRev.getComp();
        movRev=auxRev.getMov();

        //Arquivo Aleatorio
        auxRand.copiaArquivo(arqRand.getArquivo());
        auxRand.initMov();
        auxRand.initComp();
        tini=System.currentTimeMillis();
        auxRand.bubble();
        tfim=System.currentTimeMillis();
        ttotalRand=(tfim-tini)/1000;
        compRand=auxRand.getComp();
        movRand=auxRand.getMov();

        //GRAVA NO ARQ TXT

                                                            //Shake Sort
        //Arquivo Ordenado
        arqOrd.initMov();
        arqOrd.initComp();
        tini=System.currentTimeMillis();
        arqOrd.shake();
        tfim=System.currentTimeMillis();
        ttotalOrd=(tfim-tini)/1000;
        compOrd=arqOrd.getComp();
        movOrd=arqOrd.getMov();

        //Arquivo Reverso
        auxRev.copiaArquivo(arqRev.getArquivo());
        auxRev.initMov();
        auxRev.initComp();
        tini=System.currentTimeMillis();
        auxRev.shake();
        tfim=System.currentTimeMillis();
        ttotalRev=(tfim-tini)/1000;
        compRev=auxRev.getComp();
        movRev=auxRev.getMov();

        //Arquivo Aleatorio
        auxRand.copiaArquivo(arqRand.getArquivo());
        auxRand.initMov();
        auxRand.initComp();
        tini=System.currentTimeMillis();
        auxRand.shake();
        tfim=System.currentTimeMillis();
        ttotalRand=(tfim-tini)/1000;
        compRand=auxRand.getComp();
        movRand=auxRand.getMov();

        //GRAVA NO ARQ TXT

                                                        //Shell Sort
        //Arquivo Ordenado
        arqOrd.initMov();
        arqOrd.initComp();
        tini=System.currentTimeMillis();
        arqOrd.shell();
        tfim=System.currentTimeMillis();
        ttotalOrd=(tfim-tini)/1000;
        compOrd=arqOrd.getComp();
        movOrd=arqOrd.getMov();

        //Arquivo Reverso
        auxRev.copiaArquivo(arqRev.getArquivo());
        auxRev.initMov();
        auxRev.initComp();
        tini=System.currentTimeMillis();
        auxRev.shell();
        tfim=System.currentTimeMillis();
        ttotalRev=(tfim-tini)/1000;
        compRev=auxRev.getComp();
        movRev=auxRev.getMov();

        //Arquivo Aleatorio
        auxRand.copiaArquivo(arqRand.getArquivo());
        auxRand.initMov();
        auxRand.initComp();
        tini=System.currentTimeMillis();
        auxRand.shell();
        tfim=System.currentTimeMillis();
        ttotalRand=(tfim-tini)/1000;
        compRand=auxRand.getComp();
        movRand=auxRand.getMov();

        //GRAVA NO ARQ TXT

                                                    //Heap Sort
        //Arquivo Ordenado
        arqOrd.initMov();
        arqOrd.initComp();
        tini=System.currentTimeMillis();
        arqOrd.heap();
        tfim=System.currentTimeMillis();
        ttotalOrd=(tfim-tini)/1000;
        compOrd=arqOrd.getComp();
        movOrd=arqOrd.getMov();

        //Arquivo Reverso
        auxRev.copiaArquivo(arqRev.getArquivo());
        auxRev.initMov();
        auxRev.initComp();
        tini=System.currentTimeMillis();
        auxRev.heap();
        tfim=System.currentTimeMillis();
        ttotalRev=(tfim-tini)/1000;
        compRev=auxRev.getComp();
        movRev=auxRev.getMov();

        //Arquivo Aleatorio
        auxRand.copiaArquivo(arqRand.getArquivo());
        auxRand.initMov();
        auxRand.initComp();
        tini=System.currentTimeMillis();
        auxRand.heap();
        tfim=System.currentTimeMillis();
        ttotalRand=(tfim-tini)/1000;
        compRand=auxRand.getComp();
        movRand=auxRand.getMov();

        //GRAVA NO ARQ TXT

                                                //Quick Sem Pivo Sort
        //Arquivo Ordenado
        arqOrd.initMov();
        arqOrd.initComp();
        tini=System.currentTimeMillis();
        arqOrd.quickSPivo();
        tfim=System.currentTimeMillis();
        ttotalOrd=(tfim-tini)/1000;
        compOrd=arqOrd.getComp();
        movOrd=arqOrd.getMov();

        //Arquivo Reverso
        auxRev.copiaArquivo(arqRev.getArquivo());
        auxRev.initMov();
        auxRev.initComp();
        tini=System.currentTimeMillis();
        auxRev.quickSPivo();
        tfim=System.currentTimeMillis();
        ttotalRev=(tfim-tini)/1000;
        compRev=auxRev.getComp();
        movRev=auxRev.getMov();

        //Arquivo Aleatorio
        auxRand.copiaArquivo(arqRand.getArquivo());
        auxRand.initMov();
        auxRand.initComp();
        tini=System.currentTimeMillis();
        auxRand.quickSPivo();
        tfim=System.currentTimeMillis();
        ttotalRand=(tfim-tini)/1000;
        compRand=auxRand.getComp();
        movRand=auxRand.getMov();

        //GRAVA NO ARQ TXT

                                                    //Quick com Pivo Sort
        //Arquivo Ordenado
        arqOrd.initMov();
        arqOrd.initComp();
        tini=System.currentTimeMillis();
        arqOrd.quickCPivo();
        tfim=System.currentTimeMillis();
        ttotalOrd=(tfim-tini)/1000;
        compOrd=arqOrd.getComp();
        movOrd=arqOrd.getMov();

        //Arquivo Reverso
        auxRev.copiaArquivo(arqRev.getArquivo());
        auxRev.initMov();
        auxRev.initComp();
        tini=System.currentTimeMillis();
        auxRev.quickCPivo();
        tfim=System.currentTimeMillis();
        ttotalRev=(tfim-tini)/1000;
        compRev=auxRev.getComp();
        movRev=auxRev.getMov();

        //Arquivo Aleatorio
        auxRand.copiaArquivo(arqRand.getArquivo());
        auxRand.initMov();
        auxRand.initComp();
        tini=System.currentTimeMillis();
        auxRand.quickCPivo();
        tfim=System.currentTimeMillis();
        ttotalRand=(tfim-tini)/1000;
        compRand=auxRand.getComp();
        movRand=auxRand.getMov();

        //GRAVA NO ARQ TXT

                                                    //Merge Sort
        //Arquivo Ordenado
        arqOrd.initMov();
        arqOrd.initComp();
        tini=System.currentTimeMillis();
        arqOrd.merge();
        tfim=System.currentTimeMillis();
        ttotalOrd=(tfim-tini)/1000;
        compOrd=arqOrd.getComp();
        movOrd=arqOrd.getMov();

        //Arquivo Reverso
        auxRev.copiaArquivo(arqRev.getArquivo());
        auxRev.initMov();
        auxRev.initComp();
        tini=System.currentTimeMillis();
        auxRev.merge();
        tfim=System.currentTimeMillis();
        ttotalRev=(tfim-tini)/1000;
        compRev=auxRev.getComp();
        movRev=auxRev.getMov();

        //Arquivo Aleatorio
        auxRand.copiaArquivo(arqRand.getArquivo());
        auxRand.initMov();
        auxRand.initComp();
        tini=System.currentTimeMillis();
        auxRand.merge();
        tfim=System.currentTimeMillis();
        ttotalRand=(tfim-tini)/1000;
        compRand=auxRand.getComp();
        movRand=auxRand.getMov();

        //GRAVA NO ARQ TXT

                                                                //Merge2 Sort
        //Arquivo Ordenado
        arqOrd.initMov();
        arqOrd.initComp();
        tini=System.currentTimeMillis();
        arqOrd.mergeSort();
        tfim=System.currentTimeMillis();
        ttotalOrd=(tfim-tini)/1000;
        compOrd=arqOrd.getComp();
        movOrd=arqOrd.getMov();

        //Arquivo Reverso
        auxRev.copiaArquivo(arqRev.getArquivo());
        auxRev.initMov();
        auxRev.initComp();
        tini=System.currentTimeMillis();
        auxRev.mergeSort();
        tfim=System.currentTimeMillis();
        ttotalRev=(tfim-tini)/1000;
        compRev=auxRev.getComp();
        movRev=auxRev.getMov();

        //Arquivo Aleatorio
        auxRand.copiaArquivo(arqRand.getArquivo());
        auxRand.initMov();
        auxRand.initComp();
        tini=System.currentTimeMillis();
        auxRand.mergeSort();
        tfim=System.currentTimeMillis();
        ttotalRand=(tfim-tini)/1000;
        compRand=auxRand.getComp();
        movRand=auxRand.getMov();

        //GRAVA NO ARQ TXT

                                                //Counting Sort
        //Arquivo Ordenado
        arqOrd.initMov();
        arqOrd.initComp();
        tini=System.currentTimeMillis();
        arqOrd.counting();
        tfim=System.currentTimeMillis();
        ttotalOrd=(tfim-tini)/1000;
        compOrd=arqOrd.getComp();
        movOrd=arqOrd.getMov();

        //Arquivo Reverso
        auxRev.copiaArquivo(arqRev.getArquivo());
        auxRev.initMov();
        auxRev.initComp();
        tini=System.currentTimeMillis();
        auxRev.counting();
        tfim=System.currentTimeMillis();
        ttotalRev=(tfim-tini)/1000;
        compRev=auxRev.getComp();
        movRev=auxRev.getMov();

        //Arquivo Aleatorio
        auxRand.copiaArquivo(arqRand.getArquivo());
        auxRand.initMov();
        auxRand.initComp();
        tini=System.currentTimeMillis();
        auxRand.counting();
        tfim=System.currentTimeMillis();
        ttotalRand=(tfim-tini)/1000;
        compRand=auxRand.getComp();
        movRand=auxRand.getMov();

        //GRAVA NO ARQ TXT

                                                //Bucket Sort
        //Arquivo Ordenado
        arqOrd.initMov();
        arqOrd.initComp();
        tini=System.currentTimeMillis();
        arqOrd.bucket(5);
        tfim=System.currentTimeMillis();
        ttotalOrd=(tfim-tini)/1000;
        compOrd=arqOrd.getComp();
        movOrd=arqOrd.getMov();

        //Arquivo Reverso
        auxRev.copiaArquivo(arqRev.getArquivo());
        auxRev.initMov();
        auxRev.initComp();
        tini=System.currentTimeMillis();
        auxRev.bucket(5);
        tfim=System.currentTimeMillis();
        ttotalRev=(tfim-tini)/1000;
        compRev=auxRev.getComp();
        movRev=auxRev.getMov();

        //Arquivo Aleatorio
        auxRand.copiaArquivo(arqRand.getArquivo());
        auxRand.initMov();
        auxRand.initComp();
        tini=System.currentTimeMillis();
        auxRand.bucket(5);
        tfim=System.currentTimeMillis();
        ttotalRand=(tfim-tini)/1000;
        compRand=auxRand.getComp();
        movRand=auxRand.getMov();

        //GRAVA NO ARQ TXT

                                                        //Radix Sort
        //Arquivo Ordenado
        arqOrd.initMov();
        arqOrd.initComp();
        tini=System.currentTimeMillis();
        arqOrd.radix();
        tfim=System.currentTimeMillis();
        ttotalOrd=(tfim-tini)/1000;
        compOrd=arqOrd.getComp();
        movOrd=arqOrd.getMov();

        //Arquivo Reverso
        auxRev.copiaArquivo(arqRev.getArquivo());
        auxRev.initMov();
        auxRev.initComp();
        tini=System.currentTimeMillis();
        auxRev.radix();
        tfim=System.currentTimeMillis();
        ttotalRev=(tfim-tini)/1000;
        compRev=auxRev.getComp();
        movRev=auxRev.getMov();

        //Arquivo Aleatorio
        auxRand.copiaArquivo(arqRand.getArquivo());
        auxRand.initMov();
        auxRand.initComp();
        tini=System.currentTimeMillis();
        auxRand.radix();
        tfim=System.currentTimeMillis();
        ttotalRand=(tfim-tini)/1000;
        compRand=auxRand.getComp();
        movRand=auxRand.getMov();

        //GRAVA NO ARQ TXT

                                                    //Comb Sort
        //Arquivo Ordenado
        arqOrd.initMov();
        arqOrd.initComp();
        tini=System.currentTimeMillis();
        arqOrd.comb();
        tfim=System.currentTimeMillis();
        ttotalOrd=(tfim-tini)/1000;
        compOrd=arqOrd.getComp();
        movOrd=arqOrd.getMov();

        //Arquivo Reverso
        auxRev.copiaArquivo(arqRev.getArquivo());
        auxRev.initMov();
        auxRev.initComp();
        tini=System.currentTimeMillis();
        auxRev.comb();
        tfim=System.currentTimeMillis();
        ttotalRev=(tfim-tini)/1000;
        compRev=auxRev.getComp();
        movRev=auxRev.getMov();

        //Arquivo Aleatorio
        auxRand.copiaArquivo(arqRand.getArquivo());
        auxRand.initMov();
        auxRand.initComp();
        tini=System.currentTimeMillis();
        auxRand.comb();
        tfim=System.currentTimeMillis();
        ttotalRand=(tfim-tini)/1000;
        compRand=auxRand.getComp();
        movRand=auxRand.getMov();

        //GRAVA NO ARQ TXT

                                                        //Gnome Sort
        //Arquivo Ordenado
        arqOrd.initMov();
        arqOrd.initComp();
        tini=System.currentTimeMillis();
        arqOrd.gnome();
        tfim=System.currentTimeMillis();
        ttotalOrd=(tfim-tini)/1000;
        compOrd=arqOrd.getComp();
        movOrd=arqOrd.getMov();

        //Arquivo Reverso
        auxRev.copiaArquivo(arqRev.getArquivo());
        auxRev.initMov();
        auxRev.initComp();
        tini=System.currentTimeMillis();
        auxRev.gnome();
        tfim=System.currentTimeMillis();
        ttotalRev=(tfim-tini)/1000;
        compRev=auxRev.getComp();
        movRev=auxRev.getMov();

        //Arquivo Aleatorio
        auxRand.copiaArquivo(arqRand.getArquivo());
        auxRand.initMov();
        auxRand.initComp();
        tini=System.currentTimeMillis();
        auxRand.gnome();
        tfim=System.currentTimeMillis();
        ttotalRand=(tfim-tini)/1000;
        compRand=auxRand.getComp();
        movRand=auxRand.getMov();

        //GRAVA NO ARQ TXT

                                                            //Tim Sort
        //Arquivo Ordenado
        arqOrd.initMov();
        arqOrd.initComp();
        tini=System.currentTimeMillis();
        arqOrd.tim(64);
        tfim=System.currentTimeMillis();
        ttotalOrd=(tfim-tini)/1000;
        compOrd=arqOrd.getComp();
        movOrd=arqOrd.getMov();

        //Arquivo Reverso
        auxRev.copiaArquivo(arqRev.getArquivo());
        auxRev.initMov();
        auxRev.initComp();
        tini=System.currentTimeMillis();
        auxRev.tim(64);
        tfim=System.currentTimeMillis();
        ttotalRev=(tfim-tini)/1000;
        compRev=auxRev.getComp();
        movRev=auxRev.getMov();

        //Arquivo Aleatorio
        auxRand.copiaArquivo(arqRand.getArquivo());
        auxRand.initMov();
        auxRand.initComp();
        tini=System.currentTimeMillis();
        auxRand.tim(64);
        tfim=System.currentTimeMillis();
        ttotalRand=(tfim-tini)/1000;
        compRand=auxRand.getComp();
        movRand=auxRand.getMov();

        //GRAVA NO ARQ TXT
    }
    public static void main(String[] args) {
//        vetores();
//        listas();
        arquivos();
    }
}