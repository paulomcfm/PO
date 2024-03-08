package Arquivo;

import Lista.No;

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
        int i;
        Registro aux = new Registro();
        seekArq(0);
        i = 0;
        while (!this.eof()) {
            System.out.println("Posicao " + i);
            aux.leDoArq(arquivo);
            aux.exibirReg();
            i++;
        }
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
    public void preenche(int qtd) {
        Random random = new Random();
        Registro reg = new Registro();
        for(int i = 0; i < qtd; i++){
            int codigo = random.nextInt(2000) + 1; // Gera um número aleatório entre 1 e 2000
            String nome = "Nome" + codigo; // Exemplo simples de nome, você pode modificar conforme necessário
            int idade = random.nextInt(101); // Gera um número aleatório entre 0 e 100 para idade
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
            pos=buscaBinaria(aux.getCodigo(), filesize());
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

    public void bolha(){
        Registro reg1 = new Registro();
        Registro reg2 = new Registro();
        int tam = filesize();
        boolean troca = true;

        while(tam>1 && troca){
            troca=false;
            for(int i=0;i<tam-1;i++){
                seekArq(i);
                reg1.leDoArq(arquivo);
                reg2.leDoArq(arquivo);
                if(reg1.getCodigo()>reg2.getCodigo()){
                    seekArq(i);
                    reg1.gravaNoArq(arquivo);
                    reg2.gravaNoArq(arquivo);
                    troca=true;
                }
                tam--;
            }
        }
    }


    public void selecaoDireta(){ //posiciona no inicio, vai do segundo ao final procurando um menor, se achar, troca
        int posmenor;
        int tam = filesize();
        for(int i=0;i<tam-1;i++){
            
        }
    }


}