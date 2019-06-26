package com.design.pattern.structure;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:weilu
 * @Date: 2019/5/19 15:26
 * @Description: 组合模式
 * 以叫整体模式，用于把一组相似的对象当作一个单一的对象，
 * 组合模式依据树形结构来组合对象（对象可能是终点对象，也可能还包含子对象），用来表示部分及整体层次
 *
 * 使用场景：部分、整体场景，如树形菜单，文件、文件夹的管理
 */
public class CompositePattern {

    static class Employee{
        private String name;
        private String dept;
        private int salary;
        private List<Employee> subordiantes;

        public Employee(String name, String dept, int salary) {
            this.name = name;
            this.dept = dept;
            this.salary = salary;
            this.subordiantes = new ArrayList<>();
        }
        public void add(Employee employee){
            subordiantes.add(employee);
        }
        public void remove(Employee employee){
            subordiantes.remove(employee);
        }
        public List<Employee> getSubordinates(){
            return subordiantes;
        }

        @Override
        public String toString() {
            return "Employee{" +
                    "name='" + name + '\'' +
                    ", dept='" + dept + '\'' +
                    ", salary=" + salary +
                    ", subordiantes=" + subordiantes +
                    '}';
        }
    }

    public static void main(String[] args) {
        Employee CEO = new Employee("John","CEO", 30000);

        Employee headSales = new Employee("Robert","Head Sales", 20000);

        Employee headMarketing = new Employee("Michel","Head Marketing", 20000);

        Employee clerk1 = new Employee("Laura","Marketing", 10000);
        Employee clerk2 = new Employee("Bob","Marketing", 10000);

        Employee salesExecutive1 = new Employee("Richard","Sales", 10000);
        Employee salesExecutive2 = new Employee("Rob","Sales", 10000);

        CEO.add(headSales);
        CEO.add(headMarketing);

        headSales.add(salesExecutive1);
        headSales.add(salesExecutive2);

        headMarketing.add(clerk1);
        headMarketing.add(clerk2);

        //打印该组织的所有员工
        System.out.println(CEO);
        for (Employee headEmployee : CEO.getSubordinates()) {
            System.out.println(headEmployee);
            for (Employee employee : headEmployee.getSubordinates()) {
                System.out.println(employee);
            }
        }
    }
}
