package Arquivo;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;

//... classe Arquivo (onde vai estar o m�todo para ordernar, etc) ....
public class Arquivo_Java {
    private String nomearquivo;
    private RandomAccessFile arquivo;
    private int c, m;

    public RandomAccessFile getArquivo() {
        return arquivo;
    }

    public int getComp() {
        return c;
    }

    public int getMov() {
        return m;
    }

    public Arquivo_Java(String nomearquivo) {
        try {
            File file = new File(nomearquivo);
            if (file.exists()) {
                file.delete(); // Exclui o arquivo se ele existir
            }
            arquivo = new RandomAccessFile(nomearquivo, "rw");
        } catch (IOException e) {
        }
    }

    public void initComp(){
        c=0;
    }
    public void initMov(){
        m=0;
    }
    public void truncate(long pos) //desloca eof
    {
        try {
            arquivo.setLength(pos * Registro.length());
        } catch (IOException exc) {
        }
    }

    //semelhante ao feof() da linguagem C
    //verifica se o ponteiro esta no <EOF> do arquivo
    public boolean eof() {
        boolean retorno = false;
        try {
            if (arquivo.getFilePointer() == arquivo.length())
                retorno = true;
        } catch (IOException e) {
        }
        return (retorno);
    }

    //insere um Arquivo.Registro no final do arquivo, passado por par�metro
    public void inserirRegNoFinal(Registro reg) {
        seekArq(filesize());//ultimo byte
        reg.gravaNoArq(arquivo);
    }

    public void copiaArquivo(RandomAccessFile arquivo){
        Registro reg = new Registro();
        try {
            File file = new File("arquivoAuxiliar.dat");
            if (file.exists()) {
                file.delete(); // Exclui o arquivo se ele existir
            }
            this.arquivo = new RandomAccessFile("arquivoAuxiliar.dat", "rw");
            arquivo.seek(0);
            this.arquivo.seek(0);
            while(arquivo.getFilePointer() != arquivo.length()){
                reg.leDoArq(arquivo);
                reg.gravaNoArq(this.arquivo);
            }
        } catch (IOException e) {
        }
    }

    public int filesize() {
        try{
            return (int)arquivo.length()/Registro.length();
        } catch (IOException e){

        }
        return 0;
    }

    public void exibirArq() {
//        int i;
//        Registro aux = new Registro();
//        seekArq(0);
//        i = 0;
//        while (!this.eof()) {
//            System.out.println("Posicao " + i);
//            aux.leDoArq(arquivo);
//            aux.exibirReg();
//            i++;
//        }
        int i;
        Registro aux = new Registro();
        seekArq(0);
        i = 0;
        while (!this.eof()) {
            //System.out.println("Posicao " + i);
            aux.leDoArq(arquivo);
            aux.exibirRegSimplificado();
            i++;
        }
        System.out.print("|");
        System.out.print("\n");
    }

    public void exibirUmRegistro(int pos) {
        Registro aux = new Registro();
        seekArq(pos);
        System.out.println("Posicao " + pos);
        aux.leDoArq(arquivo);
        aux.exibirReg();
    }

    public void seekArq(int pos) {
        try {
            arquivo.seek(pos * Registro.length());
        } catch (IOException e) {
        }
    }

    public void leArq() {
        int codigo, idade;
        String nome;
        codigo = Entrada.leInteger("Digite o c�digo");
        while (codigo != 0) {
            nome = Entrada.leString("Digite o nome");
            idade = Entrada.leInteger("Digite a idade");
            inserirRegNoFinal(new Registro(codigo, nome, idade));
            codigo = Entrada.leInteger("Digite o c�digo");
        }
    }
    public void executa() {
        leArq();
        exibirArq();
    }
    public void geraArquivoRandomico(int qtd) {
        Random random = new Random();
        for(int i = 0; i < qtd; i++){
            int codigo = random.nextInt(1024) + 1;
            String nome = "Nome" + codigo;
            int idade = random.nextInt(101);
            inserirRegNoFinal(new Registro(codigo, nome, idade));
        }
    }
    public void geraArquivoOrdenado(int qtd) {
        Random random = new Random();
        for(int i = 0; i < qtd; i++){
            int codigo = i;
            String nome = "Nome" + codigo;
            int idade = random.nextInt(101);
            inserirRegNoFinal(new Registro(codigo, nome, idade));
        }
    }
    public void geraArquivoReverso(int qtd) {
        Random random = new Random();
        for(int i = qtd; i > 0; i--){
            int codigo = i;
            String nome = "Nome" + codigo;
            int idade = random.nextInt(101);
            inserirRegNoFinal(new Registro(codigo, nome, idade));
        }
    }

