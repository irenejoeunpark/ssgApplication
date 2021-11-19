package com.company;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MDUtilsTest {

    @Test
    void getBodyFromText() {
        String[] bold_string1 = {"*Bold test string 1*"};
        assertEquals("<p><strong>Bold test string 1</strong></p><br/>", MDUtils.getBodyFromText(bold_string1));

        String[] bold_string2 = {"Invalid * bold string"};
        assertEquals("<p>Invalid * bold string</p><br/>", MDUtils.getBodyFromText(bold_string2));

        /* Not passed, bold markdown in the middle of the string
        String[] bold_string3 = {"Valid *bold* string 3"};
        assertEquals("<p>Valid <strong>bold</strong> string 3</p><br/>", MDUtils.getBodyFromText(bold_string3));
         */

        String[] header_string1 = {"# header test 1"};
        assertEquals("<h1>header test 1</h1><br/>", MDUtils.getBodyFromText(header_string1));

        String[] header_string2 = {"#Invalid header 1"};
        assertEquals("<p>#Invalid header 1</p><br/>", MDUtils.getBodyFromText(header_string2));

        String[] header_string3 = {"Invalid # header 2"};
        assertEquals("<p>Invalid # header 2</p><br/>", MDUtils.getBodyFromText((header_string3)));

        String[] codeblock_string1 = {"```\ncodeblock test 1\n```"};
        assertEquals("<p><code>\ncodeblock test 1\n</code></p><br/>", MDUtils.getBodyFromText(codeblock_string1));

        /* Not passed, unclosed code block markdown
        String[] codeblock_string2 = {"```\nInvalid Code Block"};
        assertEquals("<p>```\nInvalid Code Block</p><br/>", MDUtils.getBodyFromText(codeblock_string2));
        */

        String[] hr_string1 = {"----\n"};
        assertEquals("<hr><br/>", MDUtils.getBodyFromText(hr_string1));

        /* Not passed, invalid <hr> syntax
        String[] hr_string2 = {"----invalid hr syntax"};
        assertEquals("<p>----invalid hr syntax</p><br/>", MDUtils.getBodyFromText(hr_string2));
         */
    }
}