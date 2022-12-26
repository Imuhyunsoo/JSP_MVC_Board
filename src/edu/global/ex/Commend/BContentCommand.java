package edu.global.ex.Commend;

import edu.global.ex.Dao.BDao;
import edu.global.ex.Dto.BDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BContentCommand implements BCommand{

    //자손이 구현하므로 interface 의 메소드를 override 한다.
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("BContentCommand ..");

        String Bid = request.getParameter("BId");

        // request 에 담긴 데이터를 BController 에서 forwarding 으로 넘김으로써
        // dto 객체를 content_view.jsp 에서 사용할 수 있도록 한다.
        BDao bDao = new BDao();
        // 테이블에 있는 모든 데이터를 끌고 온다는 뜻
        BDto bDto = bDao.contentView(Bid);

        request.setAttribute("content_view", bDto);
    }
}
