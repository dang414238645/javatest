package demo.spi;

import java.util.Iterator;
import java.util.ServiceLoader;
/**
 * 在java应用下创建 META-INF目录，services文件夹，文件为借口全名，文件内容为实现类
 * @author changpengfei
 * 这里是不行的，在maven下
 *
 */
public class SearchTest {  
  
    public static void main(String[] args) {  
        ServiceLoader<Search> s = ServiceLoader.load(Search.class);  
        Iterator<Search> searchs = s.iterator();  
        if (searchs.hasNext()) {  
            Search curSearch = searchs.next();  
            curSearch.search("test");  
        }  
    }  
}  