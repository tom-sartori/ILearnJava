package fr.univ_montpellier.iut.sudoku;

import fr.univ_montpellier.iut.sudoku.imp.Sudoku;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {

        Sudoku sudokuIMP = new Sudoku(4);
        sudokuIMP.findSolution();

        fr.univ_montpellier.iut.sudoku.ppc.Sudoku sudokuPPC = new fr.univ_montpellier.iut.sudoku.ppc.Sudoku();


        sudokuPPC.solve();
        System.out.println(sudokuPPC);

    }
}
