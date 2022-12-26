package edu.global.ex.Dao;

import edu.global.ex.Dto.BDto;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class BDao {

    private DataSource dataSource = null;  // 커넥션풀을 사용하기 위한 소스코드 (DataSource 를 import 할 시 java.sql 로 해야한다.)

    /* 기존에 driver 를 설정하고 Class.forName(driver)를 넣는 방식과는 달리 context.xml 에 리소스를 설정해주었다.
    context.xml 에 있는 소스를 읽기 위해 context 객체 생성한다. */
    public BDao() {

        try {
            /* jdbc/oracle : context.xml 에 들어간 Resource 에서 name 에 해당하는 부분
            위의 식은 context.xml 에서 name 을 lookup 찾으라는 뜻이다. */
            Context context = new InitialContext();
            dataSource = (DataSource)context.lookup("java:comp/env/jdbc/oracle");
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<BDto> list() {

        List<BDto> bDtoList = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String query = "SELECT * FROM MVC_BOARD ORDER BY BGROUP DESC, BSTEP ASC";  //  ORDER BY 정렬 추가
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();  // 데이터베이스 문구를 보내서 답변을 받아오는 문구

            while (resultSet.next()) {
                int BId = resultSet.getInt("BId");
                String BName = resultSet.getString("BName");
                String BTitle = resultSet.getString("BTitle");
                String BContent = resultSet.getString("BContent");
                Timestamp BDate = resultSet.getTimestamp("BDate");
                int BHit = resultSet.getInt("BHit");
                int BGroup = resultSet.getInt("BGroup");
                int BStep = resultSet.getInt("BStep");
                int BIndent = resultSet.getInt("BIndent");

                BDto bDto = new BDto(BId, BName, BTitle, BContent, BDate, BHit, BGroup, BStep, BIndent);
                bDtoList.add(bDto);
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                if(resultSet != null) resultSet.close();
                if(preparedStatement != null) preparedStatement.close();
                if(connection != null) connection.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return bDtoList;
    }

    public void write(String BName, String BTitle, String BContent) {

        System.out.println("write() ..");

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            /*
	           파라미터로 받는 값이 bName, bTitle, bContent 3개므로 3개만 ?로 넣고 나머지는 0으로
	           설정했다. 글 작성시 조회수(bHit)는 0이고, 원본 글로 취급하므로 bStep, bIndent 는
	           지수를 넣어줄 필요가 없기 때문이다.

	        String query = "INSERT INTO MVC_BOARD "
                + "(BId,BName,BTitle,BContent,BHit,BGroup,BStep,BIndent)"
                + "VALUES (MVC_BOARD_SEQ.nextVal,?,?,?,0,MVC_BOARD_SEQ.currVal,0,0)"; */

            String query = "INSERT INTO MVC_BOARD "
                    + "(BID, BNAME, BTITLE, BCONTENT, BHIT, BGROUP, BSTEP, BINDENT)"
                    + "VALUES (MVC_BOARD_SEQ.nextval,?,?,?,0,MVC_BOARD_SEQ.currval,0,0)";

            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1,BName);
            preparedStatement.setString(2,BTitle);
            preparedStatement.setString(3,BContent);

            int update = preparedStatement.executeUpdate();
            System.out.println("업데이트 갯수 : " + update);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if(preparedStatement != null) preparedStatement.close();
                if(connection != null) connection.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public BDto contentView(String BId) {

        BDto bDto = null;

        hit(BId);

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            // 쿼리문 작성시 찾으려는 글번호에 ? 로 넘기고 아래서 preparedStatement 로 set 해준다.
            String query = "SELECT * FROM MVC_BOARD WHERE BID = ?";
            preparedStatement = connection.prepareStatement(query);

            // int 형으로 setInt 해야하는데, BID 의 자료형은 String 이므로 Integer 로 바꿔준다.
            preparedStatement.setInt(1, Integer.parseInt(BId));
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                // 한 행의 데이터만 가져오기 때문에 while 말고 if(rs.next())로 해도 가능하다.
                int Id = resultSet.getInt("BId");
                String BName = resultSet.getString("BName");
                String BTitle = resultSet.getString("BTitle");
                String BContent = resultSet.getString("BContent");
                Timestamp BDate = resultSet.getTimestamp("BDate");
                int BHit = resultSet.getInt("BHit");
                int BGroup = resultSet.getInt("BGroup");
                int BStep = resultSet.getInt("BStep");
                int BIndent = resultSet.getInt("BIndent");

                bDto = new BDto(Id, BName, BTitle, BContent, BDate, BHit, BGroup, BStep, BIndent);
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                if(connection != null) connection.close();
                if(preparedStatement != null) preparedStatement.close();
                if(resultSet != null) resultSet.close();
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return bDto;
    }

    public void delete(String BId) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = dataSource.getConnection();
            String query = "DELETE FROM MVC_BOARD WHERE BID = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, Integer.parseInt(BId));

            int del = preparedStatement.executeUpdate();
            System.out.println("삭제 개수 : " + del);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                if(connection != null) connection.close();
                if(preparedStatement != null) preparedStatement.close();
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public BDto reply_view(String BId) {

        BDto bDto = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = dataSource.getConnection();
            // 쿼리문 작성시 찾으려는 글번호에 ?로 넘기고 아래서 preparedStatement 로 set 해준다.
            String query = "SELECT * FROM MVC_BOARD WHERE BID = ?";
            preparedStatement = connection.prepareStatement(query);
            // int 형으로 setInt 해야하는데, BID 의 자료형은 String 이므로 Integer 로 바꿔준다.
            preparedStatement.setInt(1, Integer.parseInt(BId));
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                // 한 행의 데이터만 가져오기 때문에 while 말고 if(rs.next())로 해도 가능하다.
                int Id = resultSet.getInt("BId");
                String BName = resultSet.getString("BName");
                String BTitle = resultSet.getString("BTitle");
                String BContent = resultSet.getString("BContent");
                Timestamp BDate = resultSet.getTimestamp("BDate");
                int BHit = resultSet.getInt("BHit");
                int BGroup = resultSet.getInt("BGroup");
                int BStep = resultSet.getInt("BStep");
                int BIndent = resultSet.getInt("BIndent");

                bDto = new BDto(Id, BName, BTitle, BContent, BDate, BHit, BGroup, BStep, BIndent);
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                if(resultSet != null) resultSet.close();
                if(connection != null) connection.close();
                if(preparedStatement != null) preparedStatement.close();
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return bDto;
    }

    public void replyShape(String BGroup, String BStep) {

        System.out.println("replyShape() ..");

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = dataSource.getConnection();
            /* 이때 파라미터엔 BGroup, BStep 이 들어가도록 하고, 답글달려는 원본글 기준 답글 달린 글들이
               1칸씩 아래로 가도록 쿼리문을 작성해주는 것이 핵심이다. */
            String query = "UPDATE MVC_BOARD SET BSTEP = BSTEP + 1 "
                    + "WHERE BGROUP = ? AND BSTEP > ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, Integer.parseInt(BGroup));
            preparedStatement.setInt(2, Integer.parseInt(BStep));

            int reply = preparedStatement.executeUpdate();
            System.out.println("답글 갯수 : " + reply);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                if(preparedStatement != null) preparedStatement.close();
                if(connection != null) connection.close();
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void reply(String BId, String BName, String BTitle, String BContent,
                      String BGroup, String BStep, String BIndent) {

        System.out.println("reply() ..");  // -> 답글위치 잡기, step 한칸씩 밀어내기

        replyShape(BGroup, BStep);

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = dataSource.getConnection();
            /* BStep 을 1씩 증가 시킨다는 건 ->
          	답글달려는 원본글 기준 답글 달린 글들이 1칸씩 아래로 내려간다는 뜻. */
            String query = "INSERT INTO MVC_BOARD(BID, BNAME, BTITLE, BCONTENT, BGROUP, BSTEP, BINDENT)"
                    + "VALUES (MVC_BOARD_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, BName);
            preparedStatement.setString(2, BTitle);
            preparedStatement.setString(3, BContent);
            preparedStatement.setInt(4, Integer.parseInt(BGroup));
            preparedStatement.setInt(5, Integer.parseInt(BStep) + 1);
            preparedStatement.setInt(6, Integer.parseInt(BIndent) + 1);

            preparedStatement.executeUpdate();  //

        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                if(preparedStatement != null) preparedStatement.close();
                if(connection != null) connection.close();
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void hit(String BId) {

        System.out.println("hit() ..");

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = dataSource.getConnection();
            // String query = "update mvc_board set bhit = bhit + 1 where bid = ?";
            String query = "UPDATE MVC_BOARD SET BHIT = BHIT + 1 WHERE BID = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, Integer.parseInt(BId));

            int rn = preparedStatement.executeUpdate();  // 데이터베이스 문구 보내기
            System.out.println("업데이트 갯수 : " + rn);

        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                if(preparedStatement != null) preparedStatement.close();
                if(connection != null) connection.close();
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}