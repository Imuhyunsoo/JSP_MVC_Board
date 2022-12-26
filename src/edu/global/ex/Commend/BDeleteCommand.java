package edu.global.ex.Commend;

import edu.global.ex.Dao.BDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BDeleteCommand implements BCommand{

    //자손이 구현하므로 interface 의 메소드를 override 한다.
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("BDeleteCommand ..");

        String BId = request.getParameter("BId");

        BDao bDao = new BDao();
        bDao.delete(BId);
    }
}
