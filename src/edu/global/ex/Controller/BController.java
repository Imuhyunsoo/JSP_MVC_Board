package edu.global.ex.Controller;

import edu.global.ex.Commend.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("*.do")  // 모든 URL 들은 webServlet 에서 받아내겠다는 의미
public class BController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public BController() {  // 의미 없음.
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("doGet() ..");
        actionDo(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("doPost() ..");
        actionDo(request, response);
    }

    private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("actionDo() ..");

        request.setCharacterEncoding("UTF-8");

        String viewPage = null;
        BCommand command = null;

        // 위의 세 줄은 http://localhost:8282/jsp_mvc_board/list.do 에서 list.do를 꺼내기 위한 코드들
        String url = request.getRequestURI();  // result = /WebProjects_war_exploded/list.do
        String conPath = request.getContextPath();  // result = /WebProjects_war_exploded
        String com = url.substring(conPath.length());  // result = /list.do

        if(com.equals("/list.do")) {
            System.out.println("/list.do ..");

            command = new BListCommand();
            command.execute(request, response);
            viewPage = "list.jsp";
        }
        else if(com.equals("/write_view.do")) {
            System.out.println("/write_view.do ..");
            viewPage = "write_view.jsp";
        }
        else if(com.equals("/write.do")){
            System.out.println("/write.do ..");

            command = new BWriteCommand();
            command.execute(request, response);
            viewPage = "list.do";
        }
        else if(com.equals("/content_view.do")){
            System.out.println("/content_view.do ..");

            command = new BContentCommand();
            command.execute(request, response);
            viewPage = "content_view.jsp";
        }
        else if(com.equals("/delete.do")){
            System.out.println("/delete.do ..");

            command = new BDeleteCommand();
            command.execute(request, response);
            viewPage = "list.do";
        }
        else if(com.equals("/reply_view.do")){
            System.out.println("/reply_view.do ..");

            command = new BReplyViewCommand();
            command.execute(request, response);
            viewPage = "reply_view.jsp";
        }
        else if(com.equals("/reply.do")){
            System.out.println("/reply.do ..");

            command = new BReplyCommand();
            command.execute(request, response);
            viewPage = "list.do";
        }
        /*
	        클라이언트에게 list 는 list.jsp 로 forwarding 을 시키겠다는 의미이다.
	        forwarding 은 BListCommand 클래스에서 메모리에 올린 request, response 객체 정보를
	        list.jsp 에서 사용가능하다는 의미다. forwarding 될때까지 정보가 살아있기 때문이다.
	        고로 list.jsp 에서 forEach 문을 사용하여 데이터를 꺼낼 수 있다.
       */

        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
        dispatcher.forward(request, response);
    }
}
