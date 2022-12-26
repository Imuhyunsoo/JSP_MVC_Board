package edu.global.ex.Commend;

import edu.global.ex.Dao.BDao;
import edu.global.ex.Dto.BDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BReplyViewCommand implements BCommand {

    //자손이 구현하므로 interface 의 메소드를 override 한다.
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("BReplyViewCommand ..");

        String BId = request.getParameter("BId");

        BDao bDao = new BDao();
        BDto bDto = bDao.reply_view(BId);

        request.setAttribute("reply_view", bDto);
        System.out.println("BReplyViewCommand over"); // -> 디버깅 문구
    }
}