    public double IDcompMin(){
        return filesize()-1;
    }

    public double IDcompMed(){
        return ((filesize()*filesize())+filesize()-2)/4;
    }

    public double IDcompMax(){
        return ((filesize()*filesize())+filesize()-4)/4;
    }

    public double IDmovMin(){
        return 3*(filesize()-1);
    }

    public double IDmovMed(){
        return ((filesize()*filesize())+9*filesize()-10)/4;
    }

    public double IDmovMax(){
        return ((filesize()*filesize())+3*filesize()-4)/2;
    }

    public void insercaoDireta(){ //inicia i no segundo elemento, enquanto i>0 e aux maior que vet[i-1] vai trocando, dps incrementa i ate tl
        Registro aux = new Registro();
        Registro ant = new Registro();
        Registro atual;
        int pos;
        for(int i=1;i<filesize();i++){
            seekArq(i);
            aux.leDoArq(arquivo);
            atual=aux;
            seekArq(i-1);
            ant.leDoArq(arquivo);
            pos=i;
            c++;
            while(pos>0 && atual.getCodigo()<ant.getCodigo()){
                seekArq(pos);
                ant.gravaNoArq(arquivo);
                m++;
                seekArq(pos-1);
                atual.gravaNoArq(arquivo);
                m++;
                pos--;
                if(pos>0){
                    seekArq(pos);
                    atual.leDoArq(arquivo);
                    seekArq(pos-1);
                    ant.leDoArq(arquivo);
                }
                c++;
            }
            seekArq(pos);
            aux.gravaNoArq(arquivo);
            m++;
        }
    }
    public double IBcompMin(){
        return filesize() * (Math.log(filesize()) - Math.log(2.71828) + 0.5);
    }

    public double IBcompMed(){
        return filesize() * (Math.log(filesize()) - Math.log(2.71828) + 0.5);    }

    public double IBcompMax(){
        return filesize() * (Math.log(filesize()) - Math.log(2.71828) + 0.5);
    }

    public double IBmovMin(){
        return 3*(filesize()-1);
    }

    public double IBmovMed(){
        return ((filesize()*filesize())+9*filesize()-10)/4;
    }

    public double IBmovMax(){
        return ((filesize()*filesize())+3*filesize()-4)/2;
    }
    public int buscaBinaria(int chave, int tl){
        int ini=0,fim=tl-1,meio=fim/2;
        Registro aux= new Registro();
        seekArq(meio);
        aux.leDoArq(arquivo);
        c++;
        while(ini<fim && chave!=aux.getCodigo()){
            c++;
            if(chave>aux.getCodigo()){
                ini=meio+1;
            }
            else{
                fim=meio-1;
            }
            meio=(ini+fim)/2;
            seekArq(meio);
            aux.leDoArq(arquivo);
            c++;
        }
        c++;
        if(chave>aux.getCodigo()){
            return meio+1;
        }
        return meio;
    }

    public void insercaoBinaria(){
        Registro aux = new Registro();
        Registro atual = new Registro();
        Registro ant = new Registro();
        int pos,j;
        for(int i=1;i<filesize();i++){
            seekArq(i);
            aux.leDoArq(arquivo);
            atual=aux;
            pos=buscaBinaria(aux.getCodigo(), i);
            seekArq(i-1);
            ant.leDoArq(arquivo);
            j=i;
            while(j>pos){
                seekArq(j);
                ant.gravaNoArq(arquivo);
                m++;
                seekArq(j-1);
                atual.gravaNoArq(arquivo);
                m++;
                j--;
                if(j>0){
                    seekArq(j);
                    atual.leDoArq(arquivo);
                    seekArq(j-1);
                    ant.leDoArq(arquivo);
                }
            }
            seekArq(pos);
            aux.gravaNoArq(arquivo);
            m++;
        }
    }

