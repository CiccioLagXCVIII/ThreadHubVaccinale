public class Main{
    public static final int numeroAddettiTamponi = 4;
    public static final int numeroAddettiVaccini = 3;
    public static final int numeroClienti = 10;
    public static void main(String args[]){
        //Istanziare in partenza N Thread Personale con tipoManzione = 0 ed M hread Personale con tipoManzione = 1
        HubVaccinale hubVaccinale = new HubVaccinale();

        Personale[] personaleTamponi = new Personale[numeroAddettiTamponi];
        Personale[] personaleVaccini = new Personale[numeroAddettiVaccini];
        Cliente[] clienti = new Cliente[numeroClienti];
        

        for(int i=0; i < personaleTamponi.length; i++){
            personaleTamponi[i] = new Personale(hubVaccinale, i, 0);
            personaleTamponi[i].start();
            //System.out.println("Personale " + i + " (" + personaleTamponi[i].manzionePersonale() + ")");
        }

        for(int j=0; j < personaleVaccini.length; j++){
            personaleVaccini[j] = new Personale(hubVaccinale, j+numeroAddettiTamponi, 1);
            personaleVaccini[j].start();
            //System.out.println("Personale " + personaleVaccini[j].idPersonale + " (" + personaleVaccini[j].manzionePersonale() + ")");
        }

        try{
            Thread.sleep(3000);
        } catch (Exception e){
            //TODO
        }

        for(int x=0; x<numeroClienti; x++){
            int t = (int)((Math.random()*90) % 2);
            clienti[x] = new Cliente(hubVaccinale, x, t);
            clienti[x].start();
            //System.out.println("Cliente" + x + " (" + clienti[x].tipoRichiesta + ")");
        }
    }
}