package edu.global.ex.Commend;

import edu.global.ex.Dao.BDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BReplyCommand implements BCommand{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("BReplyCommand ..");

        String BId = request.getParameter("BId");
        String BName = request.getParameter("BName");
        String BTitle = request.getParameter("BTitle");
        String BContent = request.getParameter("BContent");
        String BGroup = request.getParameter("BGroup");
        String BStep = request.getParameter("BStep");
        String BIndent = request.getParameter("BIndent");

        BDao bDao = new BDao();
        bDao.reply(BId, BName, BTitle, BContent, BGroup, BStep, BIndent);

//        request.setAttribute("reply_view", bDao);
        System.out.println("BReplyCommand over");
    }
}
