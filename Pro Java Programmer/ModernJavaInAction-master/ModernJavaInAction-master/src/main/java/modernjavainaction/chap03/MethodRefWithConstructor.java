package modernjavainaction.chap03;

/**
 * @author viveksoni100
 */
public class MethodRefWithConstructor {

    public static void main(String[] args) {
        // Employee emp = Employee::new;
    }

    class Employee {
        private String name;

        public Employee() {
        }

        public Employee(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Employee{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
}
