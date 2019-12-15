#!/usr/bin/bash
# cmps012b-pt.w18 grading
# usage: pa2.sh
# (run within your pa2 directory to test your code)

SRCDIR=https://raw.githubusercontent.com/legendddhgf/cmps012b-pt.w18.grading/master/pa2
EXE="pa2-check.sh"
SCRIPT=$(mktemp)

curl $SRCDIR/$EXE > $EXE
chmod +x $EXE
./$EXE
rm -f $EXE
