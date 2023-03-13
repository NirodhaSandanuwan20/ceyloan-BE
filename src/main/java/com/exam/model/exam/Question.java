package com.exam.model.exam;
import javax.persistence.*;
import java.util.Set;

@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long quesId;
    @Column(length = 5000)
    private String content;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String option5;
    private String answer;
    private Boolean Accuracy;
    @Transient
    private  String givenAnswer;

    @ManyToOne(fetch = FetchType.EAGER)
    private Quiz quiz;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "question_images",
            joinColumns = {
                    @JoinColumn(name = "ques_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "image_id")
            }
    )


    private Set<ImageModel> questionImages;

    public Set<ImageModel> getQuestionImages() {
        return questionImages;
    }

    public void setQuestionImages(Set<ImageModel> questionImages) {
        this.questionImages = questionImages;
    }




    public String getOption5() {
        return option5;
    }

    public void setOption5(String option5) {
        this.option5 = option5;
    }

    public Boolean getAccuracy() {
        return Accuracy;
    }

    public void setAccuracy(Boolean accuracy) {
        Accuracy = accuracy;
    }



    public Question() {
    }

    public Long getQuesId() {
        return quesId;
    }

    public void setQuesId(Long quesId) {
        this.quesId = quesId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public String getGivenAnswer() {
        return givenAnswer;
    }

    public void setGivenAnswer(String givenAnswer) {
        this.givenAnswer = givenAnswer;
    }
}