    public double SDcompMin(){
        return ((filesize()*filesize())-filesize())/2;
    }
    public double SDcompMed(){
        return ((filesize()*filesize())-filesize())/2;
    }
    public double SDcompMax(){
        return ((filesize()*filesize())-filesize())/2;
    }
    public double SDmovMin(){
        return 3*(filesize()-1);
    }
    public double SDmovMed(){
        return filesize() * (Math.log(filesize()) + 0.577216);
    }
    public double SDmovMax(){
        return ((filesize() * filesize()) / 4) + 3 * (filesize() - 1);
    }
    public void selecaoDireta(){ //posiciona no inicio, vai do segundo ao final procurando um menor, se achar, troca
        Registro aux = new Registro();
        Registro menor = new Registro();
        int posmenor;
        int tam = filesize();
        for(int i=0;i<tam-1;i++){
            posmenor=i;
            for(int j=i+1;j<tam;j++){
                seekArq(j);
                aux.leDoArq(arquivo);
                seekArq(posmenor);
                menor.leDoArq(arquivo);
                c++;
                if(aux.getCodigo()<menor.getCodigo()){
                    posmenor=j;
                }
            }
            seekArq(posmenor);
            menor.leDoArq(arquivo);
            seekArq(i);
            aux.leDoArq(arquivo);
            seekArq(i);
            menor.gravaNoArq(arquivo);
            m++;
            seekArq(posmenor);
            aux.gravaNoArq(arquivo);
            m++;
        }
    }
    public double BBcompMin(){
        return ((filesize()*filesize())-filesize())/2;
    }
    public double BBcompMed(){
        return ((filesize()*filesize())-filesize())/2;
    }
    public double BBcompMax(){
        return ((filesize()*filesize())-filesize())/2;
    }
    public double BBmovMin(){
        return 0;
    }
    public double BBmovMed(){
        return 3 * (filesize()*filesize() - filesize()) / 2;
    }
    public double BBmovMax(){
        return 3 * (filesize()*filesize() - filesize()) / 4;
    }
    public void bubble() {
        Registro reg1 = new Registro();
        Registro reg2 = new Registro();
        int tam = filesize()-1;
        boolean troca = true;
        while(tam>1 && troca){
            troca=false;
            for(int i=0;i<tam;i++){
                seekArq(i);
                reg1.leDoArq(arquivo);
                reg2.leDoArq(arquivo);
                c++;
                if(reg1.getCodigo()>reg2.getCodigo()){
                    seekArq(i);
                    reg2.gravaNoArq(arquivo);
                    m++;
                    reg1.gravaNoArq(arquivo);
                    m++;
                    troca=true;
                }
            }
            tam--;
        }
    }
    public double SScompMin(){
        return ((filesize()*filesize())-filesize())/2;
    }
    public double SScompMed(){
        return ((filesize()*filesize())-filesize())/2;
    }
    public double SScompMax(){
        return ((filesize()*filesize())-filesize())/2;
    }
    public double SSmovMin(){
        return 0;
    }
    public double SSmovMed(){
        return 3 * (filesize()*filesize() - filesize()) / 2;
    }
    public double SSmovMax(){
        return 3 * (filesize()*filesize() - filesize()) / 4;
    }
    public void shake() {
        Registro reg1 = new Registro();
        Registro reg2 = new Registro();
        int fim = filesize()-1;
        int ini=0;
        boolean troca = true;
        while(ini < fim && troca){
            troca=false;
            for(int i=ini;i<fim;i++){
                seekArq(i);
                reg1.leDoArq(arquivo);
                reg2.leDoArq(arquivo);
                c++;
                if(reg1.getCodigo()>reg2.getCodigo()){
                    seekArq(i);
                    reg2.gravaNoArq(arquivo);
                    m++;
                    reg1.gravaNoArq(arquivo);
                    m++;
                    troca=true;
                }
            }
            fim--;
            if(troca){
                for(int i=fim;i>ini;i--){
                    seekArq(i);
                    reg1.leDoArq(arquivo);
                    seekArq(i-1);
                    reg2.leDoArq(arquivo);
                    c++;
                    if(reg1.getCodigo()<reg2.getCodigo()){
                        seekArq(i);
                        reg2.gravaNoArq(arquivo);
                        m++;
                        seekArq(i-1);
                        reg1.gravaNoArq(arquivo);
                        m++;
                        troca=true;
                    }
                }
            }
            ini++;
        }
    }

