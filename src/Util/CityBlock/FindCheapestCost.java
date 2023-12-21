package Util.CityBlock;

import Util.Coordinates;
import Util.Direction;

import java.util.*;


public class FindCheapestCost {

    private static TreeMap<Integer, Set<State>> statesByCost;
    private static Map<String, Integer> seenCostsByState;
    private static Integer finalCost;

    public static int execute(Grid grid, Coordinates start, Coordinates end) {
        statesByCost = new TreeMap<>();
        seenCostsByState = new HashMap<>();
        finalCost = null;

        // initial movements
        moveAndAddState(grid, end, 0, start, Direction.DOWN, 1);
        moveAndAddState(grid, end, 0, start, Direction.RIGHT, 1);

        while (finalCost == null) {
            // get the lowest current cost
            int currentCost = statesByCost.firstKey();

            // get all states with that cost
            Set<State> nextStates = statesByCost.get(currentCost);
            // remove the element from the map, so it won't be processed again
            statesByCost.remove(currentCost);

            // process each state
            for (State state : nextStates) {
                // new directions (turning left/right)
                Set<Direction> newDirections = state.getDirection().getPerpendicularDirections();
                for (Direction direction : newDirections) {
                    moveAndAddState(grid, end, currentCost, state.getCoordinates(), direction, 1);
                }
                // continue straight if possible
                if (state.getDistance() < 3) {
                    moveAndAddState(
                            grid,
                            end,
                            currentCost,
                            state.getCoordinates(),
                            state.getDirection(),
                            state.getDistance() + 1
                    );
                }
            }
        }
        return finalCost;
    }

    private static void moveAndAddState(
            Grid grid,
            Coordinates end,
            int cost,
            Coordinates coordinates,
            Direction direction,
            int distance
    ) {
        // update the direction
        coordinates = coordinates.move(direction, 1);
        // do bounds checking
        if (!grid.inBounds(coordinates)) {
            return;
        }
        // calculate the code of stepping on the square
        int newCost = cost + grid.getCost(coordinates);

        // are we at the end?
        if (coordinates.equals(end)) {
            finalCost = newCost;
            return;
        }

        // create the state
        State state = new State(coordinates, direction, distance);

        // have we seen the state before
        if (!seenCostsByState.containsKey(state.getKey())) {
            Set<State> stateQueue = statesByCost.getOrDefault(newCost, new HashSet<>());
            stateQueue.add(state);
            statesByCost.put(newCost, stateQueue);
            seenCostsByState.put(state.getKey(), newCost);
        }
    }

}
