import java.net.URL;

import com.rabbitmq.client.AMQP.Basic.Get;

public class TaskInfo {
	
 
  private String TaskId;
  private String TaskName;
  private String Uri;
  private String Colletion;
  private boolean AutoDeleteQueue;
  private boolean QueueDurable;
  private boolean Exclusive;
  private String QueueName;
  public void setTaskId(String taskid) 
  {
	 TaskId = taskid;
  }
  public String GetTaskId()
  {
	return TaskId;
  }
public void setTaskName(String taskName)
{
	TaskName = taskName;
}
public String GetTaskName()
{
   return TaskName;	
}
public void setUri(String url)
{
   Uri = url;	
}
public String getUrl()
{
	return Uri;
}
public void setColletion(String colletion)
{
 Colletion = colletion;	
}
public String getColletion()
{
return Colletion;	
}
public void setAutoDeleteQueue(boolean autoDeleteQueue)
{
	AutoDeleteQueue = autoDeleteQueue;
}
public boolean getAutoDeleteQueue()
{
  return AutoDeleteQueue;	
}
public void setQueueDurable(boolean queueDurable)
{
   QueueDurable = queueDurable;	
}
public boolean getQueueDurable()
{
	return QueueDurable;
}
public void setExclusive(boolean exclusive)
{
   Exclusive = exclusive;	
}
public boolean getExclusive()
{
	return Exclusive;
}
public void setQueueName(String queueName) {
	QueueName = queueName;
}
public String getQueueName() {
	return QueueName;
}
}
