import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LocalDate now = LocalDate.now();
      

        // 결과 출력
        System.out.println(now);    
       
        System.out.println("== 시작 ==");

        Scanner sc = new Scanner(System.in);
        int lastArticleId = 0;

        List<Article> articles = new ArrayList<>();

        while (true) {
            System.out.printf("명령어 ) ");
            String cmd = sc.nextLine().trim();

            if (cmd.length() == 0) {
                System.out.println("명령어를 입력하세요");
                continue;
            }

            if (cmd.equals("exit")) {
                break;
            }

            if (cmd.equals("write")) {
                int id = lastArticleId + 1;
                lastArticleId = id;

                System.out.printf("제목 : ");
                String title = sc.nextLine();
                System.out.printf("내용 : ");
                String body = sc.nextLine();

                Article article = new Article(id, title, body);
                articles.add(article);

                System.out.printf("%d번 글이 생성되었습니다\n", id);

            } else if (cmd.equals("list")) {
                if (articles.size() == 0) {
                    System.out.println("게시글이 없습니다");
                } else {
                    System.out.println("게시글 목록");
                    for (Article article : articles) {
                        System.out.println("번호: " + article.id);
                        System.out.println("제목: " + article.title);
                        System.out.println("내용: " + article.body);
                        System.out.println();
                    }
                }
            } else if (cmd.startsWith("detail")) {
            	 String[] splitCmd = cmd.split(" ");
                if (splitCmd.length != 3) {
                    System.out.println("잘못된 명령어 형식입니다");
                    continue;
                }
                int articleId = Integer.parseInt(splitCmd[2]);
                Article article = getArticleById(articles, articleId);
                if (article == null) {
                    System.out.printf("%d번 게시물은 존재하지 않습니다\n", articleId);
                } else {
                    System.out.println("번호: " + article.id);
                    System.out.println("날짜: " + now);
                    System.out.println("제목: " + article.title);
                    System.out.println("내용: " + article.body);
                }
            } else if (cmd.startsWith("delete")) {
                String[] splitCmd = cmd.split(" ");
                if (splitCmd.length != 3) {
                    System.out.println("잘못된 명령어 형식입니다");
                    continue;
                }
                int articleId = Integer.parseInt(splitCmd[2]);
                boolean removed = removeArticleById(articles, articleId);
                if (removed) {
                    System.out.printf("%d번 게시물이 삭제되었습니다\n", articleId);
                } else {
                    System.out.printf("%d번 게시물은 존재하지 않습니다\n", articleId);
                }
            } else {
                System.out.println("없는 명령어입니다");
            }
        }

        sc.close();

        System.out.println("== 종료 ==");
    }

    private static Article getArticleById(List<Article> articles, int articleId) {
        for (Article article : articles) {
            if (article.id == articleId) {
                return article;
            }
        }
        return null;
    }

    private static boolean removeArticleById(List<Article> articles, int articleId) {
        for (Article article : articles) {
            if (article.id == articleId) {
                articles.remove(article);
                return true;
            }
        }
        return false;
    }
}

class Article {
    int id;
    String title;
    String body;

    public Article(int id, String title, String body) {
        this.id = id;
        this.title = title;
        this.body = body;
    }
}
