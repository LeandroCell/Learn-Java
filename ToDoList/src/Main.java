import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        List<Task> tasks = new ArrayList<Task>();

        tasks.add(new Task("Pi2 lernen"));
        tasks.add(new Task("Ti1 lernen"));

        System.out.println("Alle Aufgaben: ");

        for (Task task : tasks) {
            if (task.done) {
                System.out.println(task.title + " - erledigt");
            }
            else  {
                System.out.println(task.title + " - offen");
            }
        }

        for (Task task : tasks) {
            if (task.title.equals("Pi2 lernen")) {
                task.markDone();
            }
        }

        System.out.println("Offene Aufgaben: ");

        for (Task task : tasks) {
            if (!task.done) {
                System.out.println(task.title + " - offen");
            }
        }


    }
}
