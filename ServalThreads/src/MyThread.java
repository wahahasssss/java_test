
public class MyThread extends Thread{

	private String Name;
	private int count  = 10;
    public MyThread(String threadName) {
		Name = threadName;
	}
	public void run() {
		for(int i = 0;i<10;i++)
	    {
			System.out.println(Name+"运行:"+i);
	    }
		try {
			sleep((int)Math.random()*10);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}

	
