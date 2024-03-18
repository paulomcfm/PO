package Arquivo;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;

//... classe Arquivo (onde vai estar o m�todo para ordernar, etc) ....
public class Arquivo_Java {
    private String nomearquivo;
    private RandomAccessFile arquivo;

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
    public void preencher(int qtd) {
        Random random = new Random();
        for(int i = 0; i < qtd; i++){
            int codigo = random.nextInt(100) + 1;
            String nome = "Nome" + codigo;
            int idade = random.nextInt(101);
            inserirRegNoFinal(new Registro(codigo, nome, idade));
        }
    }

    public void insercaoDireta(){ //inicia i no segundo elemento, enquanto i>0 e aux maior que vet[i-1] vai trocando, dps incrementa i ate tl
        Registro aux = new Registro();
        Registro ant = new Registro();
        Registro atual = new Registro();
        int pos;
        for(int i=1;i<filesize();i++){
            seekArq(i);
            aux.leDoArq(arquivo);
            atual=aux;
            seekArq(i-1);
            ant.leDoArq(arquivo);
            pos=i;
            while(pos>0 && atual.getCodigo()<ant.getCodigo()){
                seekArq(pos);
                ant.gravaNoArq(arquivo);
                seekArq(pos-1);
                atual.gravaNoArq(arquivo);
                pos--;
                if(pos>0){
                    seekArq(pos);
                    atual.leDoArq(arquivo);
                    seekArq(pos-1);
                    ant.leDoArq(arquivo);
                }
            }
            seekArq(pos);
            aux.gravaNoArq(arquivo);
        }
    }

    public int buscaBinaria(int chave, int tl){
        int ini=0,fim=tl-1,meio=fim/2;
        Registro aux= new Registro();
        seekArq(meio);
        aux.leDoArq(arquivo);
        while(ini<fim && chave!=aux.getCodigo()){
            if(chave>aux.getCodigo()){
                ini=meio+1;
            }
            else{
                fim=meio-1;
            }
            meio=(ini+fim)/2;
            seekArq(meio);
            aux.leDoArq(arquivo);
        }
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
                seekArq(j-1);
                atual.gravaNoArq(arquivo);
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
        }
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
            seekArq(posmenor);
            aux.gravaNoArq(arquivo);
        }
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
                if(reg1.getCodigo()>reg2.getCodigo()){
                    seekArq(i);
                    reg2.gravaNoArq(arquivo);
                    reg1.gravaNoArq(arquivo);
                    troca=true;
                }
            }
            tam--;
        }
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
                if(reg1.getCodigo()>reg2.getCodigo()){
                    seekArq(i);
                    reg2.gravaNoArq(arquivo);
                    reg1.gravaNoArq(arquivo);
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
                    if(reg1.getCodigo()<reg2.getCodigo()){
                        seekArq(i);
                        reg2.gravaNoArq(arquivo);
                        seekArq(i-1);
                        reg1.gravaNoArq(arquivo);
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
                if(FD<TL && reg1.getCodigo()<reg2.getCodigo()){
                    maiorF=FD;
                }
                seekArq(maiorF);
                reg1.leDoArq(arquivo);
                seekArq(pai);
                reg2.leDoArq(arquivo);
                if(reg1.getCodigo()>reg2.getCodigo()){
                    seekArq(maiorF);
                    reg2.gravaNoArq(arquivo);
                    seekArq(pai);
                    reg1.gravaNoArq(arquivo);
                }
                pai--;
            }
            seekArq(0);
            reg1.leDoArq(arquivo);
            seekArq(TL-1);
            reg2.leDoArq(arquivo);
            seekArq(0);
            reg2.gravaNoArq(arquivo);
            seekArq(TL-1);
            reg1.gravaNoArq(arquivo);
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
                while(j-dist>=0 && aux.getCodigo()<reg.getCodigo()){
                    seekArq(j);
                    reg.gravaNoArq(arquivo);
                    j=j-dist;
                    if(j-dist>=0){
                        seekArq(j-dist);
                        reg.leDoArq(arquivo);
                    }
                }
                seekArq(j);
                aux.gravaNoArq(arquivo);
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
        }
        seekArq(0);
        saida.seekArq(0);
        while(!eof()){
            reg.leDoArq(saida.arquivo);
            reg.gravaNoArq(arquivo);
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
                while (i < j && reg1.getCodigo() <= reg2.getCodigo()) {
                    i++;
                    seekArq(i);
                    reg1.leDoArq(arquivo);
                    seekArq(j);
                    reg2.leDoArq(arquivo);
                }
            }
            else
                while (i < j && reg1.getCodigo() <= reg2.getCodigo()) {
                    j--;
                    seekArq(i);
                    reg1.leDoArq(arquivo);
                    seekArq(j);
                    reg2.leDoArq(arquivo);
                }
            if(i<j) {
                seekArq(i);
                reg1.leDoArq(arquivo);
                seekArq(j);
                reg2.leDoArq(arquivo);
                seekArq(i);
                reg2.gravaNoArq(arquivo);
                seekArq(j);
                reg1.gravaNoArq(arquivo);
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
            while(reg1.getCodigo()<pivo) {
                i++;
                seekArq(i);
                reg1.leDoArq(arquivo);
            }
            while(reg2.getCodigo()>pivo) {
                j--;
                seekArq(j);
                reg2.leDoArq(arquivo);
            }
            if(i<=j){
                seekArq(i);
                reg1.leDoArq(arquivo);
                seekArq(j);
                reg2.leDoArq(arquivo);
                seekArq(i);
                reg2.gravaNoArq(arquivo);
                seekArq(j);
                reg1.gravaNoArq(arquivo);
                i++;
                j--;
            }
        }
        if(ini<i)
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
            cont[index]--;
        }
        seekArq(0);
        saida.seekArq(0);
        while(!eof()){
            reg.leDoArq(saida.arquivo);
            reg.gravaNoArq(arquivo);
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
                if(reg1.getCodigo()>reg2.getCodigo()){
                    seekArq(i);
                    reg2.gravaNoArq(arquivo);
                    seekArq(i+gap);
                    reg1.gravaNoArq(arquivo);
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
            if(reg1.getCodigo()>=reg2.getCodigo())
                i++;
            else{
                seekArq(i-1);
                reg1.gravaNoArq(arquivo);
                reg2.gravaNoArq(arquivo);
                i--;
            }
        }
    }
}