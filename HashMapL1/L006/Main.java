package HashMapL1.L006;
import java.io.*;
import java.util.*;

public class Main {
    public static class Student implements Comparable<Student> {
        int rno;
        int ht;
        int wt;
        Student(int rno, int ht, int wt){
            this.rno=rno;
            this.ht=ht;
            this.wt= wt;
        }

        public int compareTo(Student o){
            return this.rno-o.rno;
        }

    }
    public static void main(String[] args) throws Exception{
        PriorityQueue<Student> pq= new PriorityQueue<>();
        pq.add(new Student(12, 112, 65));
        pq.add(new Student(2, 122, 64));
        pq.add(new Student(5, 132, 69));
        pq.add(new Student(11, 172, 44));
        pq.add(new Student(17, 132, 69));

        while(pq.size()>0)
        {
            System.out.println(pq.peek());
            pq.remove();
        }


    }
}
