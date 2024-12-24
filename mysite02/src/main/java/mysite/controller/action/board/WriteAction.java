package mysite.controller.action.board;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mysite.controller.action.ActionServlet;
import mysite.dao.BoardDao;
import mysite.vo.UserVo;

import java.io.IOException;
import java.util.Optional;

public class WriteAction implements ActionServlet.Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        int g_no = Integer.parseInt(Optional.ofNullable(req.getParameter("g_no")).orElse("0"));
        int o_no = Integer.parseInt(Optional.ofNullable(req.getParameter("o_no")).orElse("0"));
        int depth = Integer.parseInt(Optional.ofNullable(req.getParameter("depth")).orElse("0"));

        if (req.getSession().getAttribute("authUser") == null) {
            resp.sendRedirect(req.getContextPath() + "/user?a=loginform");
        }

        if (title == null || content == null) {
            resp.sendRedirect(req.getContextPath() + "/board?a=writeform");
        }

        UserVo userVo = (UserVo) req.getSession().getAttribute("authUser");

        if (g_no != 0 && o_no != 0) {
            new BoardDao().insertReply(g_no, o_no, depth, userVo.getId(), title, content);
            resp.sendRedirect(req.getContextPath() + "/board");
            return;
        }
        if (new BoardDao().insertBoard(title,content,userVo.getId())) {
            resp.sendRedirect(req.getContextPath() + "/board");
        }
        else {
            resp.sendRedirect(req.getContextPath() + "/board?a=writeform");
        }
    }
}
