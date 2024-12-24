package mysite.controller.action.board;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mysite.controller.action.ActionServlet;
import mysite.dao.BoardDao;
import mysite.vo.BoardVo;
import mysite.vo.UserVo;

import java.io.IOException;

public class ModifyAction implements ActionServlet.Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        UserVo userVo = (UserVo) req.getSession().getAttribute("authUser");

        if (id == null || title == null || content == null || userVo == null) {
            resp.sendRedirect(req.getContextPath() + "/board");
            return;
        }

        BoardVo boardVo = new BoardVo();
        boardVo.setId(Long.valueOf(id));
        boardVo.setTitle(title);
        boardVo.setContents(content);

        new BoardDao().updateBoard(boardVo,userVo.getId());
        resp.sendRedirect(req.getContextPath() + "/board");
    }
}
