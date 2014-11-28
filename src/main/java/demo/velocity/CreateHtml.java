package demo.velocity;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
public class CreateHtml {
    public static void main(String[] args) {
        try {
            //初始化vm模板
            Template template=Velocity.getTemplate("template\\test.vm","UTF-8");
            //初始化上下文
            VelocityContext context=new VelocityContext();
            //添加数据到上下文中
            context.put("title", "我的第一个velocity页面");
            //生成html页面
            PrintWriter pw=new PrintWriter("f:\\myvelocity.html");
            template.merge(context, pw);
            //关闭流
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ResourceNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ParseErrorException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       
    }
}