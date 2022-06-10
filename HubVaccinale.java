import java.util.PriorityQueue;

public class HubVaccinale{
    ComparatorTamponi comparatorTamponi = new ComparatorTamponi();
    private PriorityQueue<Cliente> codaTamponi = new PriorityQueue<Cliente>(comparatorTamponi);
    ComparatorVaccini comparatorVaccini = new ComparatorVaccini();
    private PriorityQueue<Cliente> codaVaccini = new PriorityQueue<Cliente>(comparatorVaccini);
    private Cliente ultimoTampone;
    private Cliente ultimoVaccino;
    private int countVaccini;

    public synchronized void mettiInCoda(Cliente cliente){
        if(cliente.tipoRichiesta == 0){
            codaTamponi.add(cliente);
        } else if(cliente.tipoRichiesta == 1){
            codaVaccini.add(cliente);
        }
    }

    public synchronized void eseguiTampone(Personale personale){
        if(personale.tipoManzione == 0){
            while(codaTamponi.size() == 0){
                System.out.println("Non Ci Sono Persone Che Vogliono Fare Un Tampone!");
                try{
                    wait();
                } catch (Exception e) {
                    //TODO
                }
            }
            while(codaTamponi.size() != 0){
                System.out.println("La Persona " + codaTamponi.peek().idCliente + " Può Fare Il Tampone Dall'Addetto " + personale.idPersonale);
                ultimoTampone = codaTamponi.poll();
                
                try {
                    Thread.sleep((int)Math.random() * 1000);
                } catch (Exception e) {
                    //TODO: handle exception
                }
                ultimoTampone.esitoTamponeRapido = ((int)(Math.random()*2000))%2;
                if(ultimoTampone.esitoTamponeRapido == 0){
                    System.out.println("La Persona " + ultimoTampone.idCliente + " E' Negativa Al Tampone Rapido, Quindi Se Ne Va!");
                } else if(ultimoTampone.esitoTamponeRapido == 1){
                    if(ultimoTampone.tipoTampone == 1){
                        System.out.println("La Persona " +ultimoTampone.idCliente + "E' Positivo Al Tampone Molecolare. QUARANTENA ISAAA!");
                    } else if(ultimoTampone.tipoTampone == 0){
                        System.out.println("La Persona " + ultimoTampone.idCliente + " E' Positiva Al Tampone Rapido, Quindi Torna In Coda Per Il Tampone Molecolare!");
                        ultimoTampone.tipoTampone = 1;
                        codaTamponi.add(ultimoTampone);
                    }
                }
            }
        }
        notifyAll();
    }
   
    public synchronized void eseguiVaccino(Personale personale){
        //Effettua Controlli Ed Esegui Vaccino (Ogni 10 Prenotati 2 Non Prenotati)
        if(personale.tipoManzione == 1){
            while(codaVaccini.size() == 0){
                System.out.println("Non Ci Sono Persone Che Vogliono Fare Il Vaccino!");
                try{
                    wait();
                } catch (Exception e) {
                    //TODO
                }
            }
            while(codaVaccini.size() != 0){
                System.out.println("La Persona " + codaVaccini.peek().idCliente + " Può Fare Il Vaccino Dall'Addetto " + personale.idPersonale);
                ultimoVaccino = codaVaccini.poll();
                
                try {
                    Thread.sleep((int)Math.random() * 2000);
                } catch (Exception e) {
                    //TODO: handle exception
                }
                countVaccini += 1;
                System.out.println("La Persona " + ultimoVaccino.idCliente + " Ha Fatto Il Vaccino, Quindi Esce! ");

            }
        }
        notifyAll();
    }
}


/*  */