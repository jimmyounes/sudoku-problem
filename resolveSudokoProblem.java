import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class resolveSudokoProblem {
    public  int [][] matrix;
    public   int n=9;

    ArrayList<ArrayList<Integer>> cell=new ArrayList<ArrayList<Integer>>() ;//An Array list for each cell with possible fill
    ArrayList<ArrayList<Integer>> submatrix=new ArrayList<ArrayList<Integer>>();//item that are already presend in each submatriw 3*3

    //a constructor from a matrix
    public  resolveSudokoProblem(int[][] matrix) {
        this.matrix = matrix;
        Fillsubmatrix();
        for (int i=0;i<n*n;i++){
            cell.add(new ArrayList());

        }
        DefinePossibilityForEachCell();

    }
    //a constructor from  a file
    public resolveSudokoProblem(String filename){

        this.matrix=new int[9][9];

        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            int row=0;
            while ((line = br.readLine()) != null) {
                String[] t=line.split(",");
                for(int j=0;j<9;j++)this.matrix[row][j]=Integer.valueOf(t[j]);
                row++;
            }
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
        Fillsubmatrix();
        for (int i=0;i<n*n;i++){
            cell.add(new ArrayList());

        }
        DefinePossibilityForEachCell();
    }
    public void Fillsubmatrix(){

    for(int m=0;m<3;m++){
        for(int n=0;n<3;n++) {
            ArrayList list = new ArrayList();
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                      int val=matrix[m * 3 + i][n * 3 + j];
                if(val!=-1)    list.add(val);
                }
            }
            submatrix.add(list);
        }
    }
    }
    public void displaySubstring(){
        for(int i=0;i<submatrix.size();i++){
            System.out.println("the sub matrix "+i+"contain"+submatrix.get(i));
        }
    }
    public void DefinePossibilityForEachCell(){
        int numberOfThesubarray;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++) {
                if(matrix[i][j]==-1){
                numberOfThesubarray = (i/3)*3 + (j / 3);

                ArrayList list = new ArrayList();
                for (int m = 1; m < 10; m++) {
                    list.add(m);
                }
                //check the items that are already present in the row of the cell to remove them
                for (int row = 0; row < n; row++) {
                    if (matrix[row][j] != -1) list.remove(Integer.valueOf(matrix[row][j]));
                }
                    //check the items that are already present in the column of the cell to remove them
                for (int column = 0; column < n; column++) {
                    if (matrix[i][column] != -1) {
                        if (list.contains(matrix[i][column])) list.remove(Integer.valueOf(matrix[i][column]));
                    }
                }

                ArrayList listt = submatrix.get(numberOfThesubarray);

                for (int m = 0; m < listt.size(); m++) {
                    if (list.contains(listt.get(m))) list.remove(Integer.valueOf((Integer) listt.get(m)));
                }

                cell.set(i*n+j,list);
            }
            }
        }
    }
    public void displayCellPossibility(){

        for(int i=0;i<cell.size();i++){
            System.out.println("the sub matrix "+i+" possibility"+cell.get(i));
        }
    }
    public boolean Solved(){
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(matrix[i][j]==-1)return false;
            }
        }
        return true;
    }

    public boolean resolveSudokuproblem(){


        while(!Solved()){
            int min=30;
            int indexi=0;
            int indexj=0;
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                  if(matrix[i][j]==-1 && cell.get(i*n+j).size()<min) {
                      min=cell.get(i*n+j).size();
                      indexi = i;
                      indexj = j;
                  }
                }
            }

            DefinePossibilityForEachCell();


            if(cell.get(indexi*n+indexj).size()==1){
                int value=  cell.get(indexi*n+indexj).get(0);
                matrix[indexi][indexj]=value;
                cell.get(indexi*n+indexj).remove(Integer.valueOf(value));
                DefinePossibilityForEachCell();


            }
            else{
                if(matrix[indexi][indexj]==-1 && cell.get(indexi*n+indexj).size()==0 )return false;

                for(int i=0;i<cell.get(indexi*n+indexj).size();i++){
                    matrix[indexi][indexj]=cell.get(indexi*n+indexj).get(i);
                    resolveSudokoProblem reslove=new resolveSudokoProblem(matrix);
                    if(reslove.resolveSudokuproblem()==true){
                        int value=  cell.get(indexi*n+indexj).get(i);
                        matrix[indexi][indexj]=value;
                        cell.get(indexi*n+indexj).remove(Integer.valueOf(value));
                        DefinePossibilityForEachCell();
                        break;
                    }
                }

                return false;
            }
        }
        return true;
    }
    public void printThematrix(){
        System.out.println("-----------------------------------------------------------------------");
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("-----------------------------------------------------------------------");
    }


}
