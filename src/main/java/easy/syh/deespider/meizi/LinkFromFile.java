package easy.syh.deespider.meizi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LinkFromFile {
  public List<String> operateF() throws Exception {
    File file = new File("D:/_2018/_git/DeeRivenFiora/src/main/res/riven.txt");
    //HashMap<String,String> xxMap = new HashMap<>();
    ArrayList<String> list = new ArrayList();
    BufferedReader br = new BufferedReader(new FileReader(file));
    String line = null;
    while ((line = br.readLine()) != null){
      //xxMap.put(line,"");
      list.add("http:"+line);
    }
    br.close();
    return list;
  }
}
