package game_stuff;

public class Doctor implements Specialist{

    private final int medicalKnowledge;

    public Doctor(int medicalKnowledge) {
        this.medicalKnowledge = medicalKnowledge;
    }

    public int getMedicalKnowledge() {
        return medicalKnowledge;
    }
}
