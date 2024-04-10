import Arquivo.Arquivo_Java;
import Lista.Lista;

import java.io.*;
import java.util.Scanner;

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
        lista.bubble();
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
        double compMin, compMed, compMax, movMin, movMed, movMax;
        try {
            File arquivo = new File("tabela.txt");
            if (arquivo.exists()) {
                arquivo.delete();
            }

            FileWriter fw = new FileWriter("tabela.txt");
            BufferedWriter bw = new BufferedWriter(fw);

            // Cabeçalho
            bw.write(String.format("%-20s | %-77s | %-77s | %-77s", "Métodos", "Arquivo Ordenado",
                    "Arquivo Reverso", "Arquivo Randômico"));
            bw.newLine();
            bw.write(String.format("%-20s | ", ""));
            bw.write(
                    String.format("%-13s | %-13s | %-13s | %-13s | %-13s | ", "Comp. Prog.", "Comp. Equa.",
                            "Mov. Prog.", "Mov. Equa.", "Tempo"));
            bw.write(
                    String.format("%-13s | %-13s | %-13s | %-13s | %-13s | ", "Comp. Prog.", "Comp. Equa.",
                            "Mov. Prog.", "Mov. Equa.", "Tempo"));
            bw.write(
                    String.format("%-13s | %-13s | %-13s | %-13s | %-13s | ", "Comp. Prog.", "Comp. Equa.",
                            "Mov. Prog.", "Mov. Equa.", "Tempo"));
            bw.newLine();
            bw.write(
                    "-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            bw.newLine();
            bw.newLine();
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
        compMin=arqOrd.IDcompMin();
        movMin=arqOrd.IDmovMin();

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
        compMax=auxRev.IDcompMax();
        movMax=auxRev.IDmovMax();

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
        compMed=auxRand.IDcompMed();
        movMed=auxRand.IDmovMed();

            bw.write(String.format(
                    "%-20s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s |",
                    "Inserção Direta",
                    compOrd, compMin, movOrd, movMin, ttotalOrd,
                    compRev, compMax, movRev, movMax, ttotalRev,
                    compRand, compMed, movRand, movMed, ttotalRand));

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
        compMin=arqOrd.IDcompMin();
        movMin=arqOrd.IDmovMin();

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
        compMax=auxRev.IDcompMax();
        movMax=auxRev.IDmovMax();

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
        compMed=auxRand.IDcompMed();
        movMed=auxRand.IDmovMed();

            bw.write(String.format(
                    "%-20s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s |",
                    "Inserção Binária",
                    compOrd, compMin, movOrd, movMin, ttotalOrd,
                    compRev, compMax, movRev, movMax, ttotalRev,
                    compRand, compMed, movRand, movMed, ttotalRand));

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
        compMin=arqOrd.IDcompMin();
        movMin=arqOrd.IDmovMin();

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
        compMax=auxRev.IDcompMax();
        movMax=auxRev.IDmovMax();

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
        compMed=auxRand.IDcompMed();
        movMed=auxRand.IDmovMed();

            bw.write(String.format(
                    "%-20s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s |",
                    "Seleção Direta",
                    compOrd, compMin, movOrd, movMin, ttotalOrd,
                    compRev, compMax, movRev, movMax, ttotalRev,
                    compRand, compMed, movRand, movMed, ttotalRand));


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

            bw.write(String.format(
                    "%-20s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s |",
                    "Bubble Sort",
                    compOrd, 0, movOrd, 0, ttotalOrd,
                    compRev, 0, movRev, 0, ttotalRev,
                    compRand, 0, movRand, 0, ttotalRand));

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

            bw.write(String.format(
                    "%-20s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s |",
                    "Shake Sort",
                    compOrd, 0, movOrd, 0, ttotalOrd,
                    compRev, 0, movRev, 0, ttotalRev,
                    compRand, 0, movRand, 0, ttotalRand));

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

            bw.write(String.format(
                    "%-20s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s |",
                    "Shell Sort",
                    compOrd, 0, movOrd, 0, ttotalOrd,
                    compRev, 0, movRev, 0, ttotalRev,
                    compRand, 0, movRand, 0, ttotalRand));

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

            bw.write(String.format(
                    "%-20s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s |",
                    "Heap Sort",
                    compOrd, 0, movOrd, 0, ttotalOrd,
                    compRev, 0, movRev, 0, ttotalRev,
                    compRand, 0, movRand, 0, ttotalRand));

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

            bw.write(String.format(
                    "%-20s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s |",
                    "Quick S Pivo",
                    compOrd, 0, movOrd, 0, ttotalOrd,
                    compRev, 0, movRev, 0, ttotalRev,
                    compRand, 0, movRand, 0, ttotalRand));

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

            bw.write(String.format(
                    "%-20s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s |",
                    "Quick C Pivo",
                    compOrd, 0, movOrd, 0, ttotalOrd,
                    compRev, 0, movRev, 0, ttotalRev,
                    compRand, 0, movRand, 0, ttotalRand));

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

            bw.write(String.format(
                    "%-20s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s |",
                    "Merge Sort",
                    compOrd, 0, movOrd, 0, ttotalOrd,
                    compRev, 0, movRev, 0, ttotalRev,
                    compRand, 0, movRand, 0, ttotalRand));

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

            bw.write(String.format(
                    "%-20s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s |",
                    "Merge2 Sort",
                    compOrd, 0, movOrd, 0, ttotalOrd,
                    compRev, 0, movRev, 0, ttotalRev,
                    compRand, 0, movRand, 0, ttotalRand));

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

            bw.write(String.format(
                    "%-20s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s |",
                    "Counting Sort",
                    compOrd, 0, movOrd, 0, ttotalOrd,
                    compRev, 0, movRev, 0, ttotalRev,
                    compRand, 0, movRand, 0, ttotalRand));

                                                //Bucket Sort
        //Arquivo Ordenado
        arqOrd.initMov();
        arqOrd.initComp();
        tini=System.currentTimeMillis();
        arqOrd.bucket(8);
        tfim=System.currentTimeMillis();
        ttotalOrd=(tfim-tini)/1000;
        compOrd=arqOrd.getComp();
        movOrd=arqOrd.getMov();

        //Arquivo Reverso
        auxRev.copiaArquivo(arqRev.getArquivo());
        auxRev.initMov();
        auxRev.initComp();
        tini=System.currentTimeMillis();
        auxRev.bucket(8);
        tfim=System.currentTimeMillis();
        ttotalRev=(tfim-tini)/1000;
        compRev=auxRev.getComp();
        movRev=auxRev.getMov();

        //Arquivo Aleatorio
        auxRand.copiaArquivo(arqRand.getArquivo());
        auxRand.initMov();
        auxRand.initComp();
        tini=System.currentTimeMillis();
        auxRand.bucket(8);
        tfim=System.currentTimeMillis();
        ttotalRand=(tfim-tini)/1000;
        compRand=auxRand.getComp();
        movRand=auxRand.getMov();

            bw.write(String.format(
                    "%-20s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s |",
                    "Bucket Sort",
                    compOrd, 0, movOrd, 0, ttotalOrd,
                    compRev, 0, movRev, 0, ttotalRev,
                    compRand, 0, movRand, 0, ttotalRand));

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

            bw.write(String.format(
                    "%-20s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s |",
                    "Radix Sort",
                    compOrd, 0, movOrd, 0, ttotalOrd,
                    compRev, 0, movRev, 0, ttotalRev,
                    compRand, 0, movRand, 0, ttotalRand));

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

            bw.write(String.format(
                    "%-20s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s |",
                    "Comb Sort",
                    compOrd, 0, movOrd, 0, ttotalOrd,
                    compRev, 0, movRev, 0, ttotalRev,
                    compRand, 0, movRand, 0, ttotalRand));

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

            bw.write(String.format(
                    "%-20s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s |",
                    "Gnome Sort",
                    compOrd, 0, movOrd, 0, ttotalOrd,
                    compRev, 0, movRev, 0, ttotalRev,
                    compRand, 0, movRand, 0, ttotalRand));

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

            bw.write(String.format(
                    "%-20s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s |",
                    "Tim Sort",
                    compOrd, 0, movOrd, 0, ttotalOrd,
                    compRev, 0, movRev, 0, ttotalRev,
                    compRand, 0, movRand, 0, ttotalRand));


            // Fechar o arquivo
            bw.close();
            fw.close();

        } catch (IOException e) {
            System.err.println("Erro ao gravar arquivo: " + e.getMessage());
        }
    }
    public static void main(String[] args) {
//        vetores();
//        listas();
//        arquivos();
        teste();
    }

    private static void teste() {
        Arquivo_Java arqOrd = new Arquivo_Java("./arquivoOrdenado.dat");
        Arquivo_Java arqRev = new Arquivo_Java("./arquivoReverso.dat");
        Arquivo_Java arqRand = new Arquivo_Java("./arquivoAleatorio.dat");
        Arquivo_Java auxRev= new Arquivo_Java(""), auxRand = new Arquivo_Java("");
        arqRand.geraArquivoRandomico(1024);
        arqOrd.geraArquivoReverso(1024);
        arqRev.geraArquivoOrdenado(1024);
        long ttotalOrd, ttotalRev, ttotalRand, tini, tfim;
        int compOrd, compRev, compRand, movOrd, movRev, movRand;
        double compMin, compMed, compMax, movMin, movMed, movMax;

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
        arqOrd.exibirArq();

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
        auxRev.exibirArq();

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
        auxRand.exibirArq();
    }

}