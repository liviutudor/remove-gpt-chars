#!/usr/bin/env python3

import argparse
import sys
import os

def clean_text(text):
    # Keeps printable ASCII and basic whitespace
    return ''.join(c for c in text if 32 <= ord(c) <= 126 or c in '\n\r\t')

def main():
    parser = argparse.ArgumentParser(description="Remove ChatGPT-style special characters from a plain text file.")
    parser.add_argument("-i", "--input", required=True, help="Input text file path")
    parser.add_argument("-o", "--output", help="Output file path (optional). If not set, overwrites input file.")
    parser.add_argument("-e", "--encoding", default="utf8", help="Encoding to use (default: utf8)")

    args = parser.parse_args()

    input_file = args.input
    output_file = args.output or args.input
    encoding = args.encoding

    if not os.path.isfile(input_file):
        print(f"Error: File not found - {input_file}", file=sys.stderr)
        sys.exit(1)

    try:
        with open(input_file, 'r', encoding=encoding) as f:
            content = f.read()
    except Exception as e:
        print(f"Failed to read input file: {e}", file=sys.stderr)
        sys.exit(1)

    cleaned = clean_text(content)

    try:
        with open(output_file, 'w', encoding=encoding) as f:
            f.write(cleaned)
        print(f"Cleaned text written to {output_file}")
    except Exception as e:
        print(f"Failed to write output file: {e}", file=sys.stderr)
        sys.exit(1)

if __name__ == "__main__":
    main()
