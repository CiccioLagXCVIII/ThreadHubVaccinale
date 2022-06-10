public class Cliente extends Thread{
    private HubVaccinale hubVaccinale;
    public int idCliente;
    public int tipoRichiesta;
    //tipoRichiesta Rules:
    //0     Tampone
    //1     Vaccino
    public int tipoTampone;
    //tipoTampone Rules:
    //0     Tampone Rapido
    //1     Tampone Molecolare
    public int tipoPrenotazione;
    //tipoPrenotazione Rules:
    //0     Senza Prenotazione
    //1     Prenotato
    public int esitoTamponeRapido;
    //esitoTamponeRapido Rules:
    //0     Negativo
    //1     Positivo

    public Cliente(HubVaccinale hub, int id, int tipo){
        idCliente = id;
        tipoRichiesta = tipo;
    }

    public void setTipo(){
        String prenotazione = "";
        String tampone = "";
        if(tipoRichiesta == 0){
            tipoTampone = (int)(Math.random()*300)%2;
            if(tipoTampone == 0){
                tampone = "Rapido";
            } else if(tipoTampone == 1){
                tampone = "Molecolare";
            }
            System.out.println("Il Cliente " + idCliente + " Vuole Fare Un Tampone " +tampone);
        } else if(tipoRichiesta == 1){
            tipoPrenotazione = (int)(Math.random()*300)%2;
            if(tipoPrenotazione == 0){
                prenotazione = "Senza Prenotazione";
            } else if(tipoPrenotazione == 1){
                prenotazione = "Con Prenotazione";
            }
            System.out.println("Il Cliente " + idCliente + " Vuole Fare Il Vaccino " +prenotazione);
        }
    }

    public void run(){
        this.setTipo();
        try{
            hubVaccinale.mettiInCoda(this);
        } catch (Exception e){
            //TODO
        }
    }
}