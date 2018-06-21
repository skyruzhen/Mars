package core.po;

import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈微信图文消息〉
 *
 * @author lizhen
 * @create 2018/6/21
 * @since 1.0.0
 */
public class NewsMessage extends BaseMessage{
    private int ArticleCount;
    private List<News> Articles;

    public int getArticleCount() {
        return ArticleCount;
    }

    public void setArticleCount(int articleCount) {
        ArticleCount = articleCount;
    }

    public List<News> getArticles() {
        return Articles;
    }

    public void setArticles(List<News> articles) {
        Articles = articles;
    }
}