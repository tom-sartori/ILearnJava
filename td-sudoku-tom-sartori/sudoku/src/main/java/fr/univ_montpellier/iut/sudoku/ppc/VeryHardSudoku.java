package fr.univ_montpellier.iut.sudoku.ppc;

import org.apache.commons.cli.*;
import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solver;
import org.chocosolver.solver.variables.IntVar;

import static org.chocosolver.solver.search.strategy.Search.minDomLBSearch;
import static org.chocosolver.util.tools.ArrayUtils.append;

public class VeryHardSudoku {

    static int n;
    static int s;
    private static int instance;
    private static long timeout = 3600000; // one hour

    IntVar[][] rows, cols, shapes;

    Model model;

    public static void main(String[] args) throws ParseException {

        final Options options = configParameters();
        final CommandLineParser parser = new DefaultParser();
        final CommandLine line = parser.parse(options, args);

        boolean helpMode = line.hasOption("help"); // args.length == 0
        if (helpMode) {
            final HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("sudoku", options, true);
            System.exit(0);
        }
        instance = 16;
        // Check arguments and options
        for (Option opt : line.getOptions()) {
            checkOption(line, opt.getLongOpt());
        }

        n = instance;
        s = (int) Math.sqrt(n);

        new VeryHardSudoku().solve();
    }

    public void solve() {

        buildModel();
        model.getSolver().showStatistics();
        Solver solver = model.getSolver();
        solver.limitSolution(100);

        while (solver.solve()) {

            StringBuilder st = new StringBuilder(String.format("Sudoku -- %s\n", instance, " X ", instance));
            st.append("\t");
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    st.append(rows[i][j].getName() + " = " + toHex(rows[i][j].getValue())).append("\t\t\t");
                }
                st.append("\n\t");
            }

            System.out.println(st.toString());

            System.out.println(this);

