package Arquivo;

import java.io.IOException;
import java.io.RandomAccessFile;

//... classe Arquivo (onde vai estar o m�todo para ordernar, etc) ....
public class Arquivo_Java {
    private String nomearquivo;
    private RandomAccessFile arquivo;

    public Arquivo_Java(String nomearquivo) {
        try {
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