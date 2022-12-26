package edu.global.ex.Commend;

import edu.global.ex.Dao.BDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BWriteCommand implements BCommand{

    // 자손이 구현하므로 interface 의 메소드를 override 한다.
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("BWriteCommand ..");

        String BName = request.getParameter("BName");
        String BTitle = request.getParameter("BTitle");
        String BContent = request.getParameter("BContent");

        BDao bDao = new BDao();
        // 테이블에 있는 모든 데이터를 끌고 온다는 의미
        bDao.write(BName,BTitle,BContent);
    }
}