            System.out.println("--------------------------------------");
        }
        System.out.println("Il y a " + solver.getSolutionCount() + " solutions possibles. ");
    }

    private char toHex (int n) {
        switch (n) {
            case 0 :
                return '0';
            case 1 :
                return '1';
            case 2 :
                return '2';
            case 3 :
                return '3';
            case 4 :
                return '4';
            case 5 :
                return '5';
            case  6 :
                return '6';
            case 7 :
                return '7';
            case 8 :
                return '8';
            case 9 :
                return '9';
            case 10 :
                return 'A';
            case 11 :
                return 'B';
            case 12:
                return 'C';
            case 13 :
                return 'D';
            case  14 :
                return 'E';
            case  15 :
                return 'F';
            case  16 :
                return 'G';
        }
        return 'Z';
    }

    private int to10 (char n) {
        switch (n) {
            case '0' :
                return 0;
            case '1' :
                return 1;
            case '2' :
                return 2;
            case '3' :
                return 3;
            case '4' :
                return 4;
            case '5' :
                return 5;
            case  '6' :
                return 6;
            case '7' :
                return 7;
            case '8' :
                return 8;
            case '9' :
                return 9;
            case 'A' :
                return 10;
            case 'B' :
                return 11;
            case 'C' :
                return 12;
            case 'D' :
                return 13;
            case  'E' :
                return 14;
            case  'F' :
                return 15;
            case  'G' :
                return 16;
        }
        return 999;
    }

    public void buildModel() {
        model = new Model();

        rows = new IntVar[n][n];
        cols = new IntVar[n][n];
        shapes = new IntVar[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                rows[i][j] = model.intVar("c_" + i + "_" + j, 1, n, false);
                cols[j][i] = rows[i][j];
            }
        }

        for (int i = 0; i < s; i++) {
            for (int j = 0; j < s; j++) {
                for (int k = 0; k < s; k++) {
                    for (int l = 0; l < s; l++) {
                        shapes[j + k * s][i + (l * s)] = rows[l + k * s][i + j * s];
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            System.out.println(i);
            model.allDifferent(rows[i], "AC").post();
            model.allDifferent(cols[i], "AC").post();
            model.allDifferent(shapes[i], "AC").post();
        }

        // --------------------------------------
        // TODO: add constraints here


        addContraintes();


    }

    // Ajout des contraintes de la question 8.
    public void addContraintes () {

        char [][] tab = {
                {'0', 'G', '0', '0',   'F', '8', '9', '6',   '4', 'B', 'D', '5',   '0', '0', '3', '0'},
                {'6', 'C', '0', '0',   '0', '0', '4', 'E',   '2', '7', '0', '0',   '0', '0', '5', '9'},
                {'0', '0', '0', 'D',   '0', '0', 'G', '7',   'F', 'E', '0', '0',   '6', '0', '0', '0'},
                {'0', '0', '4', '3',   'A', '0', '0', '0',   '0', '0', '0', '6',   '1', 'B', '0', '0'},

                {'7', '0', '0', '5',   '8', 'F', '0', '0',   '0', '0', 'B', 'E',   '9', '0', '0', 'G'},
                {'8', '0', '0', '0',   '9', '0', '0', '4',   'D', '0', '0', '3',   '0', '0', '0', '2'},
                {'C', '1', '3', '0',   '0', '0', '6', '0',   '0', 'G', '0', '0',   '0', 'F', '4', '5'},
                {'9', 'D', 'B', '0',   '0', 'G', '0', '0',   '0', '0', 'F', '0',   '0', '7', 'A', '6'},

                {'G', 'B', 'A', '0',   '0', '2', '0', '0',   '0', '0', '7', '0',   '0', '5', '6', 'D'},
                {'5', '6', 'F', '0',   '0', '0', 'A', '0',   '0', '2', '0', '0',   '0', '8', '7', '4'},
                {'D', '0', '0', '0',   '6', '0', '0', '9',   '5', '0', '0', 'G',   '0', '0', '0', 'F'},
                {'3', '0', '0', 'C',   'B', '5', '0', '0',   '0', '0', 'A', '4',   'G', '0', '0', '1'},

                {'0', '0', '9', '6',   'G', '0', '0', '0',   '0', '0', '0', '7',   '2', 'C', '0', '0'},
                {'0', '0', '0', 'G',   '0', '0', 'B', 'D',   'C', '5', '0', '0',   'F', '0', '0', '0'},
                {'4', '3', '0', '0',   '0', '0', '8', '2',   'G', 'F', '0', '0',   '0', '0', '1', '7'},
                {'0', '8', '0', '0',   '5', '9', 'E', 'A',   '1', '3', '2', 'D',   '0', '0', 'G', '0'}
        };


        for (int i = 0; i < tab.length; i++)
            for (int j = 0; j < tab[0].length; j++)
                if (tab[i][j] != '0')
                    model.arithm(rows[i][j],"=", to10(tab[i][j])).post();
    }

    public String toString(){
        String str = "";
        for(int i=0 ; i < n ; i++) {
            if (i%s == 0)
                str = str + "\n";
            for (int j = 0; j < n; j++) {
                if (j%s == 0)
                    str = str + "   ";
                str = str + toHex(rows[i][j].getValue()) + " ";
            }
            str = str + "\n";
        }
        return str;
    }

    // Check all parameters values
    public static void checkOption(CommandLine line, String option) {

        switch (option) {
            case "inst":
                instance = Integer.parseInt(line.getOptionValue(option));
                break;
            case "timeout":
                timeout = Long.parseLong(line.getOptionValue(option));
                break;
            default: {
                System.err.println("Bad parameter: " + option);
                System.exit(2);
            }

        }

    }

    // Add options here
    private static Options configParameters() {

        final Option helpFileOption = Option.builder("h").longOpt("help").desc("Display help message").build();

        final Option instOption = Option.builder("i").longOpt("instance").hasArg(true).argName("sudoku instance")
                .desc("sudoku instance size").required(false).build();

        final Option limitOption = Option.builder("t").longOpt("timeout").hasArg(true).argName("timeout in ms")
                .desc("Set the timeout limit to the specified time").required(false).build();

        // Create the options list
        final Options options = new Options();
        options.addOption(instOption);
        options.addOption(limitOption);
        options.addOption(helpFileOption);

        return options;
    }

    public void configureSearch() {
        model.getSolver().setSearch(minDomLBSearch(append(rows)));

    }

}
