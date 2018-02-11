#H1 Welcome to a wonderful world of LaTeX.
#H3 This is how I build my papers, using LaTeX and the following is the basic layout. Do you have any other routine, lets dicuss that on Monday!


    I have a main document, this does not contain any text at all. Only the preamble and input files.
    Then I have two subfolders, one for figures and one for the sections in the report.
    That is about it ;-D

#H3 Using .tex and git
Here is the important part. When using LaTeX and git, you only have to add .tex files.
Anyother will cause a failure, when trying to compile the document on another computer.
Therefore, only add and push the .tex files!
I have added the other file types, to the .gitignore. (Please double check your commits in case, I have made a mistake)