import dao.TweetDAO;
import dao.UserDAO;
import java.util.Date;
import java.util.List;

import hibernate.util.HibernateUtil;
import model.Tweet;
import model.User;

public class main {

  public static void main(String[] args) {

    User user = new User("Pies","Psowaty","pies@aol.com",new Date(),"aaaa","pies");//(name, String lastName, String email, Date dateOfRegistration, String password, String login);
    User user2 = new User("Kot","Kotowaty","kot@aol.com",new Date(),"bbbb","kot");//(name, String lastName, String email, Date dateOfRegistration, String password, String login);
    User user3 = new User("Mysz","Myszowana","mysza@aol.com",new Date(),"cccc","mysz");//(name, String lastName, String email, Date dateOfRegistration, String password, String login);

    user.getFollows().add(user2);
    user.getFollows().add(user3);

    user3.getFollows().add(user);

    UserDAO userDAO = new UserDAO();

    userDAO.saveUser(user);
    userDAO.saveUser(user2);
    userDAO.saveUser(user3);

    System.out.println("Przed userDAO.addFollower");
    userDAO.addFollower("kot","mysz");
    System.out.println("Po userDAO.addFollower");

    System.out.println("Przed userDAO.removeFollower");
    userDAO.removeFollower("pies","mysz");
    //userDAO.removeFollower("pies","zjawa");

    System.out.println("Po userDAO.removeFollower");


    Tweet tweet = new Tweet();
    tweet.setMessage("sdasdasdas");
    tweet.setAuthor(user2);
    TweetDAO tweetDAO = new TweetDAO();

    tweetDAO.addTweet(user2.getLogin(), "asdasdasd");

    List<Tweet> followedTweets = tweetDAO.getFollowedTweets(user.getLogin());

    for(Tweet t : followedTweets){
      System.out.println(t.getAuthor() + " " + t.getMessage() + " " + t.getPublishedAt());
    }

    HibernateUtil.closeManagerFactory();
  }


}
