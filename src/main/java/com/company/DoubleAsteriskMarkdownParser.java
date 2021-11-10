package com.company;

public class DoubleAsteriskMarkdownParser {
    private StringBuilder htmlBuffer = new StringBuilder();
    private boolean isFirstPairOfAsterisksFound = false;
    private boolean isFirstAsteriskFound = false;
    int indexForOpeningDelimiter = -1;
    int indexOfLastAppearingWhitespace = -1;

    public void acceptCharacter(char character) {
        if (character == '*') {
            if (!haveFoundFirstAsterisk()) {
                isFirstAsteriskFound = true;
                push(character);
            } else if (!haveFoundFirstAsteriskPair()) {
                isFirstAsteriskFound = false;
                isFirstPairOfAsterisksFound = true;
                indexForOpeningDelimiter = getCurrentCharacterIndex() - 1;
                push(character);
            } else if (!isSecondPairPrecededBySpace()) {
                replaceAtBufferWith(indexForOpeningDelimiter, 2, "<strong>");
                replaceAtBufferWith(getCurrentCharacterIndex() - 1, 1, "</strong>");
                isFirstAsteriskFound = false;
                isFirstPairOfAsterisksFound = false;
            }
        } else {
            if (character == ' ') {
                indexOfLastAppearingWhitespace = getCurrentCharacterIndex();

                if (isFirstAsteriskPairFollowedBySpace()) {
                    isFirstPairOfAsterisksFound = false;
                    indexForOpeningDelimiter = -1;
                }
            }
            isFirstAsteriskFound = false;
            push(character);
        }
    }

    private void replaceAtBufferWith(int start, int length, String s) {
        htmlBuffer.delete(start, start + length);
        htmlBuffer.insert(start, s);
    }

    private boolean isSecondPairPrecededBySpace() {
        return getCurrentCharacterIndex() - indexOfLastAppearingWhitespace == 2;
    }

    private boolean haveFoundFirstAsteriskPair() {
        return isFirstPairOfAsterisksFound;
    }

    public String returnParsedLine() {
        return htmlBuffer.toString();
    }

    private boolean haveFoundFirstAsterisk() {
        return isFirstAsteriskFound;
    }

    private int getCurrentCharacterIndex() {
        return htmlBuffer.length();
    }

    private void push(char character) {
        htmlBuffer.append(character);
    }

    private boolean isFirstAsteriskPairFollowedBySpace() {
        return indexForOpeningDelimiter >= 0 && indexOfLastAppearingWhitespace - indexForOpeningDelimiter == 2;
    }

    private void clear() {
        htmlBuffer.setLength(0);
        isFirstPairOfAsterisksFound = false;
        isFirstAsteriskFound = false;
        indexForOpeningDelimiter = -1;
        indexOfLastAppearingWhitespace = -1;
    }

    public static String parseMarkdownLine(String[] rawTextLines) {
        StringBuilder paragraphs = new StringBuilder();
        DoubleAsteriskMarkdownParser parser = new DoubleAsteriskMarkdownParser();

        for (String line : rawTextLines) {
            for (int i = 0; i < line.length(); ++i) {
                parser.acceptCharacter(line.charAt(i));
            }

            paragraphs.append(parser.returnParsedLine());
            parser.clear();
        }

        return paragraphs.toString();
    }
}
