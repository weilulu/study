package com.design.pattern.structure;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:weilu
 * @Date: 2019/5/19 14:26
 * @Description: 过滤器模式
 * 这种模式允许开发人员使用不同的标准来过滤一组对象，通过逻辑运算以解耦的方式把它们连接起来；
 * 它结合多个标准来获得单一标准
 */
public class FilterPattern {

    static class Person{
        private String name;
        private String gender;
        private String maritalStatus;

        public Person(String name, String gender, String maritalStatus) {
            this.name = name;
            this.gender = gender;
            this.maritalStatus = maritalStatus;
        }

        public String getName() {
            return name;
        }

        public String getGender() {
            return gender;
        }

        public String getMaritalStatus() {
            return maritalStatus;
        }
    }

    interface Criteria{
        List<Person> meetCriteria(List<Person> persons);
    }

    static class CriteriaMale implements Criteria{

        @Override
        public List<Person> meetCriteria(List<Person> persons) {
            List<Person> malePerson = new ArrayList<>();
            for(Person person:persons){
                if("MALE".equalsIgnoreCase(person.getGender())){
                    malePerson.add(person);
                }
            }
            return malePerson;
        }
    }

    static class CriteriaFemale implements Criteria{
        List<Person>  singlePersons = new ArrayList<>();

        @Override
        public List<Person> meetCriteria(List<Person> persons) {
            for(Person person : persons){
                if("FEMALE".equalsIgnoreCase(person.getGender())){
                    singlePersons.add(person);
                }
            }
            return singlePersons;
        }
    }
    static class CriteriaSingle implements Criteria {

        @Override
        public List<Person> meetCriteria(List<Person> persons) {
            List<Person> singlePersons = new ArrayList<Person>();
            for (Person person : persons) {
                if (person.getMaritalStatus().equalsIgnoreCase("SINGLE")) {
                    singlePersons.add(person);
                }
            }
            return singlePersons;
        }
    }
    static class AndCriteria implements Criteria{
        private Criteria criteria;
        private Criteria otherCriteria;

        public AndCriteria(Criteria criteria,Criteria otherCriteria){
            this.criteria = criteria;
            this.otherCriteria = otherCriteria;
        }

        @Override
        public List<Person> meetCriteria(List<Person> persons) {
            List<Person> firstCriteriaPerson = criteria.meetCriteria(persons);
            return otherCriteria.meetCriteria(firstCriteriaPerson);
        }
    }

    static class OrCriteria implements Criteria{
        private Criteria criteria;
        private Criteria otherCriteria;

        public OrCriteria(Criteria criteria, Criteria otherCriteria) {
            this.criteria = criteria;
            this.otherCriteria = otherCriteria;
        }

        @Override
        public List<Person> meetCriteria(List<Person> persons) {
            List<Person> firstCriteriaItems = criteria.meetCriteria(persons);
            List<Person> otherCriteraiItems = otherCriteria.meetCriteria(persons);

            for (Person person : otherCriteraiItems){
                //将firstCriteriaItems没有的person填到firstCriteriaItems里
                if(!firstCriteriaItems.contains(person)){
                    firstCriteriaItems.add(person);
                }
            }
            return firstCriteriaItems;
        }

        public static void main(String[] args) {
            List<Person> persons = new ArrayList<Person>();

            persons.add(new Person("Robert","Male", "Single"));
            persons.add(new Person("John","Male", "Married"));
            persons.add(new Person("Laura","Female", "Married"));
            persons.add(new Person("Diana","Female", "Single"));
            persons.add(new Person("Mike","Male", "Single"));
            persons.add(new Person("Bobby","Male", "Single"));

            Criteria male = new CriteriaMale();
            Criteria female = new CriteriaFemale();
            Criteria single = new CriteriaSingle();
            Criteria singleMale = new AndCriteria(single, male);
            Criteria singleOrFemale = new OrCriteria(single, female);

            System.out.println("Males: ");
            printPersons(male.meetCriteria(persons));

            System.out.println("\nFemales: ");
            printPersons(female.meetCriteria(persons));

            System.out.println("\nSingle Males: ");
            printPersons(singleMale.meetCriteria(persons));

            System.out.println("\nSingle Or Females: ");
            printPersons(singleOrFemale.meetCriteria(persons));
        }

        public static void printPersons(List<Person> persons){
            for (Person person : persons) {
                System.out.println("Person : [ Name : " + person.getName()
                        +", Gender : " + person.getGender()
                        +", Marital Status : " + person.getMaritalStatus()
                        +" ]");
            }
        }
    }
}
