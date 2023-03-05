public class Main {

    public static void main(String[] args){
     int [] row1={-1,4,-1,-1,-1,-1,-1,3,8};
     int [] row2={1,-1,-1,3,2,4,6,-1,-1};
     int [] row3={-1,-1,-1,-1,9,-1,-1,-1,-1};
     int [] row4={3,-1,2,4,5,8,-1,-1,1};
     int [] row5={-1,8,-1,2,1,9,-1,6,-1};
     int [] row6={-1,1,-1,-1,-1,-1,-1,8,-1};
     int [] row7={-1,2,7,1,-1,6,3,9,5};
     int [] row8={-1,9,1,5,4,-1,7,-1,6};
     int [] row9={-1,-1,6,9,7,-1,8,-1,-1};
     int[][] matrix={row1,row2,row3,row4,row5,row6,row7,row8,row9};

     resolveSudokoProblem resolveSudokoProblem=new resolveSudokoProblem("sudokuInitialTable.txt");
     //resolveSudokoProblem.displaySubstring();
     //resolveSudokoProblem.displayCellPossibility();

     resolveSudokoProblem.resolveSudokuproblem();
     resolveSudokoProblem.printThematrix();
    // System.out.println(resolveSudokoProblem.cell.size());
    }
}
