package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentManger {
    public static void main(String[] args) {
        ArrayList<Student> arrayList=new ArrayList<Student>();

        while (true) {
            System.out.println("----------欢迎来到学生管理系统------------");
            System.out.println("1 添加学生");
            System.out.println("2 删除学生");
            System.out.println("3 修改学生");
            System.out.println("4 查看所有学生");
            System.out.println("5 退出");
            System.out.println("请输入你的选择：");

            Scanner sc = new Scanner(System.in);
            String line = sc.nextLine();


            switch (line) {
                case "1":
                    addStudent(arrayList);
                    break;
                case "2":
                    deleteStudent(arrayList);
                    break;
                case "3":
                    updateStudent(arrayList);
                    break;
                case "4":
                    getAllStudent(arrayList);
                    break;
                case "5":
                    System.out.println("谢谢使用");
                    //break;
                    System.exit(0);//jvm退出
            }


        }
    }


    //定义一个方法，判断学号是否被使用
    public static boolean isUsedSid(ArrayList<Student> array,String sid){
        //如果与集合中的某一个学生学号相同，返回true，如果都不相同返回false
        boolean flag=false;
        for (int i=0;i<array.size();i++){
            Student s=array.get(i);
            if (s.getSid().equals(sid)){
                flag=true;
                break;
            }
        }
        return flag;
    }
    //添加学生的方法
    public static void addStudent(ArrayList<Student> array){
        //从键盘获取student对象的变量
        Scanner sc=new Scanner(System.in);
        //为了让sid可以在循环外被使用，所以在外面定义sid变量
        String sid;
        while(true){
            System.out.println("请输入学生学号：");
            sid=sc.nextLine();
            boolean flag=isUsedSid(array,sid);
            if (flag){
                System.out.println("你输入的学号已经被使用了，请重新输入");
            }else {
                break;
            }
        }

        System.out.println("please input student name:");
        String name=sc.nextLine();
        System.out.println("please input student age:");
        String age=sc.nextLine();
        System.out.println("please input student address:");
        String address=sc.nextLine();

        //赋值给student对象
        Student student=new Student();
        student.setSid(sid);
        student.setName(name);
        student.setAge(age);
        student.setAddress(address);

        //添加到数组里
        array.add(student);
        //给出添加成功的提示
        System.out.println("add student success");
    }

    //查询所有学生的方法
    public static void getAllStudent(ArrayList<Student> array){
        //判断数组如果没有对象时，给出提示
        if (array.size()==0){
            System.out.println("no data");
            return;//这样程序就不往下执行了
        }

        System.out.println("学号\t\t姓名\t\t年龄\t\t居住地");

        //遍历数据
        for (int i=0;i< array.size();i++){
            Student s=array.get(i);
            System.out.println(s.getSid()+"\t\t"+s.getName()+"\t"+s.getAge()+"\t"+s.getAddress());

        }
    }

    //删除学生的方法
    public static void deleteStudent(ArrayList<Student> array){
        //从键盘录入要删除的学生学号，显示提示信息
        System.out.println("please input a number to delete:");
        Scanner sc=new Scanner(System.in);
        String sid=sc.nextLine();
        //在删除之前，对学号是否存在进行判断
        //如果不存在，显示提示信息
        //如果存在，再执行删除/修改操作
        int index=-1;
        for (int i=0;i<array.size();i++){
            Student s=array.get(i);
            if (s.getSid().equals(sid)){
                index=i;
                break;
            }
        }
        if (index==-1){
            System.out.println("不存在对应的学号");
        }else {
            array.remove(index);
            System.out.println("删除成功");
        }
    }

    //修改学生的方法
    public static void updateStudent(ArrayList<Student> array){
        //键盘录入要修改的学生学号，显示提示信息
        System.out.println("please input a sid to update:");
        Scanner sc=new Scanner(System.in);
        String sid=sc.nextLine();

        //遍历集合修改对应的学生信息
        int index=-1;
        for (int i=0;i<array.size();i++){
            Student student=array.get(i);
            if(student.getSid().equals(sid)){
                index=i;
                break;
            }
        }
        if (index==-1){
            System.out.println("没有对应的学号");
        }else {
            //键盘录入要修改的学生信息
            System.out.println("请输入新的学号：");
            String sid1=sc.nextLine();
            System.out.println("请输入新的名字：");
            String name=sc.nextLine();
            System.out.println("请输入新的年龄：");
            String age=sc.nextLine();
            System.out.println("请输入新的地址：");
            String address=sc.nextLine();

            //创建学生对象
            Student s=new Student();
            s.setSid(sid1);
            s.setName(name);
            s.setAge(age);
            s.setAddress(address);
            array.set(index,s);
            System.out.println("修改成功");
        }
    }
}
