Q4 : Le problème de `findSolution()` du sudoku imp est qu'il est très lent. De plus dès qu'on prend un sodoku grand, il y a très peu de chances qu'il trouve la solution. 

Q6 : Pour améliorer le sudoku imp, on peut se dire qu'à chaque fois qu'on veut ajouter une valeur aléatoire à la grille, on regarde qu'elle ne soit pas déjà dans sa ligne, colonne et carré. Cependant, on peut vite arriver sur un sudoku impossible et le programme risque de tourner indéfiniement. 

Pour résoudre ce problème de boucle infinie, j'ai choisi de retourner à la case précédente du sudoku lorsqu'on a déjà essayé n valeurs pour une case. Ainsi, cela diminue beaucoup de boucles infinies. De cette manière, pour des sudoku de taille inférieure ou égale à 9, je trouve des temps de résolution similaires au sudoku ppc. Cependant, pour des grandes tailles, cette méthode est beaucoup trop longue alors que le ppc est très rapide. 

Voir : `generateSolutionV3()`