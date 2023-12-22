import Util.Coordinates;
import Util.GetPolygonArea;
import Util.PipeNode;
import Util.ReadFileAsArray;

import java.util.ArrayList;
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
                PipeNode pipeNode = new PipeNode(x, y, chars[x - 1]);
                nodes.add(pipeNode);
            }
        }

        List<PipeNode> loopNodes = getLoopNodes(nodes);
        int steps = loopNodes.size();
        System.out.println("Part 1: Max distance = " + steps/2);

        long loopArea = GetPolygonArea.execute(loopNodes.stream().map(PipeNode::getPosition).toList());
        // pick's theorem is that A = i + b/2 - 1, where A = area, i = interior points, b = edge points
        // therefore, i = A - b/2 + 1
        long enclosedPoints = loopArea - (steps / 2) + 1;
        System.out.println("Part 2: enclosed node count = " + enclosedPoints);
    }

    private static PipeNode getStartNode(Set<PipeNode> nodes) {
        return nodes.stream().filter(PipeNode::isStart).findFirst().orElse(null);
    }

    private static Set<PipeNode> getNodesConnectedToStart(PipeNode start, Set<PipeNode> nodes) {
        return nodes.stream().
                filter(node -> node.getConnectedCoordinates().contains(start.getPosition())).
                collect(Collectors.toSet());
    }

    private static List<PipeNode> getLoopNodes(Set<PipeNode> nodes) {
        List<PipeNode> visitedNodes = new ArrayList<>();
        PipeNode start = getStartNode(nodes);
        System.out.println("Starting at" + start);
        Set<PipeNode> connectedNodes = getNodesConnectedToStart(start, nodes);
        PipeNode current = connectedNodes.stream().findFirst().orElse(null);
        while (current != null && !current.equals(start)) {
            System.out.println(current);
            visitedNodes.add(current);
            Coordinates nextCoords = current.getConnectedCoordinates().stream().
                    filter(coords -> !visitedNodes.contains(new PipeNode(coords.getX(), coords.getY(), '.'))).
                    findFirst().orElse(null);
            if (nextCoords == null) {
                System.out.println("Couldn't find next coordinates");
                return visitedNodes;
            }
            current = nodes.stream().filter(node -> node.getPosition().equals(nextCoords)).findFirst().orElse(null);
        }
        visitedNodes.add(start);
        return visitedNodes;
    }
}
