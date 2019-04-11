package com.academy.lesson13.model;

import com.academy.lesson11.subscr.Gender;
import com.academy.lesson11.subscr.Subscriber;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PhoneBookDemo {

//создвли отдельно переменную, передаваемую в массив просто для того,чтоб не привязываться к конкретной цифре
    private static int count_subscribers = 200;

    //создаем переменные с путями к каждому файлу хранящему имена
    private static final String MAN_FIRST_NAMES_PATH = "D:/Automation/MAVENproject/src/main/java/com.academy/lesson11/homeTask/мужские имена.txt";
    private static final String MAN_LAST_NAMES_PATH = "D:/Automation/MAVENproject/src/main/java/com.academy/lesson11/homeTask/мужские фамилии.txt";
    private static final String WOMAN_FIRST_NAMES_PATH = "D:/Automation/MAVENproject/src/main/java/com.academy/lesson11/homeTask/женские имена.txt";
    private static final String WOMAN_LAST_NAMES_PATH = "D:/Automation/MAVENproject/src/main/java/com.academy/lesson11/homeTask/женские фамилии.txt";

    //создаем списки с именами для каждого файла
    private  static List<String> manFirstNames = new ArrayList<>();
    private  static List<String> manLastNames = new ArrayList<>();
    private  static List<String> womanFirstNames = new ArrayList<>();
    private  static List<String> womanLastNames = new ArrayList<>();


    //для каждого случая считываем данные из файла, записываем в LIST
    public static void main(String[] args) {
        //подготавливаем данные
        manFirstNames = prepareData(MAN_FIRST_NAMES_PATH);
        manLastNames = prepareData(MAN_LAST_NAMES_PATH);
        womanFirstNames = prepareData(WOMAN_FIRST_NAMES_PATH);
        womanLastNames = prepareData(WOMAN_LAST_NAMES_PATH);


        Random random = new Random();

       //создаем цикл,в котором абонент будет генерировать 200 раз
        for(int i=0;i<=count_subscribers;i++){
        Subscriber subscriber=generateNextSubscriber(random);
            try {
                insertSubscriberToDB(subscriber);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        }//конец методо main


 //------------------------------------------------------------------------------------------------------
    //в этом методе мы считываем данные из файла и складываем их в LIST
    private static List<String> prepareData(String path)  {

        List<String> result = new ArrayList<>();

        try (
            BufferedReader bfr = new BufferedReader(new FileReader(path)))
        {
        String row;
        while ((row = bfr.readLine()) !=null)
           {
                result.add(row);
           }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


//------------------------------------------------------------------------------------------------------
//Генерируем случайного subscriber
private static Subscriber generateNextSubscriber(Random random) {
        // создаем экземпляр сабскрайбера
        Subscriber subscriber =  new Subscriber();
     //наполняем сабскайбера

    String firstName;
    String lastName;
    int nextIndex;

    if(random.nextBoolean()) {

        //Допустим мужчина
        nextIndex  =  random.nextInt(manFirstNames.size());
        firstName = manFirstNames.get(nextIndex);
        nextIndex = random.nextInt(manLastNames.size());
        lastName = manLastNames.get(nextIndex);
        subscriber.setGender(Gender.MALE);

    }
    else {

        //Женщина
        nextIndex  =  random.nextInt(womanFirstNames.size());
        firstName = womanFirstNames.get(nextIndex);
        nextIndex  =  random.nextInt(womanLastNames.size());
        lastName = womanLastNames.get(nextIndex);
        subscriber.setGender(Gender.FEMALE);

    }

    //присваиваем рандомное имя, считаенное предварительно из файла
    subscriber.setFirstName(firstName);
    subscriber.setLastName(lastName);
    subscriber.setAge(random.nextInt(86)+5);

    return subscriber;
        }
//------------------------------------------------------------------------------------------------------
        //  в этом методе будем описывать вставку данных из LIST  в базу данных
            private static void insertSubscriberToDB(Subscriber subscriber) throws SQLException {
                System.out.println(subscriber);

                try {
// Инициализация драйвера
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/qa-ja-06?user=root&password=root&serverTimezone=UTC&useSSL=false");
                    Statement statement = connection.createStatement();

                    for (int i = 0; i<200;i++) {
                        PreparedStatement preparedStatement = connection.prepareStatement
                                ("INSERT INTO abonent('first_name', 'last_name', 'gender', 'age') VALUES (?,?, ?, ?)");
                       // preparedStatement.setLong(1, subscriber.getId());
                        preparedStatement.setString(1, subscriber.getFirstName());
                        preparedStatement.setString(2, subscriber.getLastName());
                        preparedStatement.setString(3, String.valueOf(subscriber.getGender()));
                        preparedStatement.setInt(4, subscriber.getAge());
                        preparedStatement.execute();

                        ResultSet resultSet = statement.executeQuery("SELECT * FROM abonent");
                        while (resultSet.next()) {
                            long id = resultSet.getLong("abonent_id");
                            String firstName = resultSet.getString("first_name");
                            String lastName = resultSet.getString("last_name");
                            String gender = resultSet.getString("gender");
                            int age = resultSet.getInt("age");

                        }
                    }
for (int i= 0; i<200;i++) {
    System.out.println(String.format("ID = %d, First Name = %s, Last Name = %s, Age = %d, Gender = %s",
            subscriber.getId(), subscriber.getFirstName(), subscriber.getLastName(),  subscriber.getGender(),subscriber.getAge()));
}



                } catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                }


    }


}//конец класса












  /*      System.out.println("JDBC");

        try {
// Инициализация драйвера
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/qa-ja-06?user=root&password=root&serverTimezone=UTC&useSSL=false");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
*/







