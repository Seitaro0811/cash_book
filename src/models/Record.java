package models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name ="records")
@NamedQueries({
    @NamedQuery(
            name = "getAllRecords",
            query = "SELECT r FROM Record AS r WHERE r.book_user = :user ORDER BY r.date DESC"
            ),
    @NamedQuery(
            name = "getRecordsCount",
            query = "SELECT COUNT(r) FROM Record AS r WHERE r.book_user = :user"
            )
})
@Entity
public class Record {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "book_user_id", nullable = false)
    private BookUser book_user;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "amount", nullable = false)
    private Integer amount;

    @Lob
    @Column(name = "comment", nullable = true)
    private String comment;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BookUser getBook_user() {
        return book_user;
    }

    public void setBook_user(BookUser book_user) {
        this.book_user = book_user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}