package demo.spi;

import java.util.List;

public class DatabaseSearch implements Search {  
  
    public List<String> search(String keyword) {  
        System.out.println("now use database search. keyword:" + keyword);  
        return null;  
    }  
  
}  