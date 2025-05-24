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
* [Python]()
* Java
* Bash

