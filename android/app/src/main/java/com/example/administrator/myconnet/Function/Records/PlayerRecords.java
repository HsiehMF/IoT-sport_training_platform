package com.example.administrator.myconnet.Function.Records;

        import android.content.Intent;
        import android.graphics.Color;
        import android.os.Bundle;
        import android.os.Environment;
        import android.support.v7.app.AppCompatActivity;
        import android.widget.ArrayAdapter;
        import android.widget.ListView;

        import com.example.administrator.myconnet.R;

        import org.achartengine.ChartFactory;
        import org.achartengine.chart.PointStyle;
        import org.achartengine.model.XYMultipleSeriesDataset;
        import org.achartengine.model.XYSeries;
        import org.achartengine.renderer.XYMultipleSeriesRenderer;
        import org.achartengine.renderer.XYSeriesRenderer;

        import java.io.BufferedReader;
        import java.io.File;
        import java.io.FileReader;
        import java.io.FilenameFilter;
        import java.io.IOException;
        import java.util.ArrayList;
        import java.util.List;
        import java.util.regex.Matcher;
        import java.util.regex.Pattern;


public class PlayerRecords extends AppCompatActivity {
    String Begining,End;
    int count=0;//計數器
    String temp[][]=new String[201][6];
    int [][]stastic=new int[201][6];
    double resultdata[][]=new double[500][6];
    String folder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_records);

        Bundle bundle = this.getIntent().getExtras();
        folder = bundle.getString("folder");
        System.out.println("folder= "+folder);


        FilenameFilter mediafilefilter = new FilenameFilter(){
            private String[] filter = {".txt"};
            @Override
            public boolean accept(File dir, String filename) {

                for(int i= 0;i< filter.length ; i++){
                    if(filename.indexOf(filter[i]) != -1)return true;
                }
                return false;
            }};
//取得Android記憶卡路徑，並檢查是否有media目錄在其中，沒有則建立
        File dir = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        String p = dir.getParent() + "/" + dir.getName() + "/"+folder;
        File mediaDiPath = new File(p);
        if (!mediaDiPath.exists())mediaDiPath.mkdir();
//取得media目錄中的媒體檔案，並設定過濾器
        File[] mediaInDir = mediaDiPath.listFiles(mediafilefilter);
//將目錄內容建立清單
        CharSequence[] list = new CharSequence[mediaInDir.length];
        for (int i = 0; i < list.length; i++) {
            list[i] = mediaInDir[i].getName();
        }

        ListView lv=(ListView)findViewById(R.id.listView_lv);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        lv.setAdapter(adapter);
