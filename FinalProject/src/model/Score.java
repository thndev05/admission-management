package model;

public class Score {
    private int studentId;
    private double score1;
    private double score2;
    private double score3;

    public Score(int studentId, double score1, double score2, double score3) {
        this.studentId = studentId;
        this.score1 = score1;
        this.score2 = score2;
        this.score3 = score3;
    }

    public Score() {
		// TODO Auto-generated constructor stub
	}

	public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public double getScore1() {
        return score1;
    }

    public void setScore1(double score1) {
        this.score1 = score1;
    }

    public double getScore2() {
        return score2;
    }

    public void setScore2(double score2) {
        this.score2 = score2;
    }

    public double getScore3() {
        return score3;
    }

    public void setScore3(double score3) {
        this.score3 = score3;
    }
}
