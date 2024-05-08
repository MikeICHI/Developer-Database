//package org.example.online.web1;
//
//import org.example.company.employer.Developer;
//import org.example.company.employer.Employer;
//
//import java.util.Comparator;
//import java.util.List;
//import java.util.OptionalDouble;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//public class Example {
//    private static List<Developer> developers = List.of(
//            new Developer("Ivan Ivanov", 20, null),
//            new Developer("Andrey Ivanov", 22, "java"),
//            new Developer("Danila Ivanov", 30, "python"),
//            new Developer("Pavel Ivanov", 21, "java"),
//            new Developer("Denis Ivanov", 32, "java"),
//            new Developer("Mike Ivanov", 23, "java"),
//            new Developer("jake Ivanov", 32, "java"),
//            new Developer("ik Ivanov", 32, "java")
//    );
//
//    public static void main(String[] args) {
//        List<Integer> collect = developers.stream()
//                .map(Employer::getAge)//лямбда выражения
//                .toList();
//
//        OptionalDouble average = collect.stream()
//                .mapToInt(Integer::intValue)
//                .average();
//
//        System.out.println("average : " + average);
//
//        //comparator - сортировщик по возвр. значению
//        Comparator<Developer> comparator = (o1, o2) -> {
//            if (o1.getAge() == o2.getAge()){
//                return 0;
//            }
//            return (o1.getAge()>o2.getAge()) ? 1 : -1;
//        };
//
//       List<Developer> collect1 = developers.stream()
//                .sorted(comparator)
//                .collect(Collectors.toList());
//
//        System.out.println(collect1);
//
//
//        List<Developer> javaDevelopers = developers.stream()
//                .filter(x -> "java".equals(x.getLanguage()))
////                    .filter(x -> x.getLanguage().equals("java")) не безопасно, м. появиться null
//                .collect(Collectors.toList());
//
//        System.out.println(javaDevelopers);
//
//
//        Set<Developer> developerSet = developers.stream()
//                .filter(x -> x.getAge() > 30)
//                .collect(Collectors.toSet());
//
//        System.out.println(developerSet);
//
//
//        Set<String> stringSet = developers.stream()
//                .map(Developer::getLanguage)
//                .collect(Collectors.toSet());
//
//        System.out.println(stringSet);
//
//
//        String collect2 = developers.stream()
//                .map(Developer::getName)
//                .collect(Collectors.joining(","));
//
//        System.out.println(collect2);
//
//    }
//}
