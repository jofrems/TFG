package com.tfg.game.testPost;

import com.tfg.game.testPost.reader.PostLine;
import com.tfg.game.testPost.reader.PostSection;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PostRunner {
    private final List<PostLineStep> postLineSteps;

    public PostRunner(List<PostLineStep> postLineSteps) {
        this.postLineSteps = postLineSteps;
    }

    public void runSection(PostSection section) {
        var lines = section.getLines();
        runLines(lines);
    }

    private void runLines(List<PostLine> lines) {
        for (var line: lines) {
            runLine(line);
        }
    }

    private void runLine(PostLine line) {
        print(line);
        if (!isWellFormattedStep(line))
            throw newErrorShouldEndWithTwoSpaces(line);

        var runners = matchLine(line);
        if (runners.size() > 1)
            throw newErrorTooManyMatches(runners, line);
        if (runners.size() < 1)
            throw newErrorNoRunnersFound(line);

        var runner = runners.get(0);
        verifyRunnerNaming(runner, line);
        runner.run(line);
    }

    private void verifyRunnerNaming(PostLineStep runner, PostLine line) {
        if (runner instanceof SystemPostLineStep) return;

        var className = runner.getClass().getSimpleName();
        if (className.contains("_") || className.contains("$") || className.matches(".*\\d.*"))
            throw new AssertionError("Matcher found for the current line should follow the naming convention.\n" +
                    " - postLineStep: " + runner.getClass().getName() + "\n" +
                    "Make sure that the class name:\n" +
                    " - does not have symbols like _ or $\n" +
                    " - does not have digits (numbers)");

        var lowerFirstClassName = className.substring(0, 1).toLowerCase() + className.substring(1).replaceFirst("Step$", "");
        var regExp = lowerFirstClassName.replaceAll("([A-Z])", ".*[^a-z]$1").toLowerCase();
        var content = line.getContent().toLowerCase();
        if (content.matches(".*" + regExp + ".*")) {
            if (!className.endsWith("Step"))
                throw new AssertionError("Matcher found for the current line should follow the naming convention.\n" +
                        " - postLineStep: " + runner.getClass().getName() + "\n" +
                        "Its class name should end in \"Step\" but it does not.\n" +
                        " - rename \""+ runner.getClass().getSimpleName() + "\" to \""+ runner.getClass().getSimpleName() + "Step\"\n");
            return;
        }

        throw new AssertionError("Matcher found for the current line should follow the naming convention for PostLineSteps:\n" +
                " - line:         " + line.getPrettyPrint() + "\n" +
                " - postLineStep: " + runner.getClass().getName() + "\n" +
                "It must have as class name the same words from the step, removing matches, and capitalizing instead of spaces."
                );
    }

    private void print(PostLine line) {
        System.out.println(line.getPrettyPrint());
    }

    private List<PostLineStep> matchLine(PostLine line) {
        return postLineSteps.stream()
                .filter(r -> r.match(line))
                .collect(Collectors.toList());
    }

    private boolean isWellFormattedStep(PostLine line) {
        return line.match("^\\s(\\s*>)") == null || line.match("\\s\\s$") != null;
    }

    private AssertionError newErrorShouldEndWithTwoSpaces(PostLine line) {
        var prettyLine = line.getPrettyPrint().replace(line.getContent(), "\"" + line.getContent() + "\"");
        var missingText = "— add two spaces ^";
        var preMissingText = prettyLine.substring(0, Math.max(0, 3 + prettyLine.length() - missingText.length())).replaceAll(".", " ");

        return new AssertionError("Post lines that begin with one space \" \" should end at least with two spaces \"  \":\n" +
                "=> " + prettyLine + "\n" +
                preMissingText + missingText
        );
    }

    private AssertionError newErrorTooManyMatches(List<PostLineStep> runners, PostLine line) {
        var message = new StringBuilder();

        message.append("There should be only one matching PostLineStep, but there are too many PostLineSteps (")
            .append(runners.size())
            .append(") matches the instruction:\n");

        message.append("=> ")
                .append(line.getPrettyPrint())
                .append("\n");

        message.append("The " + runners.size() + " matchers that conflict for the same line are:\n");
        for (var runner: runners)
            message.append("  - ")
                    .append(runner.getPrettyPrint())
                    .append('\n');

        if (!line.startsWith(" "))
            message.append("This line in the post do not start with a space, you may forgot to add one space in the beginning.\n")
                    .append(" => Please check if this post line needs an space in the beginning and add it if it is necessary.\n");

        return new AssertionError(message.toString());
    }

    private AssertionError newErrorNoRunnersFound(PostLine line) {
        return new AssertionError("There should be a matching PostLineStep, but there are no PostLineSteps (0) matching the instruction:\n=> " + line.getPrettyPrint());
    }


}
