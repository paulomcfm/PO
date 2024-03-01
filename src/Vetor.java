import java.util.Random;

public class Vetor {
    private int[] vet;
    private int TL;

    public Vetor(int TL){
        vet = new int[TL];
        this.TL = TL;
    }

    public void preenche() {
        Random random = new Random();
        for (int i = 0; i < TL; i++) {
            vet[i] = random.nextInt();
        }
    }

    public void selecaoDireta(){
        int posmenor,aux;
        for(int i=0; i<TL-1;i++){
            posmenor=i;
            for(int j=i+1;j<TL;j++)
                if(vet[j]<vet[posmenor])
                    posmenor=j;
            aux=vet[posmenor];
            vet[posmenor]=vet[i];
            vet[i]=aux;
        }
    }

    public void shake(){
        int aux, inicio=0, fim=TL-1;
        boolean troca=true;
        while(inicio < fim && troca){
            troca=false;
            for(int i=inicio;i<fim;i++){
                if(vet[i]>vet[i+1]){
                    troca=true;
                    aux=vet[i];
                    vet[i]=vet[i+1];
                    vet[i+1]=aux;
                }
            }
            fim--;
            if(troca){
                for(int i=fim;i>inicio;i--){
                    if(vet[i]<vet[i-1]){
                        troca=true;
                        aux=vet[i];
                        vet[i]=vet[i-1];
                        vet[i-1]=aux;
                    }
                }
                inicio++;
            }
        }
    }
}