    public void heap() {
        Registro reg1 = new Registro();
        Registro reg2 = new Registro();
        int TL = filesize(),  pai, FD, FE, maiorF;
        while(TL>1){
            pai=TL/2-1;
            while(pai>=0){
                FE=pai*2+1;
                FD=FE+1;
                maiorF=FE;
                seekArq(FE);
                reg1.leDoArq(arquivo);
                if(!eof())
                    reg2.leDoArq(arquivo);
                c++;
                if(FD<TL && reg1.getCodigo()<reg2.getCodigo())
                    maiorF=FD;
                seekArq(maiorF);
                reg1.leDoArq(arquivo);
                seekArq(pai);
                reg2.leDoArq(arquivo);
                c++;
                if(reg1.getCodigo()>reg2.getCodigo()){
                    seekArq(maiorF);
                    reg2.gravaNoArq(arquivo);
                    m++;
                    seekArq(pai);
                    reg1.gravaNoArq(arquivo);
                    m++;
                }
                pai--;
            }
            seekArq(0);
            reg1.leDoArq(arquivo);
            seekArq(TL-1);
            reg2.leDoArq(arquivo);
            seekArq(0);
            reg2.gravaNoArq(arquivo);
            m++;
            seekArq(TL-1);
            reg1.gravaNoArq(arquivo);
            m++;
            TL--;
        }
    }

    public void shell() {
        Registro reg = new Registro();
        Registro aux = new Registro();
        int i,j,dist=1;
        while(dist<filesize())
            dist=dist*3+1;
        dist=dist/3;
        while(dist>0){
            for(i=dist;i<filesize();i++){
                seekArq(i);
                aux.leDoArq(arquivo);
                j=i;
                if(j-dist>=0){
                    seekArq(j-dist);
                    reg.leDoArq(arquivo);
                }
                c++;
                while(j-dist>=0 && aux.getCodigo()<reg.getCodigo()){
                    seekArq(j);
                    reg.gravaNoArq(arquivo);
                    m++;
                    j=j-dist;
                    if(j-dist>=0){
                        seekArq(j-dist);
                        reg.leDoArq(arquivo);
                    }
                    c++;
                }
                seekArq(j);
                aux.gravaNoArq(arquivo);
                m++;
            }
            dist=dist/3;
        }
    }

    public void counting() {
        Registro reg = new Registro();
        seekArq(0);
        reg.leDoArq(arquivo);
        int maior=reg.getCodigo();
        while(!eof()){
            reg.leDoArq(arquivo);
            c++;
            if(reg.getCodigo()>maior)
                maior= reg.getCodigo();
        }
        int cont[] = new int[maior+1];
        seekArq(0);
        while(!eof()){
            reg.leDoArq(arquivo);
            cont[reg.getCodigo()]++;
        }
        for (int i = 1; i <= maior; i++) {
            cont[i]=cont[i-1]+cont[i];
        }
        Arquivo_Java saida = new Arquivo_Java("saida.dat");
        for(int i = filesize()-1;i>=0;i--){
            seekArq(i);
            reg.leDoArq(arquivo);
            saida.seekArq(cont[reg.getCodigo()]-1);
            cont[reg.getCodigo()]--;
            reg.gravaNoArq(saida.arquivo);
            m++;
        }
        seekArq(0);
        saida.seekArq(0);
        while(!eof()){
            reg.leDoArq(saida.arquivo);
            reg.gravaNoArq(arquivo);
            m++;
        }
        try {
            saida.arquivo.close();
        }catch (Exception e){}
        File fsaida = new File("saida.dat");
        fsaida.delete();
    }

