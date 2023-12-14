import Util.GetPrimeFactors;
import Util.MapNode;
import Util.ReadFileAsArray;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

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
        Set<MapNode> startingNodes = new HashSet<>();
        startingNodes.add(nodeMap.get("AAA"));

        long numSteps = getLowestCommonMultiple(steps, startingNodes, true);
        System.out.println("Part 1: Number of steps = " + numSteps);
        startingNodes = new HashSet<>(nodeMap.values().stream().filter(MapNode::isStartingNode).toList());

        numSteps = getLowestCommonMultiple(steps, startingNodes, false);
        System.out.println("Part 2: Number of steps = " + numSteps);
    }

    private static long getLowestCommonMultiple(char[] instructions, Set<MapNode> startingNodes, boolean strict) {
        List<List<Long>> primeFactors = startingNodes.stream().
                map(node -> countStepsToDestination(instructions, node, strict)).
                map(GetPrimeFactors::execute).toList();

        Set<Long> distinctFactors = new TreeSet<>();
        primeFactors.forEach(distinctFactors::addAll);

        long lcm = 1;
        for (long factor: distinctFactors) {
            AtomicLong maxCount = new AtomicLong();
            primeFactors.forEach(factors ->  {
                long count = factors.stream().filter(f -> f == factor).count();
                if (count > maxCount.get()) {
                    maxCount.set(count);
                }
            });
            if (maxCount.get() > 0) {
                for (long j = 1; j <= maxCount.get(); j++) {
                    lcm = lcm * factor;
                }
            }
        }

        return lcm;
    }

    private static long countStepsToDestination(char[] instructions, MapNode startingNode, boolean strict) {
        long steps = 0;
        MapNode current = startingNode;
        while (!current.isEndingNode(strict)) {
            for (char instruction : instructions) {
                steps++;
                //Set<MapNode> newCurrentNodes = new HashSet<>();
                if (instruction == 'L') {
                    current = current.getLeft();
                } else if (instruction == 'R') {
                    current = current.getRight();
                }
                if (current.isEndingNode(strict)) {
                    break;
                }
            }
        }
        return steps;
    }
}
