package demo.spi;

import java.util.List;

public class FileSearch implements Search {  
  
    public List<String> search(String keyword) {  
        System.out.println("now use file system search. keyword:" + keyword);  
        return null;  
    }  
  
}  