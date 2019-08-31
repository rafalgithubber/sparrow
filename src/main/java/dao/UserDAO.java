package dao;

import model.User;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserDAO extends AbstractDAO {

  public List<User> list() {
    return entityManager.createQuery("select u from User u").getResultList();
  }

  public void saveUser(User user) {
    hibernateUtil.save(user);
  }

  public void deleteUser(long userId) {
    hibernateUtil.delete(User.class, userId);
  }

  public List<User> getUserByName(String name) {
    Query query = entityManager.createQuery("select u from User u where name = :name");
    return query.setParameter("name", name).getResultList();
  }

  public User getUserByEmail(String email) {
    TypedQuery<User> query = entityManager.createQuery("select u from User u where email = :email", User.class);
    return query.setParameter("email", email).getSingleResult();
  }

  public User getUserByLogin(String login) {
    TypedQuery<User> query = entityManager.createQuery("select u from User u where login = :login", User.class);
    return query.setParameter("login", login).getSingleResult();
  }

  public boolean isUserExist(String login, String password) {
    Query query = entityManager.createQuery("select count(*) as cnt from User u where u.login = :login and u.password = :password");
    query.setParameter("login", login);
    query.setParameter("password", password);
    Object singleResult = query.getSingleResult();
    return ((Long) singleResult > 0) ? true : false;
  }

  public List<User> getFollowingUsers(String followerLogin) {
    User user = getUserByLogin(followerLogin);
    Long userId = user.getId();
    Query query = entityManager.createQuery("select distinct follows from User u where u.id = :userId");
    return query.setParameter("userId", userId).getResultList();
  }
  public Set<String> getFollowingUsers2String(String userLogin){
    Set<String> output=new HashSet<>();
    getFollowingUsers(userLogin).forEach(u->output.add(u.getName()));

    return output;
  }


  public List<User> getNotFollowedUsers(String followerLogin) {
    List<User> users = entityManager.createQuery("select u from User u").getResultList();
    List<User> followed = getFollowingUsers(followerLogin);
    users.removeAll(followed);
    return users;
  }

  public void addFollower(String sourceLogin,String followerLogin){
    User sourceUser,followerUser;

    sourceUser = getUserByLogin(sourceLogin);
    followerUser = getUserByLogin(followerLogin);

    sourceUser.getFollows().add(followerUser);

    saveUser(sourceUser);
  }

  public void removeFollower(String sourceLogin,String removeLogin){
    User sourceUser,remoweUser;

    sourceUser = getUserByLogin(sourceLogin);
    remoweUser = getUserByLogin(removeLogin);

    if (sourceUser.getFollows().contains(remoweUser)){
      sourceUser.getFollows().remove(remoweUser);
    }
    else {
      System.out.println("Nie ma takiego FOLLOWERSA");
    }

    saveUser(sourceUser);
  }

}
