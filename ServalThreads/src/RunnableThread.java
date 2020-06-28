
public class RunnableThread extends Thread implements Runnable
{
	private String Name;
	public RunnableThread(String name) {
		Name = name;
	}
	@Override
	public void run()
	{
		  for (int i = 0; i < 5; i++) {
	            System.out.println(Name + "运行  :  " + i);
	            try {
	            	Thread.sleep((int) Math.random() * 10);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
		
	}
	
}