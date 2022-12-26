package edu.global.ex.Commend;

import edu.global.ex.Dao.BDao;
import edu.global.ex.Dto.BDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class BListCommand implements BCommand{

    // 자손이 구현하므로 interface 의 메소드를 override 한다.
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("BListCommand ..");

        BDao bDao = new BDao();
        // 테이블에 있는 모든 데이터를 끌고 온다는 의미
        List<BDto> bDtoList = bDao.list();

        // forwarding 될때까지 메모리에 살아있음을 반드시 기억해야한다.
        request.setAttribute("list", bDtoList);
    }
}
