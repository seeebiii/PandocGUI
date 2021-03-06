# PandocGUI
This project provides a GUI for [Pandoc](https://github.com/jgm/pandoc) by using JavaFX. It's a kind of prototype for me to learn JavaFX.

# Author
Sebastian Hesse

# Technologies
Java, JavaFX, Spring, CSS, Maven

# Usage
1. You can download the source code, compile the files and run the `Main` class. 
2. An application opens with multiple text fields and some buttons. You need to specify the location of your Pandoc executable, the output file you want to create and at least one file you want to convert. 
3. Optional: add command options.
4. Then click "Generate" and the corresponding Pandoc command is generated.
5. Check generated command and click "Execute!" to execute the command.
6. Be happy :)

# TODO
- Add options by mouse click, without typing them by yourself.
- Allow to change ordering of input files
- Don't allow duplicate input files
- Print out Pandoc's output if available
- Know the last viewed directory for all file dialogs
- Open output file or directory after successful conversion
- Internationalization

# License
The MIT License (MIT)

Copyright (c) 2015 Sebastian Hesse

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
