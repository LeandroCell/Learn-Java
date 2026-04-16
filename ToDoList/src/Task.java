public class Task
{
    String title;
    boolean done;

    public Task(String title)
    {
        this.title = title;
        this.done = false;
    }

    public void markDone()
    {
        this.done = true;
    }
}
