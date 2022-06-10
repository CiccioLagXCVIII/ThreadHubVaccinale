public class Personale extends Thread{
    private HubVaccinale hubVaccinale;
    public int idPersonale;
    public int tipoManzione;
    //tipoRichiesta Rules:
    //0     Tampone
    //1     Vaccino

    public Personale(HubVaccinale hub, int id, int tipo){
        hubVaccinale = hub;
        idPersonale = id;
        tipoManzione = tipo;
    }

    public String manzionePersonale(){
        String manzione = "";
        if(tipoManzione == 0){
            manzione = "Tampone";
        }else if(tipoManzione == 1){
            manzione = "Vaccino";
        }
        return manzione;
    }

    public void run(){
        try{
            if(tipoManzione == 0){
                hubVaccinale.eseguiTampone(this);
            }else if(tipoManzione == 1){
                hubVaccinale.eseguiVaccino(this);
            }
        } catch (Exception e){
            //TODO
        }
    }
}