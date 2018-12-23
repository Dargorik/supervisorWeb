//package supervisorweb.domain;
//
//import javax.persistence.*;
//
//@Entity
//public class ListOfCompletedWork {
//    @Id
//    @GeneratedValue(strategy= GenerationType.AUTO)
//    private Integer id;
//    private String comments;
//
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "user_id")
//    private User user;
//
//    public ListOfCompletedWork(){}
//
//    public ListOfCompletedWork(String comments, User user){
//        this.comments=comments;
//        this.user=user;
//    }
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer idusers) {
//        this.id = idusers;
//    }
//
//    public String getComments() {
//        return comments;
//    }
//
//    public void setComments(String comments) {
//        this.comments = comments;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//}
