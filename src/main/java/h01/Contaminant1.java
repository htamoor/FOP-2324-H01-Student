package h01;

import fopbot.Direction;
import fopbot.Robot;
import fopbot.RobotFamily;
import h01.template.Contaminant;
import h01.template.GameConstants;
import h01.template.TickBased;
import h01.template.Utils;
import org.tudalgo.algoutils.student.Student;

import java.util.ArrayList;

/**
 * A {@link Contaminant}-{@link Robot} that moves randomly and contaminates the floor.
 */
public class Contaminant1 extends Robot implements Contaminant, TickBased {

    /**
     * Creates a new {@link Contaminant1}.
     *
     * @param x             the initial x coordinate of the robot
     * @param y             the initial y coordinate of the robot
     * @param direction     the initial direction of the robot
     * @param numberOfCoins the initial number of coins of the robot
     */
    public Contaminant1(final int x, final int y, final Direction direction, final int numberOfCoins) {
        super(x, y, direction, numberOfCoins, RobotFamily.SQUARE_ORANGE);
    }

    @Override
    public int getUpdateDelay() {
        return 10;
    }

    @Override
    public void doMove() {
        // 1.
        if (!hasAnyCoins()) {
            turnOff();
            return;
        }

        // 2.
        if (!hasAnyCoins() || isTurnedOff()) {
            return;
        }

        // 3.
        int coinsToPut = Utils.getRandomInteger(GameConstants.CONTAMINANT_ONE_MIN_PUT_COINS, GameConstants.CONTAMINANT_ONE_MAX_PUT_COINS);
        boolean canPutCoins = Utils.getCoinAmount(getX(), getY()) < 20;
        if(!isOnACoin() && canPutCoins) {
            for (int i = 0; i < coinsToPut; i++) {
                if (!hasAnyCoins() || Utils.getCoinAmount(getX(), getY()) >= 20) {
                    break;
                }
                putCoin();
            }
        }

        // 4.
        ArrayList<Direction> possibleDireCtions = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            if (isFrontClear()) {
                possibleDireCtions.add(getDirection());
            }
            turnLeft();
        }

        if (!possibleDireCtions.isEmpty()) {
            int value = Utils.getRandomInteger(0, possibleDireCtions.size() - 1);
            Direction moveToDirection = possibleDireCtions.get(value);
            while (getDirection() != moveToDirection) {
                turnLeft();
            }
        }

        // 5.
        if (isFrontClear()) {
            move();
        }

    }
}
