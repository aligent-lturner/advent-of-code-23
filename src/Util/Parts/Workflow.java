package Util.Parts;

import java.util.List;

public class Workflow {

    private final List<Rule> ruleList;
    private final String defaultOutcome;

    public Workflow(List<Rule> ruleList, String defaultOutcome) {
        this.ruleList = ruleList;
        this.defaultOutcome = defaultOutcome;
    }

    public String runWorkflow(Part part) {
        for (Rule rule : ruleList) {
            String outcome = rule.testPart(part);
            if (outcome != null) {
                return outcome;
            }
        }
        return defaultOutcome;
    }
}
