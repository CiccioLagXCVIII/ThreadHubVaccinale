import java.util.Comparator;
public class ComparatorTamponi implements Comparator<Cliente>{
    public int compare(Cliente c1, Cliente c2){
        if(c1.tipoTampone < c2.tipoTampone){
            return 1;
        } else if(c1.tipoTampone > c2.tipoTampone){
            return -1;
        }
        return 0;
    }
}