package demo.activemq;

import javax.jms.JMSException;

public class ConsumerTest {
	public static void main(String[] args) throws JMSException, Exception {
		// TODO Auto-generated method stub     
        ConsumerTool consumer = new ConsumerTool();     
        // 开始监听     
        consumer.consumeMessage();     
        // 延时500毫秒之后停止接受消息     
        Thread.sleep(500);     
        consumer.close();     
    }     
}
