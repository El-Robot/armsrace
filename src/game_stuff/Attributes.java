package game_stuff;

import java.util.ArrayList;

public class Attributes {

    // These hold values that determine what actions players can take

    // values representing things you have
    private int population;
    private int foodStores;

    // values that show how advanced you civilization is
    private int generalScience;
    private int generalEducation;
    private int generalAgriculture;
    private int generalMilitary;
    private int generalNationalism;
    private int generalMedicine;

    // values that show rates
    private double birthRate;
    private double consumptionRate;
    private double sicknessRate;


    // holds experts, politicians, spies, etc.
    private ListOfSpecialists specialists;

    public Attributes() {

        population = 1000;
        foodStores = 1200;

        generalAgriculture = 0;
        generalEducation = 0;
        generalNationalism = 0;
        generalMilitary = 0;
        generalScience = 0;
        generalMedicine = 0;

        birthRate = 0.1;
        consumptionRate = 1;
        sicknessRate = 0;

        specialists = new ListOfSpecialists();

    }

    /**
     * Get a list of buttons that have all the actions a player can use.
     * @return ArrayList of the possible actions a player can take based on the
     * Attributes they have unlocked will be sent as buttons with the
     * event in them.
     */
    public ArrayList<Action> availableActions() {

        ArrayList<Action> actions = new ArrayList<>();

        // we start with the actions that the playe starts with

        // basic improve agriculture action
        actions.add(new Action("Focus on Agriculture", "Slightly increases agriculture score",
                event -> {

                    generalAgriculture++;

                }));

        // basic improve science action
        actions.add(new Action("Focus on Science", "Slightly increases Science score",
                event -> {

                    generalScience++;

                }));

        // basic improve medicine action
        actions.add(new Action("Focus on Medicine", "Slightly increases Medicine score",
                event -> {

                    generalMedicine++;

                }));

        // basic improve nationalism action
        actions.add(new Action("Focus on Nationalism", "Slightly increases nationalism score",
                event -> {

                    generalNationalism++;

                }));


        // basic improve education action
        actions.add(new Action("Focus on Education", "Slightly increases education score",
                event -> {

                    generalEducation++;

                }));

        // basic improve military action
        actions.add(new Action("Focus on Military", "Slightly increases military score",
                event -> {

                    generalMilitary++;

                }));

        return actions;

    }

    public void endOfTurn() {
        produceFood();
        sicknessDeaths();
        consumeFood();
        births();
    }

    public void produceFood() {
        foodStores += generalAgriculture * 1000;
    }

    public void sicknessDeaths() {
        int succumbedPeople = (int) (-sicknessRate * population) +
                generalMedicine * specialists.doctorCount();
        population += Math.max(succumbedPeople, 0);
    }

    public void consumeFood() {
        foodStores -= population * consumptionRate;
        if (foodStores < 0) {
            population += foodStores / consumptionRate;
            foodStores = 0;
        }
    }

    public void births() {
        population += (int) (birthRate * population);
    }
}