    public void quickSPivo() {
        quickSP(0,filesize()-1);
    }

    private void quickSP(int ini, int fim) {
        Registro reg1 = new Registro();
        Registro reg2 = new Registro();
        int i=ini, j=fim,aux;
        boolean flag=true;
        while(i<j){
            seekArq(i);
            reg1.leDoArq(arquivo);
            seekArq(j);
            reg2.leDoArq(arquivo);
            if(flag) {
                c++;
                while (i < j && reg1.getCodigo() <= reg2.getCodigo()) {
                    i++;
                    seekArq(i);
                    reg1.leDoArq(arquivo);
                    seekArq(j);
                    reg2.leDoArq(arquivo);
                    c++;
                }
            }
            else {
                c++;
                while (i < j && reg1.getCodigo() <= reg2.getCodigo()) {
                    j--;
                    seekArq(i);
                    reg1.leDoArq(arquivo);
                    seekArq(j);
                    reg2.leDoArq(arquivo);
                    c++;
                }
            }
            if(i<j) {
                seekArq(i);
                reg1.leDoArq(arquivo);
                seekArq(j);
                reg2.leDoArq(arquivo);
                seekArq(i);
                reg2.gravaNoArq(arquivo);
                m++;
                seekArq(j);
                reg1.gravaNoArq(arquivo);
                m++;
            }
            flag = !flag;
        }
        if(ini<i-1)
            quickSP(ini,i-1);
        if(j+1<fim)
            quickSP(j+1,fim);
    }

    public void quickCPivo() {
        quickCP(0,filesize()-1);
    }

    private void quickCP(int ini, int fim) {
        int i=ini, j=fim, aux;
        Registro reg1 = new Registro();
        Registro reg2 = new Registro();
        seekArq((ini+fim)/2);
        reg1.leDoArq(arquivo);
        int pivo=reg1.getCodigo();
        while(i<j){
            seekArq(i);
            reg1.leDoArq(arquivo);
            seekArq(j);
            reg2.leDoArq(arquivo);
            c++;
            while(reg1.getCodigo()<pivo) {
                i++;
                seekArq(i);
                reg1.leDoArq(arquivo);
                c++;
            }
            c++;
            while(reg2.getCodigo()>pivo) {
                j--;
                seekArq(j);
                reg2.leDoArq(arquivo);
                c++;
            }
            if(i<=j){
                seekArq(i);
                reg1.leDoArq(arquivo);
                seekArq(j);
                reg2.leDoArq(arquivo);
                seekArq(i);
                reg2.gravaNoArq(arquivo);
                m++;
                seekArq(j);
                reg1.gravaNoArq(arquivo);
                m++;
                i++;
                j--;
            }
        }
        if(ini<j)
            quickCP(ini,j);
        if(i<fim)
            quickCP(i, fim);
    }

