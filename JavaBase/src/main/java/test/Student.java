package test;


/**
 * Created by jingjie on 2018/9/9.
 */
public class Student {
    private int age;
    private int mathScore;
    private int englishScore;
    private int chineseScore;

    private Student(Builder builder){
        this.age = builder.age;
        this.mathScore = builder.mathScore;
        this.englishScore = builder.englishScore;
        this.chineseScore = builder.chineseScore;
    }

    public static class Builder{
        private int age;
        private int mathScore;
        private int englishScore;
        private int chineseScore;

        public Builder age(int age){
            this.age = age;
            return this;
        }

        public Builder mathScore(int mathScore){
            this.mathScore = mathScore;
            return this;
        }

        public Builder englishScore(int englishScore){
            this.englishScore = englishScore;
            return this;
        }

        public Builder chineseScore(int chineseScore){
            this.chineseScore = chineseScore;
            return this;
        }

        public Student build(){
            return new Student(this);
        }
    }

    public static void main(String[]args){
    }
}
