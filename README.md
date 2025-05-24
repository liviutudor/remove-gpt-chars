# remove-gpt-chars
Removes the odd whitespace characters ChatGPT introduces in text.
When generating text, ChatGPT introduces some odd non-printable characters that often go unnoticed in modern text editors, but end up mangling the text when run through a console or ASCII text reader.
Example:

```
t?M-^@M-^Ys val
```

This repo contains a set of source code which can help you remove those.

It contains source code for the following languages:

* [Javascript](remove-gpt-chars.js)
    * To run it: `npm install` and then `npm run clean -- -i <input_file> [-o <output file>] [-e <encoding>]`
* [Python](remove-gpt-chars.py)
    * Tun run it: `python3 remove_gpt_chars -i <input_file> [-o <output file>] [-e <encoding>]`
* [Bash](remove-gpt-chars.sh)
    * Run it: `remove-gpt-chars.sh inputfile [outputfile]`
* [Java](RemoveGptChars.java)
    * Requires Java 17
    * **Gradle** wrapper and build file provided
    * Run it: `./gradlew run --args="-i input.txt [-o output.txt] [-e encoding]"`