//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                FilenameFilter mediafilefilter = new FilenameFilter(){
//                    private String[] filter = {".txt"};
//                    @Override
//                    public boolean accept(File dir, String filename) {
//
//                        for(int i= 0;i< filter.length ; i++){
//                            if(filename.indexOf(filter[i]) != -1)return true;
//                        }
//                        return false;
//                    }};
////取得Android記憶卡路徑，並檢查是否有media目錄在其中，沒有則建立
//                File dir = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
//                String p = dir.getParent() + "/" + dir.getName() + "/"+folder;
//                File mediaDiPath = new File(p);
//                if (!mediaDiPath.exists())mediaDiPath.mkdir();
////取得media目錄中的媒體檔案，並設定過濾器
//                File[] mediaInDir = mediaDiPath.listFiles(mediafilefilter);
//                mediaInDir[position].getName();
////                System.out.println(mediaInDir[position].getName());
//                Draw(mediaInDir[position].getName());
//            }
//
//        });

    }
    public void Draw(String string){
        File dir = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        String path = dir.getParent() + "/" + dir.getName() + "/"+folder+"/"+string;
        int []x=count(path);//屬行數與秒數x[0]=lines ; x[1]=Secs
        int onecal=(x[0]/x[1])*5;//行數除以總秒數再以5秒計算
        int S,E;
        S=onecal*0;
        E=onecal*1;
//    System.out.println("lines= "+x[0]+"\t Secs= "+x[1]+"\t E= "+E);
        int i=0;//共多少筆資要加一筆
        do {
            int show[]=caculate(S, E,path);
            if(E>x[0]){
                show=caculate(S,x[0],path);
            }
//        int show[]=caculate(S, E,path);
            for(int j=0;j<6;j++){
                resultdata[i][j]=show[j];
//            System.out.println(resultdata[i][j]);
            }
            i++;
            E+=onecal;
            S+=onecal;
        } while (E<x[0]);
//    System.out.println("i= "+i);
        String[] titles = new String[] { "X加速度變化量", "Y軸腳加速度變化量"}; // 定義折線的名稱
        List<double[]> xaxis = new ArrayList<double[]>(); // 點的x坐標
        List<double[]> yaxis = new ArrayList<double[]>(); // 點的y坐標

        XYMultipleSeriesDataset dataset = buildDatset(titles, resultdata, i,x[1]); // 儲存座標值

        int[] colors = new int[] { Color.BLUE, Color.GREEN };// 折線的顏色
        PointStyle[] styles = new PointStyle[] { PointStyle.CIRCLE, PointStyle.DIAMOND }; // 折線點的形狀
        XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles, true);
        setChartSettings(renderer, "折線圖展示", "X軸名稱", "Y軸名稱", 0,15, -30000, 30000, Color.BLACK);// 定義折線圖

        Intent intent=ChartFactory.getLineChartIntent(this, dataset, renderer);
        startActivity(intent);
    }
    public int[] caculate(int start, int end,String path){
        String string=null;
        int x=0;
        if(end<start){
            int temp=end;
            end=start;
            start=end;
        }
        try {
            String[] g = new String [6];
            FileReader FileReader = new FileReader(path);
            BufferedReader reader=new BufferedReader(FileReader);
            while ((string = reader.readLine()) != null) {
                int i = 0;
                if(start<=x && x<end && (end-start)<200) {
                    for (String a : string.split(",")) {
                        a = a.trim();
                        if (a.equals("a/g")) {
                            i = 0;
                        }
                        if (i == 1) {
                            g[0] =a;
                        } else if (i == 2) {
                            g[1] =a;
                        } else if (i == 3) {
                            g[2] =a;
                        } else if (i == 4) {
                            g[3] =a;
                        } else if (i == 5) {
                            g[4] =a;
                        } else if (i == 6) {
                            g[5] =a;
                        }
                        i++;
                    }
                    storage(end-start,g);
                }
                x++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        int []fin=critical(stastic);
        return fin;
    }
    public void storage(int x,String str[]){
        if (count<200){
            for (int i=0;i<6;i++){
                if(str[i]==null){
                    str[i]="0";
                }else if(str[i].equals("")){
                    str[i]="0";
                }
                temp[count][i]=str[i];
            }
            count++;
        }
        if(count==x){
            convert();
        }
    }
    public void convert(){
        int i,j;
        for (i=0;i<count;i++){
            j=0;
            while (j<6) {
                if (isNumeric(temp[i][j])==true){
                    stastic[i][j] = Integer.valueOf(temp[i][j]);
//                    System.out.println(stastic[i][j]);
                }else {
                    stastic[i][j]=0;
                }
                j++;
            }
        }
        count=0;
    }
    public boolean isNumeric(String str)
    {
//        System.out.println(str);
        Pattern pattern = Pattern.compile("^-?[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() )
        {
            return false;
        }
        return true;
    }
    public int[] critical(int data[][]){
        int maxtemp[]=new int[6];
        int mintemp[]=new int[6];
        int critical[]=new int[6];
        maxtemp[0]=data[0][0];
        maxtemp[1]=data[0][1];
        maxtemp[2]=data[0][2];
        maxtemp[3]=data[0][3];
        maxtemp[4]=data[0][4];
        maxtemp[5]=data[0][5];
        for (int i=0;i<201;i++){
            if (maxtemp[0]>data[i][0]){
                maxtemp[0]=data[i][0];
            }
            if (maxtemp[1]>data[i][1]){
                maxtemp[1]=data[i][1];
            }
            if (maxtemp[2]>data[i][2]){
                maxtemp[2]=data[i][2];
            }
            if (maxtemp[3]>data[i][3]){
                maxtemp[3]=data[i][3];
            }
            if (maxtemp[4]>data[i][4]){
                maxtemp[4]=data[i][4];
            }
            if (maxtemp[5]>data[i][5]){
                maxtemp[5]=data[i][5];
            }
        }
        mintemp[0]=data[0][0];
        mintemp[1]=data[0][1];
        mintemp[2]=data[0][2];
        mintemp[3]=data[0][3];
        mintemp[4]=data[0][4];
        mintemp[5]=data[0][5];
        for (int i=0;i<201;i++){
            if (mintemp[0]<data[i][0]){
                mintemp[0]=data[i][0];
            }
            if (mintemp[1]<data[i][1]){
                mintemp[1]=data[i][1];
            }
            if (mintemp[2]<data[i][2]){
                mintemp[2]=data[i][2];
            }
            if (mintemp[3]<data[i][3]){
                mintemp[3]=data[i][3];
            }
            if (mintemp[4]<data[i][4]){
                mintemp[4]=data[i][4];
            }
            if (mintemp[5]<data[i][5]){
                mintemp[5]=data[i][5];
            }
        }
        critical[0]=maxtemp[0]-mintemp[0];
        critical[1]=maxtemp[1]-mintemp[1];
        critical[2]=maxtemp[2]-mintemp[2];
        critical[3]=maxtemp[3]-mintemp[3];
        critical[4]=maxtemp[4]-mintemp[4];
        critical[5]=maxtemp[5]-mintemp[5];
        temp=new String[201][6];
        stastic=new int[201][6];
        return critical;
    }
    public int[] count(String path){
        String string=null;
        int flag=-100;
        String str = null;
        String temp[]=new String[150];
        int lines=0;//共有幾行
        int Secs=0;//共花多少時間
        int []x=new int[2];
        try {
            String[] g = new String[6];
            FileReader FileReader = new FileReader(path);
            BufferedReader reader=new BufferedReader(FileReader);
            while ((string=reader.readLine()) != null) {
                int i = 0;
                int j=0;
                for (String a : string.split(",")) {
                    a=a.trim();
                    if(a.equals("Beginging")) {flag=-1;}
                    if (j == 1 ) {
                        if( flag==-1){
                            Begining=a;
                            flag=-100;
                        }
                    }
                    if(a.equals("THE END")) {flag=-2;}
                    if (j == 1 ) {
                        if( flag==-2){
                            End=a;
                            flag=-100;
                        }
                    }
                    i++;j++;
                }
                lines++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Secs=Integer.valueOf(End)-Integer.valueOf(Begining);
        x[0]=lines;
        x[1]=Secs;
        return x;
    }
    private void setChartSettings(XYMultipleSeriesRenderer renderer, String title, String xTitle,
                                  String yTitle, double xMin, double xMax, double yMin, double yMax, int axesColor) {
        renderer.setChartTitle(title); // 折線圖名稱
        renderer.setChartTitleTextSize(24); // 折線圖名稱字形大小
        renderer.setXTitle(xTitle); // X軸名稱
        renderer.setYTitle(yTitle); // Y軸名稱
        renderer.setXAxisMin(xMin); // X軸顯示最小值
        renderer.setXAxisMax(xMax); // X軸顯示最大值
        renderer.setXLabelsColor(Color.BLACK); // X軸線顏色
        renderer.setYAxisMin(yMin); // Y軸顯示最小值
        renderer.setYAxisMax(yMax); // Y軸顯示最大值
        renderer.setAxesColor(axesColor); // 設定坐標軸顏色
        renderer.setYLabelsColor(0, Color.BLACK); // Y軸線顏色
        renderer.setLabelsColor(Color.BLACK); // 設定標籤顏色
        renderer.setMarginsColor(Color.WHITE); // 設定背景顏色
        renderer.setShowGrid(true); // 設定格線
    }

    private XYMultipleSeriesRenderer buildRenderer(int[] colors, PointStyle[] styles, boolean fill) {
        XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
        int length = colors.length;
        for (int i = 0; i < length; i++) {
            XYSeriesRenderer r = new XYSeriesRenderer();
            r.setColor(colors[i]);
            r.setPointStyle(styles[i]);
            r.setFillPoints(fill);
            renderer.addSeriesRenderer(r); //將座標變成線加入圖中顯示
        }
        return renderer;
    }

    private XYMultipleSeriesDataset buildDatset(String[] titles, double [][]xValues,int count,int Sec) {
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        int length = titles.length; // 折線數量
        for (int i = 0; i < length; i++) {
            double []xV=new double[500];
            double []yV=new double[500];
            int j=0;
            // XYseries對象,用於提供繪製的點集合的資料
            System.out.println("titles= "+titles[i]);
            XYSeries series = new XYSeries(titles[i]); // 依據每條線的名稱新增
            if (i==0) {
                while (j < count) {
                    yV[j] = xValues[j][1];
                    xV[j]=5*(j+1);
                    j++;
                }
            }
            if (i==1){
                while (j < count) {
                    yV[j] = xValues[j][3];
                    xV[j]=5*(j+1);
                    j++;
                }
            }
            int seriesLength = xV.length; // 有幾個點
            for (int k = 0; k < seriesLength; k++) // 每條線裡有幾個點
            {
                series.add(xV[k], yV[k]);
            }
            dataset.addSeries(series);
        }
        return dataset;
    }
}
