#!/bin/bash

# Check arguments
if [ -z "$1" ]; then
  echo "Usage: $0 input.txt [output.txt]"
  exit 1
fi

INPUT_FILE="$1"
OUTPUT_FILE="$2"

# Check if input file exists
if [ ! -f "$INPUT_FILE" ]; then
  echo "Error: Input file not found: $INPUT_FILE"
  exit 1
fi

# Use tr to remove non-printable characters (excluding \n \r \t)
# Keep ASCII range 32-126 plus newline, carriage return, tab
if [ -z "$OUTPUT_FILE" ]; then
  tr -cd '\11\12\15\40-\176' < "$INPUT_FILE"
else
  tr -cd '\11\12\15\40-\176' < "$INPUT_FILE" > "$OUTPUT_FILE"
  echo "Cleaned text written to $OUTPUT_FILE"
fi
