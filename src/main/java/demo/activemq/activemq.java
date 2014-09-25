package demo.activemq;

import javax.jms.JMSException;

public class activemq {

	public static void main(String[] args) throws JMSException, Exception {
		// TODO Auto-generated method stub     
        ProducerTool producer = new ProducerTool();    
        // 延时500毫秒之后发送消息     
        producer.produceMessage("Hello, world!");     
        producer.close();     
    }     

	}

