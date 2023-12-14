import Util.MapNode;
import Util.ReadFileAsArray;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Day8 {

    public static void main(String[] args) {
        ArrayList<String> lines = ReadFileAsArray.execute("./input/day-8.txt");
        Map<String, MapNode> nodeMap = new TreeMap<>();

        String instructions = lines.get(0);
        char[] steps = instructions.toCharArray();
        // remove the first two lines
        lines.remove(0);
        lines.remove(0);

        // populate map with nodes
        for (String line : lines) {
            String value = line.substring(0, 3);
            String left = line.substring(7, 10);
            String right = line.substring(12, 15);
            MapNode node = nodeMap.getOrDefault(value, new MapNode(value));
            nodeMap.put(value, node);
            MapNode leftNode = nodeMap.getOrDefault(left, new MapNode(left));
            nodeMap.put(left, leftNode);
            MapNode rightNode = nodeMap.getOrDefault(right, new MapNode(right));
            nodeMap.put(right, rightNode);

            node.setLeft(leftNode);
            node.setRight(rightNode);
        }
        MapNode startingNode = nodeMap.get("AAA");
        if (startingNode == null) {
            System.out.println("Could not find starting node");
            return;
        }

        long numSteps = countStepsToDestination(steps, startingNode, "ZZZ");
        System.out.println("Number of steps = " + numSteps);
    }

    private static long countStepsToDestination(char[] instructions, MapNode startingNode, String destination) {
        long steps = 0;
        MapNode current = startingNode;
        while (!current.getValue().equals(destination)) {
            for (char instruction : instructions) {
                System.out.println(current.getValue() + " -> " + instruction + ":");
                steps++;
                if (instruction == 'L') {
                    current = current.getLeft();
                } else if (instruction == 'R') {
                    current = current.getRight();
                }
                if (current.getValue().equals(destination)) {
                    break;
                }
            }
        }
        return steps;
    }
}
