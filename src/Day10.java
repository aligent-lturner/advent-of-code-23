import Util.Coordinates;
import Util.PipeNode;
import Util.ReadFileAsArray;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Day10 {

    public static void main(String[] args) {
        List<String> lines = ReadFileAsArray.execute("./input/day-10.txt");
        Set<PipeNode> nodes = new HashSet<>();

        // create nodes first
        for (int y = 1; y <= lines.size(); y++ ) {
            char[] chars = lines.get(y-1).toCharArray();
            for (int x = 1; x<= chars.length; x++) {
                if (chars[x-1] != '.') {
                    PipeNode pipeNode = new PipeNode(x, y, chars[x - 1]);
                    nodes.add(pipeNode);
                }
            }
        }
        int steps = getTotalStepsInLoop(nodes);
        System.out.println("Part 1: Max distance = " + steps/2);
    }

    private static PipeNode getStartNode(Set<PipeNode> nodes) {
        return nodes.stream().filter(PipeNode::isStart).findFirst().orElse(null);
    }

    private static Set<PipeNode> getNodesConnectedToStart(PipeNode start, Set<PipeNode> nodes) {
        return nodes.stream().
                filter(node -> node.getConnectedCoordinates().contains(start.getPosition())).
                collect(Collectors.toSet());
    }

    private static int getTotalStepsInLoop(Set<PipeNode> nodes) {
        Set<PipeNode> visitedNodes = new HashSet<>();
        PipeNode start = getStartNode(nodes);
        System.out.println("Starting at" + start);
        Set<PipeNode> connectedNodes = getNodesConnectedToStart(start, nodes);
        // start with one connection - doesn't matter which
        PipeNode current = connectedNodes.stream().findFirst().orElse(null);

        int count = 1;
        while (current != null && !current.equals(start)) {
            System.out.println(current);
            visitedNodes.add(current);
            count++;
            Coordinates nextCoords = current.getConnectedCoordinates().stream().
                    filter(coords -> !visitedNodes.contains(new PipeNode(coords.getX(), coords.getY(), '.'))).
                    findFirst().orElse(null);
            if (nextCoords == null) {
                System.out.println("Couldn't find next coordinates");
                return -1;
            }
            current = nodes.stream().filter(node -> node.getPosition().equals(nextCoords)).findFirst().orElse(null);
        }
        return count;
    }
}
