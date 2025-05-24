#!/usr/bin/env node
const fs = require('fs');
const path = require('path');
const yargs = require('yargs/yargs')
const { hideBin } = require('yargs/helpers')

function commandLineArgs() {
    return yargs(hideBin(process.argv))
        .scriptName("remove-gpt-chars")
        .usage("$0 [args]")
        .describe("i", "Input file path")
        .describe("e", "Encoding of the input file (default: utf8)")
        .describe("o", "Output file path (if not provided, same as input)")
        .demandOption(["i"])
        .argv
}

const argv = commandLineArgs();
const inputPath = argv.i;
const outputPath = argv.o || inputPath;
const encoding = argv.e || 'utf8';

// Read file
let content;
try {
    content = fs.readFileSync(inputPath, encoding);
} catch (err) {
    console.error(`Error reading file: ${err.message}`);
    process.exit(1);
}

// Remove non-printable and non-ASCII characters
const cleaned = content.replace(/[^\x20-\x7E\n\r\t]/g, '');

// Write result
try {
    fs.writeFileSync(outputPath, cleaned, 'utf8');
    console.log(`Cleaned content written to ${outputPath}`);
} catch (err) {
    console.error(`Error writing file: ${err.message}`);
    process.exit(1);
}