    public void bucket(int qtd) {
        Registro reg1 = new Registro();
        seekArq(0);
        reg1.leDoArq(arquivo);
        int maior= reg1.getCodigo(), i,index;
        int pos[] = new int[qtd];
        for (i = 1; i < filesize(); i++) {
            seekArq(i);
            reg1.leDoArq(arquivo);
            c++;
            if(reg1.getCodigo()>maior)
                maior=reg1.getCodigo();
        }
        Arquivo_Java baldes[] = new Arquivo_Java[qtd];
        for (i = 0; i < qtd; i++) {
            baldes[i] = new Arquivo_Java("balde"+i+".dat");
        }
        for (i = 0; i < filesize(); i++) {
            seekArq(i);
            reg1.leDoArq(arquivo);
            index=(reg1.getCodigo()*qtd)/(maior+1);
            baldes[index].seekArq(pos[index]);
            pos[index]++;
            reg1.gravaNoArq(baldes[index].arquivo);
            m++;
        }
        for (i = 0; i < qtd; i++) {
            if(pos[i]!=0){
                baldes[i].insercaoDireta();
            }
        }
        i=0;
        int j = 0;
        while (i < filesize()) {
            baldes[j].seekArq(0);
            while(j<qtd && !baldes[j].eof()){
                reg1.leDoArq(baldes[j].arquivo);
                seekArq(i);
                reg1.gravaNoArq(arquivo);
                m++;
                i++;
            }
            j++;
        }
        File fbalde;
        for (i = 0; i < qtd; i++) {
            try {
                baldes[i].arquivo.close();
            }catch (Exception e){}
            fbalde = new File("balde"+i+".dat");
            fbalde.delete();
        }
    }
    public void countingRadix(int chave) {
        Registro reg = new Registro();
        seekArq(0);
        reg.leDoArq(arquivo);
        int maior=reg.getCodigo(), index;
        while(!eof()){
            reg.leDoArq(arquivo);
            c++;
            if(reg.getCodigo()>maior)
                maior= reg.getCodigo();
        }
        int cont[] = new int[maior+1];
        seekArq(0);
        while(!eof()){
            reg.leDoArq(arquivo);
            index=(reg.getCodigo()/chave)%10;
            cont[index]++;
        }
        for (int i = 1; i <= maior; i++) {
            cont[i]=cont[i-1]+cont[i];
        }
        Arquivo_Java saida = new Arquivo_Java("saida.dat");
        for(int i = filesize()-1;i>=0;i--){
            seekArq(i);
            reg.leDoArq(arquivo);
            index=(reg.getCodigo()/chave)%10;
            saida.seekArq(cont[index]-1);
            reg.gravaNoArq(saida.arquivo);
            m++;
            cont[index]--;
        }
        seekArq(0);
        saida.seekArq(0);
        while(!eof()){
            reg.leDoArq(saida.arquivo);
            reg.gravaNoArq(arquivo);
            m++;
        }
        try {
            saida.arquivo.close();
        }catch (Exception e){}
        File fsaida = new File("saida.dat");
        fsaida.delete();
    }
    public void radix() {
        Registro reg = new Registro();
        int chave;
        seekArq(0);
        reg.leDoArq(arquivo);
        int maior=reg.getCodigo();
        while(!eof()){
            reg.leDoArq(arquivo);
            c++;
            if(reg.getCodigo()>maior)
                maior= reg.getCodigo();
        }
        for (chave=1; maior/chave>0; chave*=10)
            countingRadix(chave);
    }

    public void comb() {
        Registro reg1 = new Registro();
        Registro reg2 = new Registro();
        int gap=filesize(),aux;
        boolean troca=true;
        while(gap > 1 || troca){
            gap=gap*10/13;
            if(gap<=1)
                gap=1;
            troca=false;
            int i=0;
            while(i+gap<filesize()){
                seekArq(i);
                reg1.leDoArq(arquivo);
                seekArq(i+gap);
                reg2.leDoArq(arquivo);
                c++;
                if(reg1.getCodigo()>reg2.getCodigo()){
                    seekArq(i);
                    reg2.gravaNoArq(arquivo);
                    m++;
                    seekArq(i+gap);
                    reg1.gravaNoArq(arquivo);
                    m++;
                    troca=true;
                }
                i++;
            }
        }
    }

    public void gnome() {
        Registro reg1 = new Registro();
        Registro reg2 = new Registro();
        int i=0;
        while(i<filesize()){
            if (i==0)
                i++;
            seekArq(i);
            reg1.leDoArq(arquivo);
            seekArq(i-1);
            reg2.leDoArq(arquivo);
            c++;
            if(reg1.getCodigo()>=reg2.getCodigo())
                i++;
            else{
                seekArq(i-1);
                reg1.gravaNoArq(arquivo);
                m++;
                reg2.gravaNoArq(arquivo);
                m++;
                i--;
            }
        }
    }

