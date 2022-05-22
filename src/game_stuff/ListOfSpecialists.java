package game_stuff;

import java.util.ArrayList;

public class ListOfSpecialists extends ArrayList<Specialist> {

    public int doctorCount() {
        int count = 0;
        for (Specialist s : this) {
            count += s instanceof Doctor ? 1 : 0;
        }
        return count;
    }

}
