package game_stuff;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class Action extends Button {

    public Action(String name, String description, EventHandler<Event> event) {

        super();
        setOnMouseClicked(event);

    }

}