        private void particao(Arquivo_Java arq1, Arquivo_Java arq2) {
            Registro reg = new Registro();
            arq1.seekArq(0);
            arq2.seekArq(0);
            for (int i = 0; i < filesize()/2; i++) {
                seekArq(i);
                reg.leDoArq(arquivo);
                reg.gravaNoArq(arq1.arquivo);
                m++;
                seekArq(i+(filesize()/2));
                reg.leDoArq(arquivo);
                reg.gravaNoArq(arq2.arquivo);
                m++;
            }
        }

    private void fusao(Arquivo_Java arq1, Arquivo_Java arq2, int seq) {
        Registro reg1 = new Registro();
        Registro reg2 = new Registro();
        int i=0,j=0,k=0,aux=seq;
        while(k<filesize()){
            while (i < seq && j < seq) {
                arq1.seekArq(i);
                arq2.seekArq(j);
                reg1.leDoArq(arq1.arquivo);
                reg2.leDoArq(arq2.arquivo);
                c++;
                if (reg1.getCodigo() > reg2.getCodigo()){
                    seekArq(k++);
                    reg2.gravaNoArq(arquivo);
                    m++;
                    j++;
                }
                else {
                    seekArq(k++);
                    reg1.gravaNoArq(arquivo);
                    m++;
                    i++;
                }
            }
            while(i<seq) {
                arq1.seekArq(i++);
                reg1.leDoArq(arq1.arquivo);
                seekArq(k++);
                reg1.gravaNoArq(arquivo);
                m++;
            }
            while(j<seq) {
                arq2.seekArq(j++);
                reg2.leDoArq(arq2.arquivo);
                seekArq(k++);
                reg2.gravaNoArq(arquivo);
                m++;
            }
            seq=seq+aux;
        }
    }

    public void merge(){
        Arquivo_Java arq1 = new Arquivo_Java("aux1.dat");
        Arquivo_Java arq2 = new Arquivo_Java("aux2.dat");
        int seq=1;
        while(seq<filesize()){
            particao(arq1,arq2);
            fusao(arq1,arq2,seq);
            seq=seq*2;
        }
        try {
            arq1.arquivo.close();
            arq2.arquivo.close();
        }catch (Exception e){}
        File aux1 = new File("aux1.dat");
        File aux2 = new File("aux2.dat");
        aux1.delete();
        aux2.delete();
    }
    private void fusaoM(Arquivo_Java aux, int ini1, int fim1, int ini2, int fim2) {
        Registro regi = new Registro(), regj = new Registro();
        int i=ini1, j=ini2, k=0;
        aux.seekArq(0);
        while(i<=fim1 && j<=fim2){
            seekArq(i);
            regi.leDoArq(arquivo);
            seekArq(j);
            regj.leDoArq(arquivo);
            c++;
            if (regi.getCodigo() > regj.getCodigo()){
                regj.gravaNoArq(aux.arquivo);
                m++;
                j++;
                k++;
            }
            else {
                regi.gravaNoArq(aux.arquivo);
                m++;
                i++;
                k++;
            }
        }
        while(i<=fim1){
            seekArq(i);
            regi.leDoArq(arquivo);
            regi.gravaNoArq(aux.arquivo);
            m++;
            i++;
            k++;
        }
        while(j<=fim2) {
            seekArq(j);
            regj.leDoArq(arquivo);
            regj.gravaNoArq(aux.arquivo);
            m++;
            j++;
            k++;
        }
        for(i=0;i<k;i++) {
            seekArq(i+ini1);
            aux.seekArq(i);
            regi.leDoArq(aux.arquivo);
            regi.gravaNoArq(arquivo);
            m++;
        }
    }

    private void mergeDiv(Arquivo_Java aux, int esq, int dir) {
        int meio;
        if(esq<dir){
            meio=(esq+dir)/2;
            mergeDiv(aux, esq, meio);
            mergeDiv(aux, meio+1, dir);
            fusaoM(aux, esq, meio, meio+1, dir);
        }
    }

    public void mergeSort(){
        Arquivo_Java aux = new Arquivo_Java("aux.dat");
        mergeDiv(aux,0,filesize()-1);
        try {
            aux.arquivo.close();
        }catch (Exception e){}
        File aux2 = new File("aux.dat");
        aux2.delete();
    }

