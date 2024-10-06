package utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviders {
    @DataProvider (name = "loginData")
    public String[][] getData() throws IOException{
        ExcelUtility util= new ExcelUtility(".\\testData\\LogIn_testData.xlsx");
        int totalrows=util.getRowCount("Sheet1");
        int totalcols= util.getCellCount("Sheet1",1);
        String[][] logInData =new String[totalrows][totalcols];
        for(int i=1;i<=totalrows;i++){
            for(int j=0;j<totalcols;j++)
            {
                logInData[i-1][j]=util.getCellData("Sheet1",i,j);

            }
        }
        return logInData;
    }
}
