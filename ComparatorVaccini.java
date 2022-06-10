import java.util.Comparator;
public class ComparatorVaccini implements Comparator<Cliente>{
    public int compare(Cliente c1, Cliente c2) {
        if(c1.tipoPrenotazione < c2.tipoPrenotazione){
            return 1;
        } else if(c1.tipoPrenotazione > c2.tipoPrenotazione){
            return -1;
        }
        return 0;
    }
}