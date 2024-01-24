package h01;

import fopbot.Robot;
import fopbot.World;
import h01.template.GameControllerBase;
import h01.template.Utils;
import org.tudalgo.algoutils.student.Student;

/**
 * A {@link GameController} controls the game loop and the {@link Robot}s and checks the win condition.
 */
public class GameController extends GameControllerBase {

    /**
     * Creates a new {@link GameControllerBase}.
     */
    public GameController() {
        setup();
    }

    @Override
    public void checkWinCondition() {
        int loadZone = Utils.getCoinAmount(0, World.getHeight() - 1);
        int coinsOnField = 0;
        for (int x = 0; x < World.getWidth(); x++) {
            for (int y = 0; y < World.getHeight(); y++) {
                if (Utils.getCoinAmount(x, y) > 0) {
                    coinsOnField++;
                }
            }
        }

        int totalField = World.getHeight() * World.getWidth();
        boolean contaminantsWon = (coinsOnField >= totalField * 0.5);
        boolean cleanerWon = (getContaminant1().isTurnedOff() && getContaminant2().isTurnedOff() || loadZone >= 200);

        if (cleanerWon) {
            getContaminant1().turnOff();
            getContaminant2().turnOff();
            System.out.println("Cleaning robot won!");
            stopGame();
        }

        if (contaminantsWon) {
            getCleaningRobot().turnOff();
            System.out.println("Contaminants won!");
            stopGame();
        }

    }
}
