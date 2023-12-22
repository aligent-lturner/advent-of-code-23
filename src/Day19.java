import Util.Parts.Part;
import Util.Parts.Rule;
import Util.Parts.Workflow;
import Util.ReadFileAsArray;

import java.util.*;

public class Day19 {

    public static final String ACCEPTED = "A";
    public static final String REJECTED = "R";

    public static void main(String[] args) {
        List<String> lines = ReadFileAsArray.execute("./input/day-19.txt");
        Map<String, Workflow> workflowMap = new HashMap<>();
        Set<Part> parts = new HashSet<>();

        int separatorLine = lines.indexOf(lines.stream().filter(String::isBlank).findFirst().orElse(""));
        List<String> workflowLines = lines.subList(0, separatorLine);
        for (String line: workflowLines) {
            addWorkflow(workflowMap, line);
        }
        List<String> partLines = lines.subList(separatorLine + 1, lines.size());
        for (String line: partLines) {
            addPart(parts, line);
        }

        Set<Part> acceptedParts = getAcceptedParts(workflowMap, parts);
        int total = acceptedParts.stream().mapToInt(Part::getRating).sum();
        System.out.println("Part 1: Total rating = " + total);
    }

    private static Set<Part> getAcceptedParts(Map<String, Workflow> workflowMap, Set<Part> parts) {
        Set<Part> acceptedParts = new HashSet<>();
        Workflow startingWorkflow = workflowMap.get("in");
        for (Part part : parts) {
            String outcome = startingWorkflow.runWorkflow(part);
            while (!outcome.equals(ACCEPTED) && !outcome.equals(REJECTED)) {
                Workflow workflow = workflowMap.get(outcome);
                outcome = workflow.runWorkflow(part);
            }
            if (outcome.equals(ACCEPTED)) {
                acceptedParts.add(part);
            }
        }
        return acceptedParts;
    }

    private static void addWorkflow(Map<String, Workflow> workflowMap, String workflowLine) {
        String name = workflowLine.substring(0, workflowLine.indexOf('{'));
        String[] rulesArray = workflowLine.substring(workflowLine.indexOf('{') + 1, workflowLine.length() - 1)
                .split(",");
        List<Rule> ruleList = new ArrayList<>();
        // all but last rule follow the same format
        for (int i = 0; i < rulesArray.length - 1; i++) {
            String[] ruleValues = rulesArray[i].split(":");
            String test = ruleValues[0];
            String defaultOutcome = ruleValues[1];
            ruleList.add(new Rule(
                    test.charAt(0),
                    test.charAt(1),
                    Integer.parseInt(test.substring(2)),
                    defaultOutcome
            ));
        }
        String defaultWorkflowOutcome = rulesArray[rulesArray.length - 1];
        workflowMap.put(name, new Workflow(ruleList, defaultWorkflowOutcome));
    }

    private static void addPart(Set<Part> parts, String partLine) {
        partLine = partLine.replaceAll("[{}]", "");
        String[] values = partLine.split(",");
        Part part = new Part();
        for (String value: values) {
            char ch = value.charAt(0);
            int intValue = Integer.parseInt(value.substring(2));
            part.setValueForChar(ch, intValue);
        }
        parts.add(part);
    }
}
