package web;

public class ContainerForPages {
    private String url;
    private int detectedWordFirst;
    private int detectedWordSecond;
    private int detectedWordThird;
    private int detectedWordFourth;
    private int detectedWordFifth;
    private int detectedWordsTotal;

    public int getDetectedWordFirst() {
        return detectedWordFirst;
    }

    public void setDetectedWordFirst(int detectedWordFirst) {
        this.detectedWordFirst = detectedWordFirst;
    }

    public int getDetectedWordSecond() {
        return detectedWordSecond;
    }

    public void setDetectedWordSecond(int detectedWordSecond) {
        this.detectedWordSecond = detectedWordSecond;
    }

    public int getDetectedWordThird() {
        return detectedWordThird;
    }

    public void setDetectedWordThird(int detectedWordThird) {
        this.detectedWordThird = detectedWordThird;
    }

    public int getDetectedWordFourth() {
        return detectedWordFourth;
    }

    public void setDetectedWordFourth(int detectedWordFourth) {
        this.detectedWordFourth = detectedWordFourth;
    }

    public int getDetectedWordFifth() {
        return detectedWordFifth;
    }

    public void setDetectedWordFifth(int detectedWordFifth) {
        this.detectedWordFifth = detectedWordFifth;
    }

    public int getDetectedWordsTotal() {
        return detectedWordsTotal;
    }

    public void setDetectedWordsTotal(int detectedWordsTotal) {
        this.detectedWordsTotal = detectedWordsTotal;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String toString() {
        return url + " " + detectedWordFirst + " " + detectedWordSecond
                + " " + detectedWordThird + " " + detectedWordFourth + " "
                + detectedWordFifth + " " + detectedWordsTotal;
    }
}
