package Exercici4;

import java.util.List;
import java.util.Random;

public class Articles {
    List<Article> articles;
    private Random rd;

    public Articles(List<Article> articles){
        this.articles = articles;
        this.rd = new Random();
    }
    public Article getArticleRandom(){
        int index = rd.nextInt(articles.size());
        return articles.get(index);
    }

    public List<Article> getArticles() {
        return articles;
    }
    public int getNumArticles(){
        return articles.size();
    }
}