    private int tamMin(int runs) {
        int tam=runs;
        int i = 0;
        while (tam >= runs) {
            tam /= runs;
            i++;
        }
        return tam + i;
    }
    private void insercaoDiretaTim(int esq, int dir){
        Registro aux = new Registro();
        Registro ant = new Registro();
        Registro atual;
        int pos;
        for (int i = esq + 1; i <= dir; i++) {
            seekArq(i);
            aux.leDoArq(arquivo);
            atual=aux;
            seekArq(i-1);
            ant.leDoArq(arquivo);
            pos=i;
            c++;
            while (pos > esq && atual.getCodigo()<ant.getCodigo()) {
                seekArq(pos);
                ant.gravaNoArq(arquivo);
                m++;
                seekArq(pos-1);
                atual.gravaNoArq(arquivo);
                m++;
                pos--;
                if(pos>esq){
                    seekArq(pos);
                    atual.leDoArq(arquivo);
                    seekArq(pos-1);
                    ant.leDoArq(arquivo);
                }
                c++;
            }
            seekArq(pos);
            aux.gravaNoArq(arquivo);
            m++;
        }
    }

    private void mergeTim(int esq, int meio, int dir){
        int tam1 = meio - esq + 1, tam2 = dir - meio;
        Registro reg1 = new Registro();
        Registro reg2 = new Registro();
        Arquivo_Java arq1 = new Arquivo_Java("arq1.dat");
        Arquivo_Java arq2 = new Arquivo_Java("arq2.dat");
        arq1.seekArq(0);
        arq2.seekArq(0);
        for (int pos = 0; pos < tam1; pos++){
            seekArq(esq+pos);
            reg1.leDoArq(arquivo);
            reg1.gravaNoArq(arq1.arquivo);
            m++;
        }
        for (int pos = 0; pos < tam2; pos++){
            seekArq(meio + 1 + pos);
            reg1.leDoArq(arquivo);
            reg1.gravaNoArq(arq2.arquivo);
            m++;
        }
        int i = 0,j = 0,k = esq;
        while (i < tam1 && j < tam2) {
            arq1.seekArq(i);
            reg1.leDoArq(arq1.arquivo);
            arq2.seekArq(j);
            reg2.leDoArq(arq2.arquivo);
            if (reg1.getCodigo() <= reg2.getCodigo()){
                seekArq(k);
                reg1.gravaNoArq(arquivo);
                m++;
                i++;
                k++;
            }
            else{
                seekArq(k);
                reg2.gravaNoArq(arquivo);
                m++;
                j++;
                k++;
            }
        }
        while (i < tam1){
            arq1.seekArq(i);
            reg1.leDoArq(arq1.arquivo);
            seekArq(k);
            reg1.gravaNoArq(arquivo);
            m++;
            i++;
            k++;
        }
        while (j < tam2){
            arq2.seekArq(j);
            reg2.leDoArq(arq2.arquivo);
            seekArq(k);
            reg2.gravaNoArq(arquivo);
            m++;
            j++;
            k++;
        }
        try {
            arq1.arquivo.close();
            arq2.arquivo.close();
        }catch (Exception e){}
        File aux1 = new File("arq1.dat");
        File aux2 = new File("arq2.dat");
        aux1.delete();
        aux2.delete();
    }
    private int min(int i, int j) {
        return (i <= j) ? i : j;
    }
    public void tim(int runs) {
        int run = tamMin(runs);

        for (int i = 0; i < filesize(); i += run) {
            insercaoDiretaTim(i,min((i + runs - 1), (filesize() - 1)));
        }

        for (int tam = run; tam < filesize(); tam = 2 * tam) {
            for (int esq = 0; esq < filesize(); esq += 2 * tam) {
                int meio = esq + tam - 1;
                int dir = min((esq + 2 * tam - 1),(filesize() - 1));
                if (meio < dir)
                    mergeTim(esq, meio, dir);
            }
        }
    }
}